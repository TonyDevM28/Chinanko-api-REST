package chinanko.chinanko.mapper;

import org.springframework.stereotype.Component;

import chinanko.chinanko.dto.PictureProductRequest;
import chinanko.chinanko.dto.PictureProductResponse;
import chinanko.chinanko.model.PictureProduct;
import chinanko.chinanko.model.Product;

@Component
public class PictureProductMapper {

    public static PictureProduct toEntity(PictureProductRequest r) {
        if (r == null) return null;

        return PictureProduct.builder()
                .url(r.getUrl())
                // Si viene el ID, lo usamos. Si no (cascada), se asignará en el mapper del padre.
                .product(r.getProductId() != null ? Product.builder().idProduct(r.getProductId()).build() : null)
                .build();
    }

    public static PictureProductResponse toResponse(PictureProduct e) {
        if (e == null) return null;

        return PictureProductResponse.builder()
                .idPictureProduct(e.getIdPictureProduct())
                .url(e.getUrl())
                .productName(e.getProduct() != null ? e.getProduct().getName() : null)
                .build();
    }

    public static void copyToEntity(PictureProduct e, PictureProductRequest r) {
        if (e == null || r == null) return;

        e.setUrl(r.getUrl());
        if (r.getProductId() != null) {
            e.setProduct(Product.builder().idProduct(r.getProductId()).build());
        }
    }
}