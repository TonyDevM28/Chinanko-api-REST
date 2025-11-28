package chinanko.chinanko.service;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import chinanko.chinanko.dto.ImageInterestPointRequest;
import chinanko.chinanko.dto.ImageInterestPointResponse;
import chinanko.chinanko.mapper.ImageInterestPointMapper;
import chinanko.chinanko.model.ImageInterestPoint;
import chinanko.chinanko.repository.ImageInterestPointRepository;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class ImageInterestPointServiceImpl implements ImageInterestPointService {

    private final ImageInterestPointRepository repository;

    @Override
    @Transactional
    public ImageInterestPointResponse create(ImageInterestPointRequest request) {
        ImageInterestPoint entity = ImageInterestPointMapper.toEntity(request);
        ImageInterestPoint saved = repository.save(entity);
        return ImageInterestPointMapper.toResponse(saved);
    }

    @Override
    @Transactional
    public ImageInterestPointResponse update(Integer id, ImageInterestPointRequest request) {
        ImageInterestPoint existing = repository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Image not found with id: " + id));
        
        ImageInterestPointMapper.copyToEntity(existing, request);
        ImageInterestPoint saved = repository.save(existing);
        return ImageInterestPointMapper.toResponse(saved);
    }

    @Override
    @Transactional(readOnly = true)
    public ImageInterestPointResponse getById(Integer id) {
        ImageInterestPoint entity = repository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Image not found with id: " + id));
        return ImageInterestPointMapper.toResponse(entity);
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
    public List<ImageInterestPointResponse> getByInterestPointId(Integer interestPointId) {
        return repository.findByInterestPoint_IdInterestPoint(interestPointId).stream()
                .map(ImageInterestPointMapper::toResponse)
                .collect(Collectors.toList());
    }
}