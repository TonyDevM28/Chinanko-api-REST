package chinanko.chinanko.service;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import chinanko.chinanko.dto.InterestPointRequest;
import chinanko.chinanko.dto.InterestPointResponse;
import chinanko.chinanko.mapper.InterestPointMapper;
import chinanko.chinanko.model.InterestPoint;
import chinanko.chinanko.repository.InterestPointRepository;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class InterestPointServiceImpl implements InterestPointService {

    private final InterestPointRepository repository;

    @Override
    @Transactional
    public InterestPointResponse create(InterestPointRequest request) {
        InterestPoint entity = InterestPointMapper.toEntity(request);
        InterestPoint saved = repository.save(entity);
        return InterestPointMapper.toResponse(saved);
    }

    @Override
    @Transactional
    public InterestPointResponse update(Integer id, InterestPointRequest request) {
        InterestPoint existing = repository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Interest Point not found with id: " + id));

        InterestPointMapper.copyToEntity(existing, request);
        InterestPoint saved = repository.save(existing);
        return InterestPointMapper.toResponse(saved);
    }

    @Override
    @Transactional(readOnly = true)
    public InterestPointResponse getById(Integer id) {
        InterestPoint entity = repository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Interest Point not found with id: " + id));
        return InterestPointMapper.toResponse(entity);
    }

    @Override
    @Transactional(readOnly = true)
    public InterestPointResponse getByName(String name) {
        // ACTUALIZADO: Llamada al método corregido en el repositorio
        InterestPoint entity = repository.findByNameInterestPoint(name)
                .orElseThrow(() -> new EntityNotFoundException("Interest Point not found with name: " + name));
        return InterestPointMapper.toResponse(entity);
    }

    @Override
    @Transactional(readOnly = true)
    public List<InterestPointResponse> getByTown(Integer townId, int page, int pageSize) {
        Pageable pageable = PageRequest.of(page, pageSize);
        Page<InterestPoint> pageResult = repository.findByTown_IdTown(townId, pageable);

        return pageResult.stream()
                .map(InterestPointMapper::toResponse)
                .collect(Collectors.toList());
    }

    @Override
    @Transactional(readOnly = true)
    public List<InterestPointResponse> getByTownAndType(Integer townId, Integer typeId, int page, int pageSize) {
        Pageable pageable = PageRequest.of(page, pageSize);
        Page<InterestPoint> pageResult = repository
                .findByTown_IdTownAndTypeOfInterestPoint_IdTypeInterestPoint(townId, typeId, pageable);

        return pageResult.stream()
                .map(InterestPointMapper::toResponse)
                .collect(Collectors.toList());
    }
}