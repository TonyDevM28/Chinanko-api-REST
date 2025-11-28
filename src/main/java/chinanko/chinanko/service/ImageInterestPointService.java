package chinanko.chinanko.service;

import java.util.List;

import chinanko.chinanko.dto.ImageInterestPointRequest;
import chinanko.chinanko.dto.ImageInterestPointResponse;

public interface ImageInterestPointService {

    ImageInterestPointResponse create(ImageInterestPointRequest request);

    ImageInterestPointResponse update(Integer id, ImageInterestPointRequest request);

    ImageInterestPointResponse getById(Integer id);

    void delete(Integer id);

    // Obtener galería de imágenes de un punto de interés
    List<ImageInterestPointResponse> getByInterestPointId(Integer interestPointId);
}