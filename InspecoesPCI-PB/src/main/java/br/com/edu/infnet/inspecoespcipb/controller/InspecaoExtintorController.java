package br.com.edu.infnet.inspecoespcipb.controller;

import br.com.edu.infnet.inspecoespcipb.domain.InspecaoExtintor;
import br.com.edu.infnet.inspecoespcipb.service.InspecaoExtintorService;
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
    public ResponseEntity<?> getAll() {
        try {
            Collection<InspecaoExtintor> inspecoes = inspecaoExtintorService.getAll();
            return new ResponseEntity<>(inspecoes, HttpStatus.OK);
        } catch (IllegalArgumentException ex) {
            return new ResponseEntity<>(ex.getMessage(), HttpStatus.OK);
        }
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> getById(@PathVariable int id) {
        try {
            InspecaoExtintor inspecao = inspecaoExtintorService.getById(id);
            return new ResponseEntity<>(inspecao, HttpStatus.OK);
        } catch (IllegalArgumentException ex) {
            return new ResponseEntity<>(ex.getMessage(), HttpStatus.NOT_FOUND);
        }
    }

    @GetMapping("/extintor/{idExtintor}")
    public ResponseEntity<?> getByExtintorId(@PathVariable int idExtintor) {
        try {
            Collection<InspecaoExtintor> inspecoes = inspecaoExtintorService.getByExtintorId(idExtintor);
            return new ResponseEntity<>(inspecoes, HttpStatus.OK);
        } catch (IllegalArgumentException ex) {
            return new ResponseEntity<>(ex.getMessage(), HttpStatus.NOT_FOUND);
        }
    }

    @PostMapping("/{idExtintor}")
    public ResponseEntity<?> save(@RequestBody InspecaoExtintor inspecaoExtintor, @PathVariable int idExtintor) {
        try {
            inspecaoExtintorService.add(inspecaoExtintor, idExtintor);
            return new ResponseEntity<>("Inspeção incluída com sucesso!", HttpStatus.CREATED);
        } catch (IllegalArgumentException ex) {
            return new ResponseEntity<>(ex.getMessage(), HttpStatus.NOT_FOUND);
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> delete(@PathVariable int id) {
        try {
            inspecaoExtintorService.delete(id);
            return new ResponseEntity<>("Inspeção excluída com sucesso!", HttpStatus.OK);
        } catch (IllegalArgumentException ex) {
            return new ResponseEntity<>(ex.getMessage(), HttpStatus.NOT_FOUND);
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> update(@PathVariable int id, @RequestBody InspecaoExtintor inspecaoExtintor) {
        try {
            inspecaoExtintorService.update(id, inspecaoExtintor);
            return new ResponseEntity<>("Inspeção atualizada com sucesso!", HttpStatus.OK);
        } catch (IllegalArgumentException ex) {
            return new ResponseEntity<>(ex.getMessage(), HttpStatus.NOT_FOUND);
        }
    }


}
