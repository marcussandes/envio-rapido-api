package com.api.envio_rapido.controller;

import com.api.envio_rapido.dto.EnvioRequestDTO;
import com.api.envio_rapido.dto.EnvioResponse;
import com.api.envio_rapido.service.EnvioService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/envios")
public class EnvioController {

    @Autowired
    private EnvioService envioService;

    @PostMapping
    public ResponseEntity<EnvioResponse> cadastrarEnvio(@RequestBody @Valid EnvioRequestDTO dto) {
        EnvioResponse envioResponse = envioService.criarEnvio(dto);
        return ResponseEntity.status(HttpStatus.CREATED).body(envioResponse);
    }





}
