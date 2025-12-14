package Sergipetec.gov.ferias.sistema_ferias.repository;

import Sergipetec.gov.ferias.sistema_ferias.model.Servidor;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ServidorRepository extends JpaRepository<Servidor, Integer> {
}