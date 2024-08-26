package br.com.edu.infnet.inspecoespcipb.controller;

import br.com.edu.infnet.inspecoespcipb.domain.InspecaoExtintor;
import br.com.edu.infnet.inspecoespcipb.dto.InspecaoExtintorDTO;
import br.com.edu.infnet.inspecoespcipb.service.InspecaoExtintorService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Collection;

@RestController
@RequestMapping("/inspecoes")
public class InspecaoExtintorController {

    @Autowired
    private InspecaoExtintorService inspecaoExtintorService;

    @GetMapping
    @Operation(summary = "Obter todas as inspeções", description = "Retorna uma coleção de todas as inspeções")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Operação bem-sucedida"),
            @ApiResponse(responseCode = "400", description = "Requisição inválida")
    })
    public ResponseEntity<?> getAll() {
        try {
            Collection<InspecaoExtintor> inspecoes = inspecaoExtintorService.getAll();
            return new ResponseEntity<>(inspecoes, HttpStatus.OK);
        } catch (IllegalArgumentException ex) {
            return new ResponseEntity<>(ex.getMessage(), HttpStatus.OK);
        }
    }

    @GetMapping("/{id}")
    @Operation(summary = "Obter inspeção por ID", description = "Retorna uma inspeção pelo seu ID")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Operação bem-sucedida"),
            @ApiResponse(responseCode = "404", description = "Inspeção não encontrada")
    })
    public ResponseEntity<?> getById(@PathVariable int id) {
        try {
            InspecaoExtintor inspecao = inspecaoExtintorService.getById(id);
            return new ResponseEntity<>(inspecao, HttpStatus.OK);
        } catch (IllegalArgumentException ex) {
            return new ResponseEntity<>(ex.getMessage(), HttpStatus.NOT_FOUND);
        }
    }

    @GetMapping("/extintor/{idExtintor}")
    @Operation(summary = "Obter inspeções por ID de extintor", description = "Retorna uma coleção de inspeções relacionadas a um extintor específico")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Operação bem-sucedida"),
            @ApiResponse(responseCode = "404", description = "Inspeções não encontradas para o extintor")
    })
    public ResponseEntity<?> getByExtintorId(@PathVariable int idExtintor) {
        try {
            Collection<InspecaoExtintor> inspecoes = inspecaoExtintorService.getByExtintorId(idExtintor);
            return new ResponseEntity<>(inspecoes, HttpStatus.OK);
        } catch (IllegalArgumentException ex) {
            return new ResponseEntity<>(ex.getMessage(), HttpStatus.NOT_FOUND);
        }
    }

    @PostMapping
    @Operation(summary = "Salvar uma nova inspeção", description = "Adiciona uma nova inspeção")
    @ApiResponses({
            @ApiResponse(responseCode = "201", description = "Inspeção incluída com sucesso"),
            @ApiResponse(responseCode = "400", description = "Requisição inválida")
    })
    public ResponseEntity<?> save(@RequestBody InspecaoExtintorDTO inspecaoExtintorDTO) {
        try {
            inspecaoExtintorService.add(inspecaoExtintorDTO);
            return new ResponseEntity<>("Inspeção incluída com sucesso!", HttpStatus.CREATED);
        } catch (IllegalArgumentException ex) {
            return new ResponseEntity<>(ex.getMessage(), HttpStatus.NOT_FOUND);
        }
    }

    @DeleteMapping("/{id}")
    @Operation(summary = "Excluir inspeção por ID", description = "Exclui uma inspeção pelo seu ID")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Inspeção excluída com sucesso"),
            @ApiResponse(responseCode = "404", description = "Inspeção não encontrada")
    })
    public ResponseEntity<?> delete(@PathVariable int id) {
        try {
            inspecaoExtintorService.delete(id);
            return new ResponseEntity<>("Inspeção excluída com sucesso!", HttpStatus.OK);
        } catch (IllegalArgumentException ex) {
            return new ResponseEntity<>(ex.getMessage(), HttpStatus.NOT_FOUND);
        }
    }

    @PutMapping("/{id}")
    @Operation(summary = "Atualizar inspeção por ID", description = "Atualiza uma inspeção pelo seu ID")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Inspeção atualizada com sucesso"),
            @ApiResponse(responseCode = "404", description = "Inspeção não encontrada")
    })
    public ResponseEntity<?> update(@PathVariable int id, @RequestBody InspecaoExtintorDTO inspecaoExtintorDTO) {
        try {
            inspecaoExtintorService.update(id, inspecaoExtintorDTO);
            return new ResponseEntity<>("Inspeção atualizada com sucesso!", HttpStatus.OK);
        } catch (IllegalArgumentException ex) {
            return new ResponseEntity<>(ex.getMessage(), HttpStatus.NOT_FOUND);
        }
    }


}
