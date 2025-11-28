package chinanko.chinanko.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Builder
@Table(name = "STATES_SUGGESTED_POINT")
public class StateSuggestedPoint {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "pk_id_state_suggested_point")
    private Integer idStateSuggested;

    // Configuración: String, Max 50 chars, Solo letras y espacios
    @Column(name = "state", nullable = false, length = 50)
    @Size(max = 50, message = "The state name cannot exceed 50 characters.")
    @Pattern(regexp = "^[\\p{L} ]+$", message = "The state can only contain letters and spaces.")
    private String state;
}