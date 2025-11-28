package chinanko.chinanko.mapper;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Component;

import chinanko.chinanko.dto.InterestPointRequest;
import chinanko.chinanko.dto.InterestPointResponse;
import chinanko.chinanko.model.AddressInterestPoint;
import chinanko.chinanko.model.ImageInterestPoint;
import chinanko.chinanko.model.InterestPoint;
import chinanko.chinanko.model.Town;
import chinanko.chinanko.model.TypeOfInterestPoint;

@Component
public class InterestPointMapper {

    public static InterestPoint toEntity(InterestPointRequest r) {
        if (r == null) return null;

        InterestPoint interestPoint = InterestPoint.builder()
                .nameInterestPoint(r.getNameInterestPoint())
                .description(r.getDescription())
                .typeOfInterestPoint(TypeOfInterestPoint.builder().idTypeInterestPoint(r.getTypeOfInterestPointId()).build())
                .town(Town.builder().idTown(r.getTownId()).build())
                .build();

        // --- Mapeo de Dirección (Creación) ---
        if (r.getAddress() != null) {
            AddressInterestPoint address = AddressInterestPointMapper.toEntity(r.getAddress());
            // Establecer relación bidireccional (Importante para CascadeType.ALL)
            address.setInterestPoint(interestPoint); 
            interestPoint.setAddressInterestPoint(address);
        }

        // --- Mapeo de Imágenes (Creación) ---
        if (r.getImages() != null && !r.getImages().isEmpty()) {
            List<ImageInterestPoint> images = r.getImages().stream()
                    .map(ImageInterestPointMapper::toEntity)
                    .collect(Collectors.toList());
            
            // Establecer relación bidireccional para cada imagen
            images.forEach(img -> img.setInterestPoint(interestPoint));
            
            interestPoint.setImages(images);
        }

        return interestPoint;
    }

    public static InterestPointResponse toResponse(InterestPoint e) {
        if (e == null) return null;

        return InterestPointResponse.builder()
                .idInterestPoint(e.getIdInterestPoint())
                .nameInterestPoint(e.getNameInterestPoint())
                .description(e.getDescription())
                .typeOfInterestPoint(e.getTypeOfInterestPoint() != null ? e.getTypeOfInterestPoint().getNameTypeInterestPoint() : null)
                .townName(e.getTown() != null ? e.getTown().getNameTown() : null)
                
                // Respuesta de Dirección
                .address(e.getAddressInterestPoint() != null 
                        ? AddressInterestPointMapper.toResponse(e.getAddressInterestPoint()) 
                        : null)
                
                // Respuesta de Imágenes
                .images(e.getImages() != null 
                        ? e.getImages().stream().map(ImageInterestPointMapper::toResponse).collect(Collectors.toList())
                        : null)
                .build();
    }

    public static void copyToEntity(InterestPoint e, InterestPointRequest r) {
        if (e == null || r == null) return;

        e.setNameInterestPoint(r.getNameInterestPoint());
        e.setDescription(r.getDescription());
        e.setTypeOfInterestPoint(TypeOfInterestPoint.builder().idTypeInterestPoint(r.getTypeOfInterestPointId()).build());
        e.setTown(Town.builder().idTown(r.getTownId()).build());

        // --- Actualización de Dirección ---
        if (r.getAddress() != null) {
            if (e.getAddressInterestPoint() == null) {
                // Crear nueva si no existe
                AddressInterestPoint newAddress = AddressInterestPointMapper.toEntity(r.getAddress());
                newAddress.setInterestPoint(e);
                e.setAddressInterestPoint(newAddress);
            } else {
                // Actualizar existente
                AddressInterestPointMapper.copyToEntity(e.getAddressInterestPoint(), r.getAddress());
                e.getAddressInterestPoint().setInterestPoint(e);
            }
        }

        // --- Actualización de Imágenes ---
        if (r.getImages() != null) {
            // 1. Limpiar lista actual (orphanRemoval: true eliminará los registros de BD)
            if (e.getImages() != null) {
                e.getImages().clear();
            } else {
                e.setImages(new ArrayList<>());
            }

            // 2. Crear nuevas entidades
            List<ImageInterestPoint> newImages = r.getImages().stream()
                    .map(ImageInterestPointMapper::toEntity)
                    .collect(Collectors.toList());

            // 3. Asignar padre
            newImages.forEach(img -> img.setInterestPoint(e));

            // 4. Agregar a la colección gestionada
            e.getImages().addAll(newImages);
        }
    }
}