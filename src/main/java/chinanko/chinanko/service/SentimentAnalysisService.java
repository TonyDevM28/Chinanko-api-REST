package chinanko.chinanko.service;

import java.math.BigDecimal;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.regex.Pattern;

import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

import lombok.AllArgsConstructor;
import lombok.Data;

@Service
public class SentimentAnalysisService {

    private static final String API_KEY = "AeUcBvm0aQauJH87QPh7DLUcAxVlg5KBN3sZoGwvdm8nfs4D4mpmJQQJ99BIACLArgHXJ3w3AAAEACOGdamn";
    private static final String ENDPOINT = "https://tonyindustriesuwu.cognitiveservices.azure.com/language/:analyze-text?api-version=2023-04-01";

    // Lista local de palabras ofensivas (puedes mover esto a BD si prefieres)
    private static final List<String> BAD_WORDS = Arrays.asList(
        "idiota", "estupido", "imbecil", "basura", "maldito", 
        "puto", "mierda", "pendejo", "verga", "chinga"
    );

    private final RestTemplate restTemplate = new RestTemplate();
    private final ObjectMapper objectMapper = new ObjectMapper();

    public TextAnalysisResult analyze(String text) {
        // 1. Validación Local Previa (Ahorra costos de API y latencia)
        if (containsProfanity(text)) {
            return new TextAnalysisResult("negative", BigDecimal.ONE, true); // Es ofensivo, lo marcamos negativo y peligroso
        }

        // 2. Si pasa el filtro local, consultamos a Azure para el sentimiento
        try {
            Map<String, Object> document = Map.of("id", "1", "language", "es", "text", text);
            Map<String, Object> analysisInput = Map.of("documents", List.of(document));
            Map<String, Object> body = Map.of(
                "kind", "SentimentAnalysis",
                "parameters", Map.of("modelVersion", "latest"),
                "analysisInput", analysisInput
            );

            HttpHeaders headers = new HttpHeaders();
            headers.setContentType(MediaType.APPLICATION_JSON);
            headers.set("Ocp-Apim-Subscription-Key", API_KEY);

            HttpEntity<Map<String, Object>> request = new HttpEntity<>(body, headers);
            String response = restTemplate.postForObject(ENDPOINT, request, String.class);
            
            JsonNode root = objectMapper.readTree(response);
            JsonNode docResult = root.path("results").path("documents").get(0);
            
            String sentiment = docResult.path("sentiment").asText();
            double score = docResult.path("confidenceScores").path(sentiment).asDouble();

            return new TextAnalysisResult(sentiment, BigDecimal.valueOf(score), false);

        } catch (Exception e) {
            e.printStackTrace();
            return new TextAnalysisResult("neutral", BigDecimal.ZERO, false);
        }
    }

    private boolean containsProfanity(String text) {
        if (text == null || text.trim().isEmpty()) return false;
        String normalizedText = text.toLowerCase();
        for (String word : BAD_WORDS) {
            // Usamos límites de palabra (\\b) para evitar falsos positivos
            Pattern pattern = Pattern.compile("\\b" + Pattern.quote(word) + "\\b", Pattern.CASE_INSENSITIVE);
            if (pattern.matcher(normalizedText).find()) {
                return true;
            }
        }
        return false;
    }

    @Data
    @AllArgsConstructor
    public static class TextAnalysisResult {
        private String sentiment;
        private BigDecimal score;
        private boolean isOffensive; // Nuevo campo
    }
}