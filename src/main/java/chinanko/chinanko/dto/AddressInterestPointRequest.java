package chinanko.chinanko.dto;

import java.math.BigDecimal;

import com.fasterxml.jackson.annotation.JsonProperty;

import jakarta.validation.constraints.DecimalMax;
import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.Digits;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class AddressInterestPointRequest {

    @NotBlank(message = "The street name cannot be empty.")
    @Size(max = 150, message = "The street name cannot exceed 150 characters.")
    @JsonProperty("street")
    private String street;

    @NotBlank(message = "Exterior number is required.")
    @Size(max = 20, message = "Exterior number too long.")
    @JsonProperty("exterior_number")
    private String exteriorNumber;

    @Size(max = 20, message = "Interior number too long.")
    @JsonProperty("interior_number")
    private String interiorNumber;

    @NotBlank(message = "Neighborhood is required.")
    @Size(max = 100, message = "Neighborhood name too long.")
    @JsonProperty("neighborhood")
    private String neighborhood;

    @NotBlank(message = "Postal code is required.")
    @Size(max = 10, message = "Postal code too long.")
    @JsonProperty("postal_code")
    private String postalCode;

    @NotNull(message = "Latitude is required.")
    @DecimalMin(value = "-90.0")
    @DecimalMax(value = "90.0")
    @Digits(integer = 3, fraction = 8)
    private BigDecimal latitude;

    @NotNull(message = "Longitude is required.")
    @DecimalMin(value = "-180.0")
    @DecimalMax(value = "180.0")
    @Digits(integer = 3, fraction = 8)
    private BigDecimal longitude;

    // Opcional para permitir creación anidada (sin @NotNull)
    @JsonProperty("interest_point_id")
    private Integer interestPointId;
}