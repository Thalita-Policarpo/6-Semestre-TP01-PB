package br.com.edu.infnet.inspecoespcipb.domain;

import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;
import lombok.*;

import java.time.Year;
import java.time.YearMonth;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class Extintor {

    @Id
    @GeneratedValue (strategy = GenerationType.IDENTITY)
    private Integer id;
    private int numeroControleInterno;
    private String numeroCilindro;
    private String numeroSeloInmetro;
    private String cargaEsxtintora;
    private String capacidade;
    private YearMonth dataVencimento; // apenas mes e ano
    private Year proximoTesteHidrostatico; // apena o ano

    @OneToMany(mappedBy = "extintor", cascade = CascadeType.ALL, fetch = FetchType.LAZY )
    @JsonBackReference
    private List<InspecaoExtintor> inspecoes;

    public Extintor(int numeroControleInterno, String numeroCilindro, String numeroSeloInmetro, String cargaEsxtintora, String capacidade, YearMonth dataVencimento, Year proximoTesteHidrostatico) {
        this.id = id;
        this.numeroControleInterno = numeroControleInterno;
        this.numeroCilindro = numeroCilindro;
        this.numeroSeloInmetro = numeroSeloInmetro;
        this.cargaEsxtintora = cargaEsxtintora;
        this.capacidade = capacidade;
        this.dataVencimento = dataVencimento;
        this.proximoTesteHidrostatico = proximoTesteHidrostatico;
    }
}
