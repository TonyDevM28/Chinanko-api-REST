package chinanko.chinanko.mapper;

import org.springframework.stereotype.Component;

import chinanko.chinanko.dto.CatalogRequest;
import chinanko.chinanko.dto.CatalogResponse;
import chinanko.chinanko.model.Catalog;
import chinanko.chinanko.model.InterestPoint;

@Component
public class CatalogMapper {

    public static Catalog toEntity(CatalogRequest r) {
        if (r == null) return null;

        return Catalog.builder()
                .nameCatalog(r.getNameCatalog())
                .description(r.getDescription())
                .interestPoint(InterestPoint.builder().idInterestPoint(r.getInterestPointId()).build())
                .build();
    }

    public static CatalogResponse toResponse(Catalog e) {
        if (e == null) return null;

        return CatalogResponse.builder()
                .idCatalog(e.getIdCatalog())
                .nameCatalog(e.getNameCatalog())
                .description(e.getDescription())
                .interestPointName(e.getInterestPoint() != null ? e.getInterestPoint().getNameInterestPoint() : null)
                .build();
    }

    public static void copyToEntity(Catalog e, CatalogRequest r) {
        if (e == null || r == null) return;

        e.setNameCatalog(r.getNameCatalog());
        e.setDescription(r.getDescription());
        e.setInterestPoint(InterestPoint.builder().idInterestPoint(r.getInterestPointId()).build());
    }
}