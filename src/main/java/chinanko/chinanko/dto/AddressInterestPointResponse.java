package chinanko.chinanko.dto;

import java.math.BigDecimal;

import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class AddressInterestPointResponse {

    @JsonProperty("id_address_interest_point")
    private Integer idAddressInterest;

    @JsonProperty("street")
    private String street;

    @JsonProperty("exterior_number")
    private String exteriorNumber;

    @JsonProperty("interior_number")
    private String interiorNumber;

    @JsonProperty("neighborhood")
    private String neighborhood;

    @JsonProperty("postal_code")
    private String postalCode;

    private BigDecimal latitude;
    private BigDecimal longitude;

    @JsonProperty("interest_point_name")
    private String interestPointName;
}