package br.com.edu.infnet.inspecoespcipb.controller;

import br.com.edu.infnet.inspecoespcipb.domain.Extintor;
import br.com.edu.infnet.inspecoespcipb.dto.ExtintorDTO;
import br.com.edu.infnet.inspecoespcipb.service.ExtintorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Collection;

@RestController
@RequestMapping("/extintor")
public class ExtintorController {


    @Autowired
    private ExtintorService extintorService;

    @GetMapping
    public ResponseEntity<?> getAll() {
        try {
            Collection<Extintor> extintores = extintorService.getAll();
            return new ResponseEntity<>(extintores, HttpStatus.OK);
        } catch (IllegalArgumentException ex) {
            return new ResponseEntity<>(ex.getMessage(), HttpStatus.OK);
        }
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> getById(@PathVariable int id) {
        try {
            Extintor extintor = extintorService.getById(id);
            return new ResponseEntity<>(extintor, HttpStatus.OK);
        } catch (IllegalArgumentException ex) {
            return new ResponseEntity<>(ex.getMessage(), HttpStatus.NOT_FOUND);
        }
    }

    @GetMapping("/controleInterno/{numeroCOntroleInterno}")
    public ResponseEntity<?> getByNumeroControleInterno(@PathVariable int numeroCOntroleInterno) {
        try {
            Extintor extintor = extintorService.getByNumeroControleInterno(numeroCOntroleInterno);
            return new ResponseEntity<>(extintor, HttpStatus.OK);
        } catch (IllegalArgumentException ex) {
            return new ResponseEntity<>(ex.getMessage(), HttpStatus.NOT_FOUND);
        }
    }

    @PostMapping
    public ResponseEntity<String> save(@RequestBody ExtintorDTO extintorDTO) {
        try {
            extintorService.add(extintorDTO);
            return new ResponseEntity<>("Extintor incluído com sucesso!", HttpStatus.CREATED);
        } catch (IllegalArgumentException ex) {
            return new ResponseEntity<>(ex.getMessage(), HttpStatus.CONFLICT);
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteById(@PathVariable int id) {
        try {
            extintorService.deleteById(id);
            return new ResponseEntity<>("Extintor excluído com sucesso!", HttpStatus.OK);
        } catch (IllegalArgumentException ex) {
            return new ResponseEntity<>(ex.getMessage(), HttpStatus.NOT_FOUND);
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<String> update(@PathVariable int id, @RequestBody ExtintorDTO extintorDTO) {
        try {
            extintorService.update(id, extintorDTO);
            return new ResponseEntity<>("Extintor atualizado com sucesso!", HttpStatus.OK);
        } catch (IllegalArgumentException ex) {
            return new ResponseEntity<>(ex.getMessage(), HttpStatus.NOT_FOUND);
        }
    }
}
