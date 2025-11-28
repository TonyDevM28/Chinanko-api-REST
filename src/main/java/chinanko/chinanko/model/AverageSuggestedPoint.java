package chinanko.chinanko.model;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn; // Importante
import jakarta.persistence.OneToOne;   // Importante
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "AVERAGE_SUGGESTED_POINTS")
public class AverageSuggestedPoint {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "pk_id_average_suggested_point")
    private Integer idAverage;

    @Column(name = "polarity")
    private String polarity;

    // CAMBIOS AQUÍ:
    // 1. @OneToOne en lugar de @OneToMany
    // 2. @JoinColumn define que ESTA tabla tendrá la columna con la FK
    @OneToOne(cascade = CascadeType.ALL) 
    @JoinColumn(name = "fk_id_suggested_point", referencedColumnName = "pk_id_suggested_point") // Ajusta "pk_..." al ID real de la otra tabla
    private SuggestedPoint suggestedPoint; // Ya no es una List
}