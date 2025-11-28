package chinanko.chinanko.service;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import chinanko.chinanko.dto.TypeOfReportResponse;
import chinanko.chinanko.mapper.TypeOfReportMapper;
import chinanko.chinanko.model.TypeOfReport;
import chinanko.chinanko.repository.TypeOfReportRepository;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class TypeOfReportServiceImpl implements TypeOfReportService {

    private final TypeOfReportRepository repository;

    @Override
    @Transactional(readOnly = true)
    public TypeOfReportResponse getById(Integer id) {
        TypeOfReport entity = repository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Type of report not found with id: " + id));
        return TypeOfReportMapper.toResponse(entity);
    }

    @Override
    @Transactional(readOnly = true)
    public List<TypeOfReportResponse> getAll() {
        return repository.findAll().stream()
                .map(TypeOfReportMapper::toResponse)
                .collect(Collectors.toList());
    }

    @Override
    @Transactional(readOnly = true)
    public TypeOfReportResponse getByName(String typeName) {
        TypeOfReport entity = repository.findByType(typeName)
                .orElseThrow(() -> new EntityNotFoundException("Type of report not found with name: " + typeName));
        return TypeOfReportMapper.toResponse(entity);
    }
}