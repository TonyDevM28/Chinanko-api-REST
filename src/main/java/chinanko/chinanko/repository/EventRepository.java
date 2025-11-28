package chinanko.chinanko.repository;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import chinanko.chinanko.model.Event;

@Repository
public interface EventRepository extends JpaRepository<Event, Integer> {

    // 1. Obtener por Tipo de Evento y Pueblo (Paginado)
    Page<Event> findByTown_IdTownAndTypeOfEvent_IdTypeEvent(Integer idTown, Integer idTypeEvent, Pageable pageable);

    // 2. Obtener por Dirección (Código Postal) - Usamos Distinct para evitar duplicados si un evento tiene multiples direcciones con mismo CP
    List<Event> findDistinctByAddressEvent_PostalCode(String postalCode);

    // 3. Obtener por Dirección (Calle) - Búsqueda parcial
    List<Event> findDistinctByAddressEvent_StreetContainingIgnoreCase(String street);

    // 4. Obtener por Pueblo (Paginado)
    Page<Event> findByTown_IdTown(Integer townId, Pageable pageable);

    // 5. Obtener por Estado de Evento y Pueblo (Paginado)
    Page<Event> findByTown_IdTownAndStateOfEvent_IdStateEvent(Integer townId, Integer stateId, Pageable pageable);
}