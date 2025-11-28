package chinanko.chinanko.mapper;

import org.springframework.stereotype.Component;

import chinanko.chinanko.dto.AddressInterestPointRequest;
import chinanko.chinanko.dto.AddressInterestPointResponse;
import chinanko.chinanko.model.AddressInterestPoint;
import chinanko.chinanko.model.InterestPoint;

@Component
public class AddressInterestPointMapper {

    public static AddressInterestPoint toEntity(AddressInterestPointRequest r) {
        if (r == null) return null;

        AddressInterestPoint entity = new AddressInterestPoint();
        entity.setStreet(r.getStreet());
        // Mapeo a los nombres de variables específicos de tu entidad
        entity.setExteriorNumbre(r.getExteriorNumber()); 
        entity.setInteriorNumber(r.getInteriorNumber());
        entity.setNeigborhood(r.getNeighborhood());
        entity.setPostalCode(r.getPostalCode());
        entity.setLatitude(r.getLatitude());
        entity.setLongitude(r.getLongitude());
        
        // Relación
        entity.setInterestPoint(InterestPoint.builder().idInterestPoint(r.getInterestPointId()).build());

        return entity;
    }

    public static AddressInterestPointResponse toResponse(AddressInterestPoint e) {
        if (e == null) return null;

        return AddressInterestPointResponse.builder()
                .idAddressInterest(e.getIdAddressInterest())
                .street(e.getStreet())
                .exteriorNumber(e.getExteriorNumbre())
                .interiorNumber(e.getInteriorNumber())
                .neighborhood(e.getNeigborhood())
                .postalCode(e.getPostalCode())
                .latitude(e.getLatitude())
                .longitude(e.getLongitude())
                .interestPointName(e.getInterestPoint() != null ? e.getInterestPoint().getNameInterestPoint() : null)
                .build();
    }

    public static void copyToEntity(AddressInterestPoint e, AddressInterestPointRequest r) {
        if (e == null || r == null) return;

        e.setStreet(r.getStreet());
        e.setExteriorNumbre(r.getExteriorNumber());
        e.setInteriorNumber(r.getInteriorNumber());
        e.setNeigborhood(r.getNeighborhood());
        e.setPostalCode(r.getPostalCode());
        e.setLatitude(r.getLatitude());
        e.setLongitude(r.getLongitude());
        e.setInterestPoint(InterestPoint.builder().idInterestPoint(r.getInterestPointId()).build());
    }
}