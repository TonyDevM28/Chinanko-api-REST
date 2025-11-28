package chinanko.chinanko.service;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import chinanko.chinanko.dto.StateSuggestedPointResponse;
import chinanko.chinanko.mapper.StateSuggestedPointMapper;
import chinanko.chinanko.model.StateSuggestedPoint;
import chinanko.chinanko.repository.StateSuggestedPointRepository;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class StateSuggestedPointServiceImpl implements StateSuggestedPointService {

    private final StateSuggestedPointRepository repository;

    @Override
    @Transactional(readOnly = true)
    public StateSuggestedPointResponse getById(Integer id) {
        StateSuggestedPoint entity = repository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("State Suggested Point not found with id: " + id));
        return StateSuggestedPointMapper.toResponse(entity);
    }

    @Override
    @Transactional(readOnly = true)
    public List<StateSuggestedPointResponse> getAll() {
        return repository.findAll().stream()
                .map(StateSuggestedPointMapper::toResponse)
                .collect(Collectors.toList());
    }

    @Override
    @Transactional(readOnly = true)
    public List<StateSuggestedPointResponse> getByStateValue(String state) {
        return repository.findByState(state).stream()
                .map(StateSuggestedPointMapper::toResponse)
                .collect(Collectors.toList());
    }
}