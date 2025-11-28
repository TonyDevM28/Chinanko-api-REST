package chinanko.chinanko.service;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import chinanko.chinanko.dto.TypeOfInterestPointResponse;
import chinanko.chinanko.mapper.TypeOfInterestPointMapper;
import chinanko.chinanko.model.TypeOfInterestPoint;
import chinanko.chinanko.repository.TypeOfInterestPointRepository;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class TypeOfInterestPointServiceImpl implements TypeOfInterestPointService {

    private final TypeOfInterestPointRepository repository;

    @Override
    @Transactional(readOnly = true)
    public TypeOfInterestPointResponse getById(Integer id) {
        TypeOfInterestPoint entity = repository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Type of interest point not found with id: " + id));
        return TypeOfInterestPointMapper.toResponse(entity);
    }

    @Override
    @Transactional(readOnly = true)
    public List<TypeOfInterestPointResponse> getAll() {
        return repository.findAll().stream()
                .map(TypeOfInterestPointMapper::toResponse)
                .collect(Collectors.toList());
    }
}