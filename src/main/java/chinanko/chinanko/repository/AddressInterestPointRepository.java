package chinanko.chinanko.repository;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import chinanko.chinanko.model.AddressInterestPoint;

@Repository
public interface AddressInterestPointRepository extends JpaRepository<AddressInterestPoint, Integer> {

    // 1. Obtener por código postal (Paginado)
    Page<AddressInterestPoint> findByPostalCode(String postalCode, Pageable pageable);

    // 2. Obtener por pueblo (Paginado)
    // Navegamos: Address -> InterestPoint -> Town -> idTown
    Page<AddressInterestPoint> findByInterestPoint_Town_IdTown(Integer townId, Pageable pageable);

    // 3. Obtener por nombre de calle (búsqueda parcial)
    List<AddressInterestPoint> findByStreetContainingIgnoreCase(String street);
}