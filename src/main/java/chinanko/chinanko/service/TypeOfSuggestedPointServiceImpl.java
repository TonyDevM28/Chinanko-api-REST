package chinanko.chinanko.service;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import chinanko.chinanko.dto.TypeOfSuggestedPointResponse;
import chinanko.chinanko.mapper.TypeOfSuggestedPointMapper;
import chinanko.chinanko.model.TypeOfSuggestedPoint;
import chinanko.chinanko.repository.TypeOfSuggestedPointRepository;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class TypeOfSuggestedPointServiceImpl implements TypeOfSuggestedPointService {

    private final TypeOfSuggestedPointRepository repository;

    @Override
    @Transactional(readOnly = true)
    public TypeOfSuggestedPointResponse getById(Integer id) {
        TypeOfSuggestedPoint entity = repository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Type of suggested point not found with id: " + id));
        return TypeOfSuggestedPointMapper.toResponse(entity);
    }

    @Override
    @Transactional(readOnly = true)
    public List<TypeOfSuggestedPointResponse> getAll() {
        return repository.findAll().stream()
                .map(TypeOfSuggestedPointMapper::toResponse)
                .collect(Collectors.toList());
    }

    @Override
    @Transactional(readOnly = true)
    public TypeOfSuggestedPointResponse getByName(String name) {
        TypeOfSuggestedPoint entity = repository.findByName(name)
                .orElseThrow(() -> new EntityNotFoundException("Type of suggested point not found with name: " + name));
        return TypeOfSuggestedPointMapper.toResponse(entity);
    }
}