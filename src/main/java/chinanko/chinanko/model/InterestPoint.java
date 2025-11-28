package chinanko.chinanko.model;

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
import jakarta.persistence.OneToOne;
import jakarta.persistence.PrimaryKeyJoinColumn;
import jakarta.persistence.Table;
import jakarta.persistence.UniqueConstraint;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Builder
@Table(name = "INTEREST_POINTS", uniqueConstraints = {
    @UniqueConstraint(columnNames = {"name_interest_point", "fk_id_town"})
})
public class InterestPoint {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "pk_id_interest_point")
    private Integer idInterestPoint;

    @Column(name = "name_interest_point", nullable = false)
    private String nameInterestPoint;

    @Column(name = "description")
    private String description;

    @OneToMany(mappedBy = "interestPoint", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<OpinionInterestPoint> opinionInterestPoint;

    @OneToOne(mappedBy = "interestPoint", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private AverageInterestPoint averageInterestPoint;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "fk_id_type_of_interest_point", nullable = false)
    private TypeOfInterestPoint typeOfInterestPoint;

    @OneToOne(mappedBy = "interestPoint", cascade = CascadeType.ALL, fetch = FetchType.LAZY, orphanRemoval = true)
    private AddressInterestPoint addressInterestPoint;

    @OneToMany(mappedBy = "interestPoint", cascade = CascadeType.ALL, fetch = FetchType.LAZY, orphanRemoval = true)
    private List<ImageInterestPoint> images;

    // Relación bidireccional con Catalog
    @OneToOne(mappedBy = "interestPoint", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private Catalog catalog; 

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "fk_id_town", nullable = false)
    private Town town;
}