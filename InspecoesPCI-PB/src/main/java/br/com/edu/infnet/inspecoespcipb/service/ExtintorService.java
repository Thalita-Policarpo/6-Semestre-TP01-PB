package br.com.edu.infnet.inspecoespcipb.service;

import br.com.edu.infnet.inspecoespcipb.domain.Extintor;
import br.com.edu.infnet.inspecoespcipb.dto.ExtintorDTO;
import br.com.edu.infnet.inspecoespcipb.repository.ExtintorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ExtintorService {

    @Autowired
    private ExtintorRepository extintorRepository;


    public List<Extintor> getAll() {
        List<Extintor> extintores = extintorRepository.findAll();
        if (extintores.isEmpty()) {
            throw new IllegalArgumentException("Não existe nenhum extintor cadastrado ainda");
        }
        return extintores;
    }

    public Extintor getById(int id) {
        return extintorRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Id inválido: " + id));
    }

    public Extintor add(ExtintorDTO extintorDTO){
        if(extintorRepository.findByNumeroControleInterno(extintorDTO.getNumeroControleInterno()).isPresent()){
            throw new IllegalArgumentException("Extintor com este número de controle interno já existe");
        }

        Extintor extintor = new Extintor(
                extintorDTO.getNumeroControleInterno(),
                extintorDTO.getNumeroCilindro(),
                extintorDTO.getNumeroSeloInmetro(),
                extintorDTO.getCargaExtintora(),
                extintorDTO.getCapacidade(),
                extintorDTO.getDataVencimento(),
                extintorDTO.getProximoTesteHidrostatico()
        );
        return extintorRepository.save(extintor);
    }

    public void deleteById(int id) {
        if(!extintorRepository.existsById(id)){
            throw new IllegalArgumentException("Id inválido: " + id);
        }else{
            extintorRepository.deleteById(id);
        }
    }

    public void update(int id, ExtintorDTO extintorDTO) {
        Extintor extintor = extintorRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Id inválido: " + id));

        extintor.setNumeroControleInterno(extintorDTO.getNumeroControleInterno());
        extintor.setNumeroCilindro(extintorDTO.getNumeroCilindro());
        extintor.setNumeroSeloInmetro(extintorDTO.getNumeroSeloInmetro());
        extintor.setCargaEsxtintora(extintorDTO.getCargaExtintora());
        extintor.setCapacidade(extintorDTO.getCapacidade());
        extintor.setDataVencimento(extintorDTO.getDataVencimento());
        extintor.setProximoTesteHidrostatico(extintorDTO.getProximoTesteHidrostatico());

        extintorRepository.save(extintor);
    }

    public Extintor getByNumeroControleInterno(int numeroControleInterno) {
        return extintorRepository.findByNumeroControleInterno(numeroControleInterno)
                .orElseThrow(() -> new IllegalArgumentException("Número de controle interno inválido: " + numeroControleInterno));
    }
}
