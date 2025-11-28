package chinanko.chinanko.mapper;

import org.springframework.stereotype.Component;

import chinanko.chinanko.dto.TypeOfProductRequest;
import chinanko.chinanko.dto.TypeOfProductResponse;
import chinanko.chinanko.model.TypeOfProduct;

@Component
public class TypeOfProductMapper {

    public static TypeOfProduct toEntity(TypeOfProductRequest r) {
        if (r == null) return null;

        // Nota: Asegúrate de agregar @Builder en tu entidad TypeOfProduct si no lo tiene
        return new TypeOfProduct(null, r.getType(), null);
    }

    public static TypeOfProductResponse toResponse(TypeOfProduct e) {
        if (e == null) return null;

        return TypeOfProductResponse.builder()
                .idTypeProduct(e.getIdTypeProduct())
                .type(e.getType())
                .build();
    }

    public static void copyToEntity(TypeOfProduct e, TypeOfProductRequest r) {
        if (e == null || r == null) return;
        e.setType(r.getType());
    }
}