package chinanko.chinanko.service;

import java.util.List;

import chinanko.chinanko.dto.ImageSuggestedPointRequest;
import chinanko.chinanko.dto.ImageSuggestedPointResponse;

public interface ImagesSuggestedPointService {

    ImageSuggestedPointResponse create(ImageSuggestedPointRequest request);

    ImageSuggestedPointResponse update(Integer id, ImageSuggestedPointRequest request);

    ImageSuggestedPointResponse getById(Integer id);

    void delete(Integer id);

    // Obtener imágenes por punto sugerido
    List<ImageSuggestedPointResponse> getBySuggestedPointId(Integer suggestedPointId);
}