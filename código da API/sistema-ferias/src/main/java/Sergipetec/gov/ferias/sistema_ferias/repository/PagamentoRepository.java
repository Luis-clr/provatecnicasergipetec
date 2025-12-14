package Sergipetec.gov.ferias.sistema_ferias.repository;

import Sergipetec.gov.ferias.sistema_ferias.model.Pagamento;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PagamentoRepository extends JpaRepository<Pagamento, Integer> {
}