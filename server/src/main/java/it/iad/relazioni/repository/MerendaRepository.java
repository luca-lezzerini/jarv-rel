package it.iad.relazioni.repository;

import it.iad.relazioni.model.Merenda;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MerendaRepository extends JpaRepository<Merenda, Long>{

}
