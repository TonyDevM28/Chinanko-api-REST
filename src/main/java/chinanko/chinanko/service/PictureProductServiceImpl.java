package chinanko.chinanko.service;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import chinanko.chinanko.dto.PictureProductRequest;
import chinanko.chinanko.dto.PictureProductResponse;
import chinanko.chinanko.mapper.PictureProductMapper;
import chinanko.chinanko.model.PictureProduct;
import chinanko.chinanko.repository.PictureProductRepository;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class PictureProductServiceImpl implements PictureProductService {

    private final PictureProductRepository repository;

    @Override
    @Transactional
    public PictureProductResponse create(PictureProductRequest request) {
        PictureProduct entity = PictureProductMapper.toEntity(request);
        PictureProduct saved = repository.save(entity);
        return PictureProductMapper.toResponse(saved);
    }

    @Override
    @Transactional
    public PictureProductResponse update(Integer id, PictureProductRequest request) {
        PictureProduct existing = repository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Image not found with id: " + id));
        
        PictureProductMapper.copyToEntity(existing, request);
        PictureProduct saved = repository.save(existing);
        return PictureProductMapper.toResponse(saved);
    }

    @Override
    @Transactional(readOnly = true)
    public PictureProductResponse getById(Integer id) {
        PictureProduct entity = repository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Image not found with id: " + id));
        return PictureProductMapper.toResponse(entity);
    }

    @Override
    @Transactional
    public void delete(Integer id) {
        if (!repository.existsById(id)) {
            throw new EntityNotFoundException("Cannot delete. Image not found with id: " + id);
        }
        repository.deleteById(id);
    }

    @Override
    @Transactional(readOnly = true)
    public List<PictureProductResponse> getByProductId(Integer productId) {
        return repository.findByProduct_IdProduct(productId).stream()
                .map(PictureProductMapper::toResponse)
                .collect(Collectors.toList());
    }
}