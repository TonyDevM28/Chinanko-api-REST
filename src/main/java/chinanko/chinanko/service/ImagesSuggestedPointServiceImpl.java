package chinanko.chinanko.service;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import chinanko.chinanko.dto.ImageSuggestedPointRequest;
import chinanko.chinanko.dto.ImageSuggestedPointResponse;
import chinanko.chinanko.mapper.ImagesSuggestedPointMapper;
import chinanko.chinanko.model.ImagesSuggestedPoint;
import chinanko.chinanko.repository.ImagesSuggestedPointRepository;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class ImagesSuggestedPointServiceImpl implements ImagesSuggestedPointService {

    private final ImagesSuggestedPointRepository repository;

    @Override
    @Transactional
    public ImageSuggestedPointResponse create(ImageSuggestedPointRequest request) {
        ImagesSuggestedPoint entity = ImagesSuggestedPointMapper.toEntity(request);
        ImagesSuggestedPoint saved = repository.save(entity);
        return ImagesSuggestedPointMapper.toResponse(saved);
    }

    @Override
    @Transactional
    public ImageSuggestedPointResponse update(Integer id, ImageSuggestedPointRequest request) {
        ImagesSuggestedPoint existing = repository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Image not found with id: " + id));
        
        ImagesSuggestedPointMapper.copyToEntity(existing, request);
        ImagesSuggestedPoint saved = repository.save(existing);
        return ImagesSuggestedPointMapper.toResponse(saved);
    }

    @Override
    @Transactional(readOnly = true)
    public ImageSuggestedPointResponse getById(Integer id) {
        ImagesSuggestedPoint entity = repository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Image not found with id: " + id));
        return ImagesSuggestedPointMapper.toResponse(entity);
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
    public List<ImageSuggestedPointResponse> getBySuggestedPointId(Integer suggestedPointId) {
        return repository.findBySuggestedPoint_IdSuggestedPoint(suggestedPointId).stream()
                .map(ImagesSuggestedPointMapper::toResponse)
                .collect(Collectors.toList());
    }
}