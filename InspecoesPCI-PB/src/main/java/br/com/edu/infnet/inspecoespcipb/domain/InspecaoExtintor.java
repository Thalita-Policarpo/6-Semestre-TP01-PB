package br.com.edu.infnet.inspecoespcipb.domain;

import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.time.Year;
import java.time.YearMonth;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class InspecaoExtintor {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private LocalDate dataInspecao;
    private boolean sinalizado;
    private boolean desobstruido;
    private boolean manometroPressaoAdequada;
    private boolean gatilhoBoasCondicoes;
    private boolean mangoteBoasCondicoes;
    private boolean rotuloPinturaBoasCondicoes;
    private boolean suporteBoasCondicoes;
    private boolean lacreIntacto;
    private String status;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "extintor_id")
    @JsonBackReference
    private Extintor extintor;

    public InspecaoExtintor(boolean sinalizado, boolean desobstruido, boolean manometroPressaoAdequada, boolean gatilhoBoasCondicoes, boolean mangoteBoasCondicoes, boolean rotuloPinturaBoasCondicoes, boolean suporteBoasCondicoes, boolean lacreIntacto) {
        this.sinalizado = sinalizado;
        this.desobstruido = desobstruido;
        this.manometroPressaoAdequada = manometroPressaoAdequada;
        this.gatilhoBoasCondicoes = gatilhoBoasCondicoes;
        this.mangoteBoasCondicoes = mangoteBoasCondicoes;
        this.rotuloPinturaBoasCondicoes = rotuloPinturaBoasCondicoes;
        this.suporteBoasCondicoes = suporteBoasCondicoes;
        this.lacreIntacto = lacreIntacto;
    }

    public void setStatus(Extintor extintor) {
        YearMonth dataAtual = YearMonth.now();
        Year anoAtual = Year.now();

        StringBuilder statusBuilder = new StringBuilder();
        if(VerificarConformidadeDoEquipamento(extintor, dataAtual, anoAtual)) {
            statusBuilder.append("Equipamento em conformidade");
        } else {
            statusBuilder.append("Equipamento em Não conformidade");
            appendStatusDetalhes(statusBuilder, extintor, dataAtual, anoAtual);
        }
        this.status = statusBuilder.toString();
    }

    private boolean VerificarConformidadeDoEquipamento(Extintor extintor, YearMonth dataAtual, Year anoAtual) {
        return isDataVencimentoValida(extintor, dataAtual) &&
                isDataTHValida(extintor, anoAtual) &&
                isSinalizado() && isDesobstruido() && isManometroPressaoAdequada() &&
                isGatilhoBoasCondicoes() && isMangoteBoasCondicoes() &&
                isRotuloPinturaBoasCondicoes() && isSuporteBoasCondicoes() && isLacreIntacto();
    }

    private void appendStatusDetalhes(StringBuilder statusBuilder, Extintor extintor, YearMonth dataAtual, Year anoAtual) {
        if(!isSinalizado()) {
            statusBuilder.append(" - Sinalização inadequada");
        }
        if(!isDesobstruido()) {
            statusBuilder.append(" - Equipamento obstruído");
        }
        if(!isMangoteBoasCondicoes()) {
            statusBuilder.append(" - Mangote danificado ou obstruído");
        }
        if(!isGatilhoBoasCondicoes()) {
            statusBuilder.append(" - Gatilho danificado");
        }
        if(!isManometroPressaoAdequada()) {
            statusBuilder.append(" - Pressão inadequada");
        }
        if(!isRotuloPinturaBoasCondicoes()) {
            statusBuilder.append(" - Rótulo ou Pintura inadequada");
        }
        if(!isSuporteBoasCondicoes()) {
            statusBuilder.append(" - Suporte inadequado");
        }
        if(!isLacreIntacto()) {
            statusBuilder.append(" - Lacre inadequado");
        }
        if(!isDataVencimentoValida(extintor, dataAtual)){
            statusBuilder.append(" - Extintor vencido");
        }
        if(!isDataTHValida(extintor, anoAtual)) {
            statusBuilder.append(" - Teste Hidrostático vencido");
        }
    }

    public Boolean isDataVencimentoValida(Extintor extintor, YearMonth dataAtual) {
        return extintor.getDataVencimento().isAfter(dataAtual) || extintor.getDataVencimento().equals(dataAtual);
    }

    public Boolean isDataTHValida(Extintor extintor, Year anoAtual) {
        return extintor.getProximoTesteHidrostatico().isAfter(anoAtual) || extintor.getProximoTesteHidrostatico().equals(anoAtual);
    }
}

