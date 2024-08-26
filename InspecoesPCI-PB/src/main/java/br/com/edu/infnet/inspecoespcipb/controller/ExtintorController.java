package br.com.edu.infnet.inspecoespcipb.controller;

import br.com.edu.infnet.inspecoespcipb.domain.Extintor;
import br.com.edu.infnet.inspecoespcipb.domain.ExtintorHistorico;
import br.com.edu.infnet.inspecoespcipb.dto.ExtintorDTO;
import br.com.edu.infnet.inspecoespcipb.service.ExtintorService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Collection;
import java.util.List;

@RestController
@RequestMapping("/extintor")
public class ExtintorController {


    @Autowired
    private ExtintorService extintorService;

    @GetMapping
    @Operation(summary = "Obter todos os extintores", description = "Retorna uma coleção de todos os extintores")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Operação bem-sucedida"),
            @ApiResponse(responseCode = "404", description = "Nenhum extintor encontrado")
    })
    public ResponseEntity<?> getAll() {
        try {
            Collection<Extintor> extintores = extintorService.getAll();
            return new ResponseEntity<>(extintores, HttpStatus.OK);
        } catch (IllegalArgumentException ex) {
            return new ResponseEntity<>(ex.getMessage(), HttpStatus.NOT_FOUND);
        }
    }

    @GetMapping("/{id}")
    @Operation(summary = "Obter extintor por ID", description = "Retorna um extintor pelo seu ID")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Operação bem-sucedida"),
            @ApiResponse(responseCode = "404", description = "Extintor não encontrado")
    })
    public ResponseEntity<?> getById(@PathVariable int id) {
        try {
            Extintor extintor = extintorService.getById(id);
            return new ResponseEntity<>(extintor, HttpStatus.OK);
        } catch (IllegalArgumentException ex) {
            return new ResponseEntity<>(ex.getMessage(), HttpStatus.NOT_FOUND);
        }
    }

    @GetMapping("/controleInterno/{numeroCOntroleInterno}")
    @Operation(summary = "Obter extintor por número de controle interno", description = "Retorna um extintor pelo seu número de controle interno")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Operação bem-sucedida"),
            @ApiResponse(responseCode = "404", description = "Extintor não encontrado")
    })
    public ResponseEntity<?> getByNumeroControleInterno(@PathVariable int numeroCOntroleInterno) {
        try {
            Extintor extintor = extintorService.getByNumeroControleInterno(numeroCOntroleInterno);
            return new ResponseEntity<>(extintor, HttpStatus.OK);
        } catch (IllegalArgumentException ex) {
            return new ResponseEntity<>(ex.getMessage(), HttpStatus.NOT_FOUND);
        }
    }

    @PostMapping
    @Operation(summary = "Salvar um novo extintor", description = "Adiciona um novo extintor")
    @ApiResponses({
            @ApiResponse(responseCode = "201", description = "Extintor incluído com sucesso"),
            @ApiResponse(responseCode = "409", description = "Conflito ao incluir extintor")
    })
    public ResponseEntity<String> save(@RequestBody ExtintorDTO extintorDTO) {
        try {
            extintorService.add(extintorDTO);
            return new ResponseEntity<>("Extintor incluído com sucesso!", HttpStatus.CREATED);
        } catch (IllegalArgumentException ex) {
            return new ResponseEntity<>(ex.getMessage(), HttpStatus.CONFLICT);
        }
    }

    @DeleteMapping("/{id}")
    @Operation(summary = "Excluir extintor por ID", description = "Exclui um extintor pelo seu ID")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Extintor excluído com sucesso"),
            @ApiResponse(responseCode = "404", description = "Extintor não encontrado")
    })
    public ResponseEntity<String> deleteById(@PathVariable int id) {
        try {
            extintorService.deleteById(id);
            return new ResponseEntity<>("Extintor excluído com sucesso!", HttpStatus.OK);
        } catch (IllegalArgumentException ex) {
            return new ResponseEntity<>(ex.getMessage(), HttpStatus.NOT_FOUND);
        }
    }

    @PutMapping("/{id}")
    @Operation(summary = "Atualizar extintor por ID", description = "Atualiza um extintor pelo seu ID")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Extintor atualizado com sucesso"),
            @ApiResponse(responseCode = "404", description = "Extintor não encontrado")
    })
    public ResponseEntity<String> update(@PathVariable int id, @RequestBody ExtintorDTO extintorDTO) {
        try {
            extintorService.update(id, extintorDTO);
            return new ResponseEntity<>("Extintor atualizado com sucesso!", HttpStatus.OK);
        } catch (IllegalArgumentException ex) {
            return new ResponseEntity<>(ex.getMessage(), HttpStatus.NOT_FOUND);
        }
    }

    @GetMapping("/historico/{numeroControleInterno}")
    @Operation(summary = "Obter histórico de extintor por número de controle interno", description = "Retorna o histórico de um extintor pelo seu número de controle interno")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Operação bem-sucedida"),
            @ApiResponse(responseCode = "404", description = "Nenhum histórico encontrado para o número de controle interno"),
            @ApiResponse(responseCode = "500", description = "Erro interno do servidor")
    })
    public ResponseEntity<?> getHistoricoByNumeroControleInterno(@PathVariable int numeroControleInterno) {
        try {
            List<ExtintorHistorico> historico = extintorService.getHistoricoByNumeroControleInterno(numeroControleInterno);
            if (historico.isEmpty()) {
                return new ResponseEntity<>("Nenhum histórico encontrado para este número de controle interno.", HttpStatus.NOT_FOUND);
            }
            return new ResponseEntity<>(historico, HttpStatus.OK);
        } catch (Exception ex) {
            return new ResponseEntity<>(ex.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
