package br.com.edu.infnet.inspecoespcipb.controller;

import br.com.edu.infnet.inspecoespcipb.domain.Usuario;
import br.com.edu.infnet.inspecoespcipb.dto.UsuarioDTO;
import br.com.edu.infnet.inspecoespcipb.service.UsuarioService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Collection;

@RestController
@RequestMapping("/usuarios")
class UsuarioController {

    @Autowired
    private UsuarioService usuarioService;

    @GetMapping
    @Operation(summary = "Obter todos os usuários", description = "Retorna uma coleção de todos os usuários")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Operação bem-sucedida"),
            @ApiResponse(responseCode = "400", description = "Requisição inválida")
    })
    public ResponseEntity<?> getAll() {
        Collection<Usuario> usuarios = usuarioService.getAll();
        return new ResponseEntity<>(usuarios, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    @Operation(summary = "Obter usuário por ID", description = "Retorna um usuário pelo seu ID")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Operação bem-sucedida"),
            @ApiResponse(responseCode = "404", description = "Usuário não encontrado")
    })
    public ResponseEntity<?> getById(@PathVariable int id) {
        try {
            Usuario usuario = usuarioService.getById(id);
            return new ResponseEntity<>(usuario, HttpStatus.OK);
        } catch (IllegalArgumentException ex) {
            return new ResponseEntity<>(ex.getMessage(), HttpStatus.NOT_FOUND);
        }
    }

    @PostMapping
    @Operation(summary = "Salvar um novo usuário", description = "Adiciona um novo usuário")
    @ApiResponses({
            @ApiResponse(responseCode = "201", description = "Usuário incluído com sucesso"),
            @ApiResponse(responseCode = "409", description = "Conflito ao incluir usuário")
    })
    public ResponseEntity<String> save(@RequestBody UsuarioDTO usuarioDTO) {
        try {
            usuarioService.add(usuarioDTO);
            return new ResponseEntity<>("Usuário incluído com sucesso!", HttpStatus.CREATED);
        } catch (IllegalArgumentException ex) {
            return new ResponseEntity<>(ex.getMessage(), HttpStatus.CONFLICT);
        }
    }

    @DeleteMapping("/{id}")
    @Operation(summary = "Excluir usuário por ID", description = "Exclui um usuário pelo seu ID")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Usuário excluído com sucesso"),
            @ApiResponse(responseCode = "404", description = "Usuário não encontrado")
    })
    public ResponseEntity<String> deleteById(@PathVariable int id) {
        try {
            usuarioService.deleteById(id);
            return new ResponseEntity<>("Usuário excluído com sucesso!", HttpStatus.OK);
        } catch (IllegalArgumentException ex) {
            return new ResponseEntity<>(ex.getMessage(), HttpStatus.NOT_FOUND);
        }
    }

    @PutMapping("/{id}")
    @Operation(summary = "Atualizar usuário por ID", description = "Atualiza um usuário pelo seu ID")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Usuário atualizado com sucesso"),
            @ApiResponse(responseCode = "404", description = "Usuário não encontrado")
    })
    public ResponseEntity<String> update(@PathVariable int id, @RequestBody UsuarioDTO usuarioDTO) {
        try {
            usuarioService.update(id, usuarioDTO);
            return new ResponseEntity<>("Usuário atualizado com sucesso!", HttpStatus.OK);
        } catch (IllegalArgumentException ex) {
            return new ResponseEntity<>(ex.getMessage(), HttpStatus.NOT_FOUND);
        }
    }
}
