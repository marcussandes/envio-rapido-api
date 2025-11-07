package com.api.envio_rapido.service;

import com.api.envio_rapido.dto.EnvioRequestDTO;
import com.api.envio_rapido.entity.Envio;
import com.api.envio_rapido.repository.EnvioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class EnvioService {

    @Autowired
    private EnvioRepository envioRepository;

    public Envio criarEnvio(EnvioRequestDTO dto) {
        Envio envio = new Envio();
        envio.setNomeRemetente(dto.getNomeRemetente());
        envio.setCepOrigem(dto.getCepOrigem());
        envio.setCepDestino(dto.getCepDestino());
        envio.setPeso((double) dto.getPeso());
        envio.setAltura((double) dto.getAltura());
        envio.setLargura((double) dto.getLargura());
        envio.setComprimento((double) dto.getComprimento());

        return envioRepository.save(envio);



    }



}