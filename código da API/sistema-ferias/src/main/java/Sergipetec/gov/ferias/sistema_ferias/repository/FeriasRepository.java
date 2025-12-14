package Sergipetec.gov.ferias.sistema_ferias.repository;

import Sergipetec.gov.ferias.sistema_ferias.model.Ferias;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import java.util.List;
import java.util.Optional;

@Repository
public interface FeriasRepository extends JpaRepository<Ferias, Integer> {

    List<Ferias> findByServidorIdServidorOrderByDataInicioDesc(Integer servidorId);

    @Query("SELECT f FROM Ferias f LEFT JOIN FETCH f.pagamento " +
            "LEFT JOIN FETCH f.historicoStatus WHERE f.idFerias = :id")
    Optional<Ferias> findByIdWithDetails(Integer id);
}