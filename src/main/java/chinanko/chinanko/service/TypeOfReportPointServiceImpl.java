package chinanko.chinanko.service;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import chinanko.chinanko.dto.TypeOfReportPointResponse;
import chinanko.chinanko.mapper.TypeOfReportPointMapper;
import chinanko.chinanko.model.TypeOfReportPoint;
import chinanko.chinanko.repository.TypeOfReportPointRepository;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class TypeOfReportPointServiceImpl implements TypeOfReportPointService {

    private final TypeOfReportPointRepository repository;

    @Override
    @Transactional(readOnly = true)
    public TypeOfReportPointResponse getById(Integer id) {
        TypeOfReportPoint entity = repository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Type of Report Point not found with id: " + id));
        return TypeOfReportPointMapper.toResponse(entity);
    }

    @Override
    @Transactional(readOnly = true)
    public List<TypeOfReportPointResponse> getAll() {
        return repository.findAll().stream()
                .map(TypeOfReportPointMapper::toResponse)
                .collect(Collectors.toList());
    }
}