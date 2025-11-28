package chinanko.chinanko.service;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import chinanko.chinanko.dto.SuggestedPointRequest;
import chinanko.chinanko.dto.SuggestedPointResponse;
import chinanko.chinanko.mapper.SuggestedPointMapper;
import chinanko.chinanko.model.SuggestedPoint;
import chinanko.chinanko.repository.SuggestedPointRepository;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class SuggestedPointServiceImpl implements SuggestedPointService {

    private final SuggestedPointRepository repository;

    @Override
    @Transactional
    public SuggestedPointResponse create(SuggestedPointRequest request) {
        SuggestedPoint entity = SuggestedPointMapper.toEntity(request);
        SuggestedPoint saved = repository.save(entity);
        return SuggestedPointMapper.toResponse(saved);
    }

    @Override
    @Transactional
    public SuggestedPointResponse update(Integer id, SuggestedPointRequest request) {
        SuggestedPoint existing = repository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Suggested Point not found with id: " + id));
        
        SuggestedPointMapper.copyToEntity(existing, request);
        SuggestedPoint saved = repository.save(existing);
        return SuggestedPointMapper.toResponse(saved);
    }

    @Override
    @Transactional(readOnly = true)
    public SuggestedPointResponse getById(Integer id) {
        SuggestedPoint entity = repository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Suggested Point not found with id: " + id));
        return SuggestedPointMapper.toResponse(entity);
    }

    @Override
    @Transactional(readOnly = true)
    public List<SuggestedPointResponse> getByTown(Integer townId, int page, int pageSize) {
        Pageable pageable = PageRequest.of(page, pageSize);
        Page<SuggestedPoint> result = repository.findByTown_IdTown(townId, pageable);
        return result.stream().map(SuggestedPointMapper::toResponse).collect(Collectors.toList());
    }

    @Override
    @Transactional(readOnly = true)
    public List<SuggestedPointResponse> getByTownAndType(Integer townId, Integer typeId, int page, int pageSize) {
        Pageable pageable = PageRequest.of(page, pageSize);
        Page<SuggestedPoint> result = repository.findByTown_IdTownAndTypeOfSuggestedPoint_IdTypeSuggested(townId, typeId, pageable);
        return result.stream().map(SuggestedPointMapper::toResponse).collect(Collectors.toList());
    }

    @Override
    @Transactional(readOnly = true)
    public List<SuggestedPointResponse> getByTownAndState(Integer townId, Integer stateId, int page, int pageSize) {
        Pageable pageable = PageRequest.of(page, pageSize);
        Page<SuggestedPoint> result = repository.findByTown_IdTownAndStateSuggestedPoint_IdStateSuggested(townId, stateId, pageable);
        return result.stream().map(SuggestedPointMapper::toResponse).collect(Collectors.toList());
    }
}