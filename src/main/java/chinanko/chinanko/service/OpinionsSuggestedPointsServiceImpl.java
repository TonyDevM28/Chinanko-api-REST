package chinanko.chinanko.service;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import chinanko.chinanko.dto.OpinionSuggestedPointRequest;
import chinanko.chinanko.dto.OpinionSuggestedPointResponse;
import chinanko.chinanko.mapper.OpinionSuggestedPointMapper;
import chinanko.chinanko.model.OpinionsSuggestedPoints;
import chinanko.chinanko.model.ProfileUser;
import chinanko.chinanko.model.SuggestedPoint;
import chinanko.chinanko.model.TypesOfOpinionsSuggestedPoint;
import chinanko.chinanko.repository.OpinionsSuggestedPointsRepository;
import chinanko.chinanko.repository.ProfileUserRepository;
import chinanko.chinanko.repository.SuggestedPointRepository;
import chinanko.chinanko.repository.TypesOfOpinionsSuggestedPointRepository;
import chinanko.chinanko.service.SentimentAnalysisService.TextAnalysisResult;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class OpinionsSuggestedPointsServiceImpl implements OpinionsSuggestedPointsService {

    private final OpinionsSuggestedPointsRepository repository;
    private final SuggestedPointRepository suggestedPointRepository;
    private final ProfileUserRepository profileUserRepository;
    private final TypesOfOpinionsSuggestedPointRepository typesRepository;
    
    private final SentimentAnalysisService sentimentService;

    @Override
    @Transactional
    public OpinionSuggestedPointResponse create(OpinionSuggestedPointRequest request) {
        
        // 1. Análisis Completo (Sentimiento + Ofensivo)
        TextAnalysisResult analysis = sentimentService.analyze(request.getOpinion());

        // REGLA 1: Bloquear contenido ofensivo
        if (analysis.isOffensive()) {
            throw new IllegalArgumentException("Tu opinión no pudo ser publicada porque contiene lenguaje ofensivo o inadecuado.");
        }

        // 2. Buscar entidades padre
        SuggestedPoint point = suggestedPointRepository.findById(request.getSuggestedPointId())
                .orElseThrow(() -> new EntityNotFoundException("Suggested Point not found"));
        
        ProfileUser user = profileUserRepository.findById(request.getUserId())
                .orElseThrow(() -> new EntityNotFoundException("User not found"));

        // 3. Tipo de Opinión
        TypesOfOpinionsSuggestedPoint type = typesRepository.findByType(analysis.getSentiment())
                .orElseGet(() -> typesRepository.save(
                    TypesOfOpinionsSuggestedPoint.builder().type(analysis.getSentiment()).build()
                ));

        // 4. Guardar
        OpinionsSuggestedPoints opinion = OpinionsSuggestedPoints.builder()
                .opinion(request.getOpinion())
                .polarity(analysis.getScore())
                .ofOpinionsSuggestedPoint(type)
                .suggestedPoint(point)
                .profileUser(user)
                .build();

        return OpinionSuggestedPointMapper.toResponse(repository.save(opinion));
    }

    @Override
    @Transactional
    public OpinionSuggestedPointResponse update(Integer id, OpinionSuggestedPointRequest request) {
        OpinionsSuggestedPoints existing = repository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Opinion not found"));

        // Análisis al actualizar
        TextAnalysisResult analysis = sentimentService.analyze(request.getOpinion());
        
        if (analysis.isOffensive()) {
            throw new IllegalArgumentException("La actualización contiene lenguaje ofensivo.");
        }

        TypesOfOpinionsSuggestedPoint type = typesRepository.findByType(analysis.getSentiment())
                .orElseGet(() -> typesRepository.save(
                    TypesOfOpinionsSuggestedPoint.builder().type(analysis.getSentiment()).build()
                ));

        existing.setOpinion(request.getOpinion());
        existing.setPolarity(analysis.getScore());
        existing.setOfOpinionsSuggestedPoint(type);

        return OpinionSuggestedPointMapper.toResponse(repository.save(existing));
    }

    @Override
    @Transactional
    public void delete(Integer id) {
        if (!repository.existsById(id)) {
            throw new EntityNotFoundException("Opinion not found");
        }
        repository.deleteById(id);
    }

    @Override
    @Transactional(readOnly = true)
    public List<OpinionSuggestedPointResponse> getBySuggestedPoint(Integer pointId, int page, int pageSize) {
        Pageable pageable = PageRequest.of(page, pageSize);
        Page<OpinionsSuggestedPoints> result = repository.findBySuggestedPoint_IdSuggestedPoint(pointId, pageable);
        return result.stream().map(OpinionSuggestedPointMapper::toResponse).collect(Collectors.toList());
    }

    @Override
    @Transactional(readOnly = true)
    public List<OpinionSuggestedPointResponse> getByUser(Integer userId, int page, int pageSize) {
        Pageable pageable = PageRequest.of(page, pageSize);
        Page<OpinionsSuggestedPoints> result = repository.findByProfileUser_IdProfileUser(userId, pageable);
        return result.stream().map(OpinionSuggestedPointMapper::toResponse).collect(Collectors.toList());
    }
}
