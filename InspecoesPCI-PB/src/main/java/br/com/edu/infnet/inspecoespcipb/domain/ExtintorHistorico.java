package br.com.edu.infnet.inspecoespcipb.domain;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;
import java.time.Year;
import java.time.YearMonth;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class ExtintorHistorico {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private int numeroControleInterno;
    private String numeroCilindro;
    private String numeroSeloInmetro;
    private String cargaEsxtintora;
    private String capacidade;
    private YearMonth dataVencimento;
    private Year proximoTesteHidrostatico;

    private LocalDateTime dataAlteracao;
    private String usuarioAlteracao;
    private String tipoOperacao; // CREATE, UPDATE, DELETE

    @ManyToOne
    @JoinColumn(name = "extintor_id")
    private Extintor extintor;

    public ExtintorHistorico(Extintor extintor, String tipoOperacao) {
        this.numeroControleInterno = extintor.getNumeroControleInterno();
        this.numeroCilindro = extintor.getNumeroCilindro();
        this.numeroSeloInmetro = extintor.getNumeroSeloInmetro();
        this.cargaEsxtintora = extintor.getCargaEsxtintora();
        this.capacidade = extintor.getCapacidade();
        this.dataVencimento = extintor.getDataVencimento();
        this.proximoTesteHidrostatico = extintor.getProximoTesteHidrostatico();
        this.dataAlteracao = LocalDateTime.now();
        this.usuarioAlteracao = usuarioAlteracao;
        this.tipoOperacao = tipoOperacao;
        this.extintor = extintor;
    }
}
