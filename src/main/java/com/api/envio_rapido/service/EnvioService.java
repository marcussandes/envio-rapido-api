package com.api.envio_rapido.service;

import com.api.envio_rapido.entity.Envio;
import com.api.envio_rapido.repository.EnvioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class EnvioService {

    @Autowired
    private EnvioRepository envioRepository;

    public Envio salvarEnvio(Envio envio) {
        return envioRepository.save(envio);
    }

}