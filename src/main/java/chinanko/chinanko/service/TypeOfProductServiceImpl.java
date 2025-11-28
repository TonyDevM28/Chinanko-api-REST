package chinanko.chinanko.service;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import chinanko.chinanko.dto.TypeOfProductResponse;
import chinanko.chinanko.mapper.TypeOfProductMapper;
import chinanko.chinanko.model.TypeOfProduct;
import chinanko.chinanko.repository.TypeOfProductRepository;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class TypeOfProductServiceImpl implements TypeOfProductService {

    private final TypeOfProductRepository repository;

    @Override
    @Transactional(readOnly = true)
    public TypeOfProductResponse getById(Integer id) {
        TypeOfProduct entity = repository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Type of product not found with id: " + id));
        return TypeOfProductMapper.toResponse(entity);
    }

    @Override
    @Transactional(readOnly = true)
    public List<TypeOfProductResponse> getAll() {
        return repository.findAll().stream()
                .map(TypeOfProductMapper::toResponse)
                .collect(Collectors.toList());
    }

    @Override
    @Transactional(readOnly = true)
    public TypeOfProductResponse getByName(String typeName) {
        TypeOfProduct entity = repository.findByType(typeName)
                .orElseThrow(() -> new EntityNotFoundException("Type of product not found with name: " + typeName));
        return TypeOfProductMapper.toResponse(entity);
    }
}