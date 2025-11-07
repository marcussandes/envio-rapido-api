package com.api.envio_rapido.controller;

import com.api.envio_rapido.entity.Envio;
import com.api.envio_rapido.service.EnvioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/envio")
public class EnvioController {

    @Autowired
    private EnvioService envioService;

    @PostMapping
    public Envio criarEnvio(@RequestBody Envio envio) {
        return envioService.salvarEnvio(envio);
    }
}
