package chinanko.chinanko.service;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import chinanko.chinanko.dto.OpinionRequest;
import chinanko.chinanko.dto.OpinionResponse;
import chinanko.chinanko.mapper.OpinionMapper;
import chinanko.chinanko.model.InterestPoint;
import chinanko.chinanko.model.OpinionInterestPoint;
import chinanko.chinanko.model.ProfileUser;
import chinanko.chinanko.model.TypeOfOpinion;
import chinanko.chinanko.repository.InterestPointRepository;
import chinanko.chinanko.repository.OpinionRepository;
import chinanko.chinanko.repository.ProfileUserRepository;
import chinanko.chinanko.repository.TypeOfOpinionRepository;
import chinanko.chinanko.service.SentimentAnalysisService.TextAnalysisResult;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class OpinionServiceImpl implements OpinionService {

    private final OpinionRepository opinionRepository;
    private final InterestPointRepository interestPointRepository;
    private final ProfileUserRepository profileUserRepository;
    private final TypeOfOpinionRepository typeOfOpinionRepository;
    
    private final SentimentAnalysisService sentimentService;

    @Override
    @Transactional
    public OpinionResponse create(OpinionRequest request) {
        // 1. Análisis de Sentimientos + Ofensivo
        TextAnalysisResult analysis = sentimentService.analyze(request.getOpinion());

        // Bloqueo de contenido ofensivo
        if (analysis.isOffensive()) {
            throw new IllegalArgumentException("Tu opinión no pudo ser publicada porque contiene lenguaje ofensivo o inadecuado.");
        }

        // 2. Buscar entidades relacionadas
        InterestPoint interestPoint = interestPointRepository.findById(request.getInterestPointId())
                .orElseThrow(() -> new EntityNotFoundException("Interest Point not found"));
        
        ProfileUser user = profileUserRepository.findById(request.getUserId())
                .orElseThrow(() -> new EntityNotFoundException("User not found"));

        // 3. Buscar o asignar el Tipo de Opinión
        TypeOfOpinion type = typeOfOpinionRepository.findByPolarithy(analysis.getSentiment())
                .orElseGet(() -> {
                    TypeOfOpinion newType = TypeOfOpinion.builder().polarithy(analysis.getSentiment()).build();
                    return typeOfOpinionRepository.save(newType);
                });

        // 4. Guardar
        OpinionInterestPoint opinion = OpinionInterestPoint.builder()
                .opinion(request.getOpinion())
                .polarity(analysis.getScore())
                .typeOfOpinion(type)
                .interestPoint(interestPoint)
                .profileUser(user)
                .build();

        OpinionInterestPoint saved = opinionRepository.save(opinion);
        return OpinionMapper.toResponse(saved);
    }

    @Override
    @Transactional
    public OpinionResponse update(Integer id, OpinionRequest request) {
        OpinionInterestPoint existing = opinionRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Opinion not found"));

        // Re-analizar sentimiento
        TextAnalysisResult analysis = sentimentService.analyze(request.getOpinion());
        
        if (analysis.isOffensive()) {
            throw new IllegalArgumentException("La actualización contiene contenido inadecuado.");
        }

        TypeOfOpinion type = typeOfOpinionRepository.findByPolarithy(analysis.getSentiment())
                .orElseGet(() -> typeOfOpinionRepository.save(TypeOfOpinion.builder().polarithy(analysis.getSentiment()).build()));

        existing.setOpinion(request.getOpinion());
        existing.setPolarity(analysis.getScore());
        existing.setTypeOfOpinion(type);

        OpinionInterestPoint saved = opinionRepository.save(existing);
        return OpinionMapper.toResponse(saved);
    }

    @Override
    @Transactional
    public void delete(Integer id) {
        if (!opinionRepository.existsById(id)) {
            throw new EntityNotFoundException("Opinion not found");
        }
        opinionRepository.deleteById(id);
    }

    @Override
    @Transactional(readOnly = true)
    public List<OpinionResponse> getByInterestPoint(Integer interestPointId, int page, int pageSize) {
        Pageable pageable = PageRequest.of(page, pageSize);
        Page<OpinionInterestPoint> pageResult = opinionRepository.findByInterestPoint_IdInterestPoint(interestPointId, pageable);
        return pageResult.stream().map(OpinionMapper::toResponse).collect(Collectors.toList());
    }

    @Override
    @Transactional(readOnly = true)
    public List<OpinionResponse> getByUser(Integer userId, int page, int pageSize) {
        Pageable pageable = PageRequest.of(page, pageSize);
        Page<OpinionInterestPoint> pageResult = opinionRepository.findByProfileUser_IdProfileUser(userId, pageable);
        return pageResult.stream().map(OpinionMapper::toResponse).collect(Collectors.toList());
    }
}