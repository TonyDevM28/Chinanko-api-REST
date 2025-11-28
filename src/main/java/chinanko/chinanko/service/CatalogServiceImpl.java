package chinanko.chinanko.service;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import chinanko.chinanko.dto.CatalogRequest;
import chinanko.chinanko.dto.CatalogResponse;
import chinanko.chinanko.mapper.CatalogMapper;
import chinanko.chinanko.model.Catalog;
import chinanko.chinanko.repository.CatalogRepository;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class CatalogServiceImpl implements CatalogService {

    private final CatalogRepository repository;

    @Override
    @Transactional
    public CatalogResponse create(CatalogRequest request) {
        // Validar si ya existe un catálogo para ese punto de interés (Relación 1 a 1)
        if (repository.findByInterestPoint_IdInterestPoint(request.getInterestPointId()).isPresent()) {
            throw new IllegalArgumentException("A catalog already exists for this interest point.");
        }

        Catalog entity = CatalogMapper.toEntity(request);
        Catalog saved = repository.save(entity);
        return CatalogMapper.toResponse(saved);
    }

    @Override
    @Transactional
    public CatalogResponse update(Integer id, CatalogRequest request) {
        Catalog existing = repository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Catalog not found with id: " + id));
        
        CatalogMapper.copyToEntity(existing, request);
        Catalog saved = repository.save(existing);
        return CatalogMapper.toResponse(saved);
    }

    @Override
    @Transactional(readOnly = true)
    public CatalogResponse getById(Integer id) {
        Catalog entity = repository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Catalog not found with id: " + id));
        return CatalogMapper.toResponse(entity);
    }

    @Override
    @Transactional(readOnly = true)
    public CatalogResponse getByInterestPointId(Integer interestPointId) {
        Catalog entity = repository.findByInterestPoint_IdInterestPoint(interestPointId)
                .orElseThrow(() -> new EntityNotFoundException("Catalog not found for interest point id: " + interestPointId));
        return CatalogMapper.toResponse(entity);
    }

    @Override
    @Transactional(readOnly = true)
    public CatalogResponse getByInterestPointName(String interestPointName) {
        Catalog entity = repository.findByInterestPoint_NameInterestPoint(interestPointName)
                .orElseThrow(() -> new EntityNotFoundException("Catalog not found for interest point name: " + interestPointName));
        return CatalogMapper.toResponse(entity);
    }
}