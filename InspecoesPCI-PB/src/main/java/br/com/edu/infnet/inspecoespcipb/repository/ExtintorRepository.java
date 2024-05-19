package br.com.edu.infnet.inspecoespcipb.repository;

import br.com.edu.infnet.inspecoespcipb.domain.Extintor;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface ExtintorRepository extends JpaRepository<Extintor, Integer> {
    Optional<Extintor> findByNumeroControleInterno(int numeroControleInterno);
}
