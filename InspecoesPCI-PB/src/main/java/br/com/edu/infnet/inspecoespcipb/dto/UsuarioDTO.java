package br.com.edu.infnet.inspecoespcipb.dto;

import lombok.Data;

@Data
public class UsuarioDTO {
    private String nome;
    private String email;
    private String senha;
    private boolean isAdmin;
}