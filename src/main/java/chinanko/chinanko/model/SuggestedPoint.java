package chinanko.chinanko.model;

import java.math.BigDecimal;
import java.util.List;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
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
@Table(name = "SUGGESTED_POINTS")
public class SuggestedPoint {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "pk_id_suggested_point")
    private Integer idSuggestedPoint;

    @Column(name = "name_suggested_point")
    private String name;

    @Column(name = "description") // Se mantiene por consistencia
    private String description;

    @Column(name = "longitude")
    private BigDecimal longitude;

    @Column(name = "latitude")
    private BigDecimal latitude;

    // Relaciones
    
    // Gestión en cascada de imágenes: orphanRemoval=true es vital para updates
    @OneToMany(mappedBy = "suggestedPoint", cascade = CascadeType.ALL, fetch = FetchType.LAZY, orphanRemoval = true)
    private List<ImagesSuggestedPoint> imagesSuggestedPoint;

    @OneToMany(mappedBy = "suggestedPoint", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<OpinionsSuggestedPoints> opinionsSuggestedPoints;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "fk_type_of_suggested_point")
    private TypeOfSuggestedPoint typeOfSuggestedPoint;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "fk_id_state_suggested_point")
    private StateSuggestedPoint stateSuggestedPoint;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "fk_id_town")
    private Town town;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "fk_id_profile_user")
    private ProfileUser profileUser;
}