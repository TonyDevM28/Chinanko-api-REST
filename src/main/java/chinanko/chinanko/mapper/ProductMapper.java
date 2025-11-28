package chinanko.chinanko.mapper;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Component;

import chinanko.chinanko.dto.ProductRequest;
import chinanko.chinanko.dto.ProductResponse;
import chinanko.chinanko.model.Catalog;
import chinanko.chinanko.model.PictureProduct;
import chinanko.chinanko.model.Product;
import chinanko.chinanko.model.TypeOfProduct;

@Component
public class ProductMapper {

    public static Product toEntity(ProductRequest r) {
        if (r == null) return null;

        Product product = Product.builder()
                .name(r.getName())
                .price(r.getPrice())
                .description(r.getDescription())
                .stock(r.getStock())
                .catalog(Catalog.builder().idCatalog(r.getCatalogId()).build())
                // Asumimos constructor adecuado en TypeOfProduct o usamos Builder si está disponible
                .typeOfProduct(TypeOfProduct.builder().idTypeProduct(r.getTypeOfProductId()).build()) 
                .build();

        // Mapeo de Imágenes (Creación en Cascada)
        if (r.getImages() != null && !r.getImages().isEmpty()) {
            List<PictureProduct> images = r.getImages().stream()
                    .map(PictureProductMapper::toEntity)
                    .collect(Collectors.toList());
            
            // VITAL: Asignar la relación bidireccional
            images.forEach(img -> img.setProduct(product));
            
            product.setPictureProducts(images);
        }

        return product;
    }

    public static ProductResponse toResponse(Product e) {
        if (e == null) return null;

        return ProductResponse.builder()
                .idProduct(e.getIdProduct())
                .name(e.getName())
                .price(e.getPrice())
                .description(e.getDescription())
                .stock(e.getStock())
                .catalogName(e.getCatalog() != null ? e.getCatalog().getNameCatalog() : null)
                .typeOfProduct(e.getTypeOfProduct() != null ? e.getTypeOfProduct().getType() : null)
                // Mapeo de la lista de imágenes a respuesta
                .images(e.getPictureProducts() != null 
                        ? e.getPictureProducts().stream().map(PictureProductMapper::toResponse).collect(Collectors.toList())
                        : null)
                .build();
    }

    public static void copyToEntity(Product e, ProductRequest r) {
        if (e == null || r == null) return;

        e.setName(r.getName());
        e.setPrice(r.getPrice());
        e.setDescription(r.getDescription());
        e.setStock(r.getStock());
        e.setCatalog(Catalog.builder().idCatalog(r.getCatalogId()).build());
        e.setTypeOfProduct(TypeOfProduct.builder().idTypeProduct(r.getTypeOfProductId()).build());

        // Lógica de Actualización de Imágenes (Reemplazo completo si se envía la lista)
        if (r.getImages() != null) {
            // 1. Limpiar la lista actual (orphanRemoval eliminará los registros de BD)
            if (e.getPictureProducts() != null) {
                e.getPictureProducts().clear();
            } else {
                e.setPictureProducts(new ArrayList<>());
            }

            // 2. Crear las nuevas entidades
            List<PictureProduct> newImages = r.getImages().stream()
                    .map(PictureProductMapper::toEntity)
                    .collect(Collectors.toList());

            // 3. VITAL: Asignar el producto padre
            newImages.forEach(img -> img.setProduct(e));

            // 4. Agregar a la lista gestionada por Hibernate
            e.getPictureProducts().addAll(newImages);
        }
    }
}