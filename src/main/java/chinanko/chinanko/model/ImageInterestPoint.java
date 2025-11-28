package chinanko.chinanko.model;

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
@Table(name = "IMAGES_INTEREST_POINT")
public class ImageInterestPoint {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "pk_id_image_interest_point")
    private Integer idImageInterestPoint;

    @Column(name = "url", nullable = false)
    private String url;

    // Relación Muchos a Uno con InterestPoint
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "fk_id_interest_point", nullable = false)
    private InterestPoint interestPoint;
}