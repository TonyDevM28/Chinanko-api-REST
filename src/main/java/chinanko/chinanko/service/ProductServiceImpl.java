package chinanko.chinanko.service;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import chinanko.chinanko.dto.ProductRequest;
import chinanko.chinanko.dto.ProductResponse;
import chinanko.chinanko.mapper.ProductMapper;
import chinanko.chinanko.model.Product;
import chinanko.chinanko.repository.ProductRepository;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class ProductServiceImpl implements ProductService {

    private final ProductRepository repository;

    @Override
    @Transactional
    public ProductResponse create(ProductRequest request) {
        Product entity = ProductMapper.toEntity(request);
        Product saved = repository.save(entity);
        return ProductMapper.toResponse(saved);
    }

    @Override
    @Transactional
    public ProductResponse update(Integer id, ProductRequest request) {
        Product existing = repository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Product not found with id: " + id));
        
        ProductMapper.copyToEntity(existing, request);
        Product saved = repository.save(existing);
        return ProductMapper.toResponse(saved);
    }

    @Override
    @Transactional(readOnly = true)
    public ProductResponse getById(Integer id) {
        Product entity = repository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Product not found with id: " + id));
        return ProductMapper.toResponse(entity);
    }

    @Override
    @Transactional(readOnly = true)
    public List<ProductResponse> getByCatalog(Integer catalogId, int page, int pageSize) {
        Pageable pageable = PageRequest.of(page, pageSize);
        Page<Product> result = repository.findByCatalog_IdCatalog(catalogId, pageable);
        
        return result.stream()
                .map(ProductMapper::toResponse)
                .collect(Collectors.toList());
    }

    @Override
    @Transactional(readOnly = true)
    public List<ProductResponse> getByCatalogAndType(Integer catalogId, Integer typeId, int page, int pageSize) {
        Pageable pageable = PageRequest.of(page, pageSize);
        Page<Product> result = repository.findByCatalog_IdCatalogAndTypeOfProduct_IdTypeProduct(catalogId, typeId, pageable);
        
        return result.stream()
                .map(ProductMapper::toResponse)
                .collect(Collectors.toList());
    }

    @Override
    @Transactional(readOnly = true)
    public ProductResponse getByNameAndCatalog(String name, Integer catalogId) {
        Product entity = repository.findByNameAndCatalog_IdCatalog(name, catalogId)
                .orElseThrow(() -> new EntityNotFoundException("Product not found with name '" + name + "' in catalog " + catalogId));
        return ProductMapper.toResponse(entity);
    }
}