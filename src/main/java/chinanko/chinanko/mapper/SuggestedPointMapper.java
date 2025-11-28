package chinanko.chinanko.mapper;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Component;

import chinanko.chinanko.dto.SuggestedPointRequest;
import chinanko.chinanko.dto.SuggestedPointResponse;
import chinanko.chinanko.model.ImagesSuggestedPoint;
import chinanko.chinanko.model.ProfileUser;
import chinanko.chinanko.model.StateSuggestedPoint;
import chinanko.chinanko.model.SuggestedPoint;
import chinanko.chinanko.model.Town;
import chinanko.chinanko.model.TypeOfSuggestedPoint;

@Component
public class SuggestedPointMapper {

    public static SuggestedPoint toEntity(SuggestedPointRequest r) {
        if (r == null) return null;

        SuggestedPoint point = SuggestedPoint.builder()
                .name(r.getName())
                .description(r.getDescription())
                .longitude(r.getLongitude())
                .latitude(r.getLatitude())
                .typeOfSuggestedPoint(TypeOfSuggestedPoint.builder().idTypeSuggested(r.getTypeId()).build())
                .stateSuggestedPoint(StateSuggestedPoint.builder().idStateSuggested(r.getStateId()).build())
                .town(Town.builder().idTown(r.getTownId()).build())
                .profileUser(ProfileUser.builder().idProfileUser(r.getUserId()).build())
                .build();

        // Mapeo de Imágenes en Cascada
        if (r.getImages() != null && !r.getImages().isEmpty()) {
            List<ImagesSuggestedPoint> images = r.getImages().stream()
                    .map(ImagesSuggestedPointMapper::toEntity)
                    .collect(Collectors.toList());
            
            // Asignar relación bidireccional
            images.forEach(img -> img.setSuggestedPoint(point));
            
            point.setImagesSuggestedPoint(images);
        }

        return point;
    }

    public static SuggestedPointResponse toResponse(SuggestedPoint e) {
        if (e == null) return null;

        return SuggestedPointResponse.builder()
                .idSuggestedPoint(e.getIdSuggestedPoint())
                .name(e.getName())
                .description(e.getDescription())
                .longitude(e.getLongitude())
                .latitude(e.getLatitude())
                .typeName(e.getTypeOfSuggestedPoint() != null ? e.getTypeOfSuggestedPoint().getName() : null)
                // Asumiendo que StateSuggestedPoint tiene un campo 'state' (String/Boolean)
                .stateName(e.getStateSuggestedPoint() != null ? e.getStateSuggestedPoint().getState() : null) 
                .townName(e.getTown() != null ? e.getTown().getNameTown() : null)
                .userName(e.getProfileUser() != null ? e.getProfileUser().getFirstName() : null)
                .images(e.getImagesSuggestedPoint() != null 
                        ? e.getImagesSuggestedPoint().stream().map(ImagesSuggestedPointMapper::toResponse).collect(Collectors.toList()) 
                        : null)
                .build();
    }

    public static void copyToEntity(SuggestedPoint e, SuggestedPointRequest r) {
        if (e == null || r == null) return;

        e.setName(r.getName());
        e.setDescription(r.getDescription());
        e.setLongitude(r.getLongitude());
        e.setLatitude(r.getLatitude());
        
        // Actualizar FKs
        e.setTypeOfSuggestedPoint(TypeOfSuggestedPoint.builder().idTypeSuggested(r.getTypeId()).build());
        e.setStateSuggestedPoint(StateSuggestedPoint.builder().idStateSuggested(r.getStateId()).build());
        e.setTown(Town.builder().idTown(r.getTownId()).build());
        // e.setProfileUser(...) // Generalmente no se cambia el creador

        // Actualización de Imágenes (Reemplazo)
        if (r.getImages() != null) {
            if (e.getImagesSuggestedPoint() != null) {
                e.getImagesSuggestedPoint().clear();
            } else {
                e.setImagesSuggestedPoint(new ArrayList<>());
            }

            List<ImagesSuggestedPoint> newImages = r.getImages().stream()
                    .map(ImagesSuggestedPointMapper::toEntity)
                    .collect(Collectors.toList());

            newImages.forEach(img -> img.setSuggestedPoint(e));
            e.getImagesSuggestedPoint().addAll(newImages);
        }
    }
}