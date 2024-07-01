package br.com.edu.infnet.inspecoespcipb.service;


import br.com.edu.infnet.inspecoespcipb.domain.Extintor;
import br.com.edu.infnet.inspecoespcipb.domain.InspecaoExtintor;
import br.com.edu.infnet.inspecoespcipb.domain.Usuario;
import br.com.edu.infnet.inspecoespcipb.dto.InspecaoExtintorDTO;
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

    @Autowired
    private UsuarioService usuarioService;


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

    public void add(InspecaoExtintorDTO inspecaoExtintorDTO) {
        Extintor extintor = extintorService.getById(inspecaoExtintorDTO.getExtintorId());
        Usuario usuario = usuarioService.getById(inspecaoExtintorDTO.getUsuarioId());

        if(extintor == null){
            throw new IllegalArgumentException("Extintor não está cadastrado");
        }

        InspecaoExtintor inspecaoExtintor = new InspecaoExtintor();

        inspecaoExtintor.setExtintor(extintor);
        inspecaoExtintor.setUsuario(usuario);
        inspecaoExtintor.setDataInspecao(LocalDate.now());
        inspecaoExtintor.setSinalizado(inspecaoExtintorDTO.isSinalizado());
        inspecaoExtintor.setDesobstruido(inspecaoExtintorDTO.isDesobstruido());
        inspecaoExtintor.setManometroPressaoAdequada(inspecaoExtintorDTO.isManometroPressaoAdequada());
        inspecaoExtintor.setGatilhoBoasCondicoes(inspecaoExtintorDTO.isGatilhoBoasCondicoes());
        inspecaoExtintor.setMangoteBoasCondicoes(inspecaoExtintorDTO.isMangoteBoasCondicoes());
        inspecaoExtintor.setRotuloPinturaBoasCondicoes(inspecaoExtintorDTO.isRotuloPinturaBoasCondicoes());
        inspecaoExtintor.setSuporteBoasCondicoes(inspecaoExtintorDTO.isSuporteBoasCondicoes());
        inspecaoExtintor.setLacreIntacto(inspecaoExtintorDTO.isLacreIntacto());

        inspecaoExtintor.setStatus(extintor);
        inspecaoExtintorRepository.save(inspecaoExtintor);
    }

    public void delete(int id) {
        if(!inspecaoExtintorRepository.existsById(id)){
            throw new IllegalArgumentException("Id inválido: " + id);
        }else{
            inspecaoExtintorRepository.deleteById(id);
        }
    }

    public void update(int id, InspecaoExtintorDTO inspecaoExtintorDTO) {

        Extintor extintor = extintorService.getById(inspecaoExtintorDTO.getExtintorId());
        Usuario usuario = usuarioService.getById(inspecaoExtintorDTO.getUsuarioId());

        InspecaoExtintor inspecaoExtintor = inspecaoExtintorRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Id inválido: " + id));

        inspecaoExtintor.setExtintor(extintor);
        inspecaoExtintor.setUsuario(usuario);
        inspecaoExtintor.setDataInspecao(LocalDate.now());
        inspecaoExtintor.setSinalizado(inspecaoExtintorDTO.isSinalizado());
        inspecaoExtintor.setDesobstruido(inspecaoExtintorDTO.isDesobstruido());
        inspecaoExtintor.setManometroPressaoAdequada(inspecaoExtintorDTO.isManometroPressaoAdequada());
        inspecaoExtintor.setGatilhoBoasCondicoes(inspecaoExtintorDTO.isGatilhoBoasCondicoes());
        inspecaoExtintor.setMangoteBoasCondicoes(inspecaoExtintorDTO.isMangoteBoasCondicoes());
        inspecaoExtintor.setRotuloPinturaBoasCondicoes(inspecaoExtintorDTO.isRotuloPinturaBoasCondicoes());
        inspecaoExtintor.setSuporteBoasCondicoes(inspecaoExtintorDTO.isSuporteBoasCondicoes());
        inspecaoExtintor.setLacreIntacto(inspecaoExtintorDTO.isLacreIntacto());

        inspecaoExtintor.setStatus(extintor);

        inspecaoExtintorRepository.save(inspecaoExtintor);

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
