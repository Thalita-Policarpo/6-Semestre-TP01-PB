package br.com.edu.infnet.inspecoespcipb.service;


import br.com.edu.infnet.inspecoespcipb.domain.Extintor;
import br.com.edu.infnet.inspecoespcipb.domain.InspecaoExtintor;
import br.com.edu.infnet.inspecoespcipb.repository.InspecaoExtintorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;


@Service
public class InspecaoExtintorService {

    @Autowired
    private InspecaoExtintorRepository inspecaoExtintorRepository;

    @Autowired
    ExtintorService extintorService;


    public List<InspecaoExtintor> getAll() {
        List<InspecaoExtintor> inspecoes = inspecaoExtintorRepository.findAll();
        if (inspecoes.isEmpty()) {
            throw new IllegalArgumentException("Não existem inspeções de extintores realizadas no momento");
        }
        return inspecoes;
    }

    public InspecaoExtintor getById(int id) {
        return inspecaoExtintorRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Id inválido: " + id));
    }

    public void add(InspecaoExtintor inspecaoExtintor, int idExtintor) {
        Extintor extintor = extintorService.getById(idExtintor);
        if(extintor == null){
            throw new IllegalArgumentException("Extintor não está cadastrado");
        }
        inspecaoExtintor.setExtintor(extintor);
        inspecaoExtintor.setStatus(extintor);
        inspecaoExtintor.setDataInspecao(LocalDate.now());
        inspecaoExtintorRepository.save(inspecaoExtintor);
    }

    public void delete(int id) {
        if(!inspecaoExtintorRepository.existsById(id)){
            throw new IllegalArgumentException("Id inválido: " + id);
        }else{
            inspecaoExtintorRepository.deleteById(id);
        }
    }

    public void update(int id, InspecaoExtintor inspecao) {
        if(!inspecaoExtintorRepository.existsById(id)){
            throw new IllegalArgumentException("Id inválido: " + id);
        }else{
            inspecao.setExtintor(extintorService.getById(id));
            inspecao.setDataInspecao(LocalDate.now());
            inspecao.setId(id);
            inspecao.setStatus(inspecao.getExtintor());
            inspecaoExtintorRepository.save(inspecao);
        }
    }

    public List<InspecaoExtintor> getByExtintorId(int idExtintor) {
        Extintor extintor = extintorService.getById(idExtintor);
        if(extintor == null){
            throw new IllegalArgumentException("Extintor não está cadastrado! id:" + idExtintor);
        }
        List<InspecaoExtintor> inspecoes = extintor.getInspecoes();
        if(inspecoes.isEmpty()){
            throw new IllegalArgumentException("Este extintor não possui inspeções! id:" + idExtintor);
        }
        return inspecoes;
    }


}
