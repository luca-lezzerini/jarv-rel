package it.iad.relazioni.repository;

import it.iad.relazioni.model.Alunno;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AlunnoRepository extends JpaRepository<Alunno, Long>{

}
