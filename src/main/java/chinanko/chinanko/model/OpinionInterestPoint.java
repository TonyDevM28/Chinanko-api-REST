package chinanko.chinanko.model;

import java.math.BigDecimal;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
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
@Table(name = "OPINIONS_INTEREST_POINTS")
public class OpinionInterestPoint {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "pk_id_interest_point")
    private Integer idOpinionInterestPoint;

    @Column(name = "opinion", length = 1000)
    private String opinion;

    @Column(name = "polarity")
    private BigDecimal polarity; // Guardaremos el score de confianza (0.0 a 1.0)

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "fk_id_type_of_opinion")
    private TypeOfOpinion typeOfOpinion;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "fk_id_interest_point")
    private InterestPoint interestPoint;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "fk_id_user")
    private ProfileUser profileUser; 
}