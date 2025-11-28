package chinanko.chinanko.mapper;

import org.springframework.stereotype.Component;

import chinanko.chinanko.dto.ImageSuggestedPointRequest;
import chinanko.chinanko.dto.ImageSuggestedPointResponse;
import chinanko.chinanko.model.ImagesSuggestedPoint;
import chinanko.chinanko.model.SuggestedPoint;

@Component
public class ImagesSuggestedPointMapper {

    public static ImagesSuggestedPoint toEntity(ImageSuggestedPointRequest r) {
        if (r == null) return null;

        return ImagesSuggestedPoint.builder()
                .url(r.getUrl())
                .suggestedPoint(SuggestedPoint.builder().idSuggestedPoint(r.getSuggestedPointId()).build())
                .build();
    }

    public static ImageSuggestedPointResponse toResponse(ImagesSuggestedPoint e) {
        if (e == null) return null;

        return ImageSuggestedPointResponse.builder()
                .idImageSuggested(e.getIdImageSuggested())
                .url(e.getUrl())
                .suggestedPointName(e.getSuggestedPoint() != null ? e.getSuggestedPoint().getName() : null)
                .build();
    }

    public static void copyToEntity(ImagesSuggestedPoint e, ImageSuggestedPointRequest r) {
        if (e == null || r == null) return;

        e.setUrl(r.getUrl());
        e.setSuggestedPoint(SuggestedPoint.builder().idSuggestedPoint(r.getSuggestedPointId()).build());
    }
}
