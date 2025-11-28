package chinanko.chinanko.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import chinanko.chinanko.model.StateOfEvent;

@Repository
public interface StateOfEventRepository extends JpaRepository<StateOfEvent, Integer> {

    /**
     * Busca un estado de evento por su nombre.
     */
    Optional<StateOfEvent> findByState(String state);
}