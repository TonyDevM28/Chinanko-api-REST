package chinanko.chinanko.model;

import java.util.List;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Builder
@Table(name = "TYPES_OF_OPINIONS")
public class TypeOfOpinion {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "pk_id_type_opinion")
    private Integer idTypeOpinion;

    @Column(name = "polarithy") // Ej: "positive", "neutral", "negative"
    private String polarithy;

    @OneToMany(mappedBy = "typeOfOpinion", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<OpinionInterestPoint> opinions;
}