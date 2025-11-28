package chinanko.chinanko.dto;

import com.fasterxml.jackson.annotation.JsonProperty;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Builder;
import lombok.Data;

/**
 * Data Transfer Object (DTO) for handling incoming requests related to a Report Profile User.
 * It uses Jakarta Validation annotations to ensure data integrity and
 * Jackson annotations for mapping JSON properties.
 */
@Data // Lombok: Automatically generates getters, setters, toString(), equals(), and hashCode().
@Builder // Lombok: Implements the Builder pattern for easier object instantiation.
public class ReportProfileUserRequest {

    /**
     * The description of the report.
     * - @NotBlank: Ensures the description is not null and contains text.
     * - @Size: Restricts the description length (assumed max 255, adjust if your DB allows more).
     * - @JsonProperty: Maps the JSON property "description" to this field.
     */
    @NotBlank(message = "The description cannot be empty.")
    @Size(max = 255, message = "The description cannot exceed 255 characters.")
    @JsonProperty("description")
    private String description;

    /**
     * The foreign key ID linking this report to a specific Type of Report.
     * - @NotNull: This field is mandatory to classify the report.
     * - @JsonProperty: Maps the JSON property "Type of report Id" to this field.
     */
    @NotNull(message = "The type of report ID cannot be null.")
    @JsonProperty("Type of report Id")
    private Integer typeOfReportId;

    /**
     * The foreign key ID linking this report to a specific Profile User.
     * - @NotNull: This field is mandatory to associate the report with a user profile.
     * - @JsonProperty: Maps the JSON property "Profile user Id" to this field.
     */
    @NotNull(message = "The profile user ID cannot be null.")
    @JsonProperty("Profile user Id")
    private Integer profileUserId;
}