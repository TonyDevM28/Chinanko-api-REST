package chinanko.chinanko.mapper;

import org.springframework.stereotype.Component;

import chinanko.chinanko.dto.ImageInterestPointRequest;
import chinanko.chinanko.dto.ImageInterestPointResponse;
import chinanko.chinanko.model.ImageInterestPoint;
import chinanko.chinanko.model.InterestPoint;

@Component
public class ImageInterestPointMapper {

    public static ImageInterestPoint toEntity(ImageInterestPointRequest r) {
        if (r == null) return null;

        return ImageInterestPoint.builder()
                .url(r.getUrl())
                .interestPoint(InterestPoint.builder().idInterestPoint(r.getInterestPointId()).build())
                .build();
    }

    public static ImageInterestPointResponse toResponse(ImageInterestPoint e) {
        if (e == null) return null;

        return ImageInterestPointResponse.builder()
                .idImageInterestPoint(e.getIdImageInterestPoint())
                .url(e.getUrl())
                .interestPointName(e.getInterestPoint() != null ? e.getInterestPoint().getNameInterestPoint() : null)
                .build();
    }

    public static void copyToEntity(ImageInterestPoint e, ImageInterestPointRequest r) {
        if (e == null || r == null) return;

        e.setUrl(r.getUrl());
        // Actualizamos la referencia al punto de interés si cambia
        e.setInterestPoint(InterestPoint.builder().idInterestPoint(r.getInterestPointId()).build());
    }
}