package br.com.edu.infnet.inspecoespcipb.repository;

import br.com.edu.infnet.inspecoespcipb.domain.ExtintorHistorico;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ExtintorHistoricoRepository extends JpaRepository<ExtintorHistorico, Integer> {
    List<ExtintorHistorico> findByNumeroControleInterno(int numeroControleInterno);
}
