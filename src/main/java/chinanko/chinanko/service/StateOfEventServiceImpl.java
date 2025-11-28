package chinanko.chinanko.service;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import chinanko.chinanko.dto.StateOfEventResponse;
import chinanko.chinanko.mapper.StateOfEventMapper;
import chinanko.chinanko.model.StateOfEvent;
import chinanko.chinanko.repository.StateOfEventRepository;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class StateOfEventServiceImpl implements StateOfEventService {

    private final StateOfEventRepository repository;

    @Override
    @Transactional(readOnly = true)
    public StateOfEventResponse getById(Integer id) {
        StateOfEvent entity = repository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("State of event not found with id: " + id));
        return StateOfEventMapper.toResponse(entity);
    }

    @Override
    @Transactional(readOnly = true)
    public List<StateOfEventResponse> getAll() {
        return repository.findAll().stream()
                .map(StateOfEventMapper::toResponse)
                .collect(Collectors.toList());
    }

    @Override
    @Transactional(readOnly = true)
    public StateOfEventResponse getByName(String stateName) {
        StateOfEvent entity = repository.findByState(stateName)
                .orElseThrow(() -> new EntityNotFoundException("State of event not found with name: " + stateName));
        return StateOfEventMapper.toResponse(entity);
    }
}