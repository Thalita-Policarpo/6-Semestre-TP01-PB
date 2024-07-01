package br.com.edu.infnet.inspecoespcipb.dto;

import lombok.Data;

@Data
public class InspecaoExtintorDTO {
    private int extintorId;
    private int usuarioId;
    private boolean sinalizado;
    private boolean desobstruido;
    private boolean manometroPressaoAdequada;
    private boolean gatilhoBoasCondicoes;
    private boolean mangoteBoasCondicoes;
    private boolean rotuloPinturaBoasCondicoes;
    private boolean suporteBoasCondicoes;
    private boolean lacreIntacto;
}