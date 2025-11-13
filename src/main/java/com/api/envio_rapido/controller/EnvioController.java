package com.api.envio_rapido.controller;

import com.api.envio_rapido.dto.EnvioRequestDTO;
import com.api.envio_rapido.dto.EnvioResponse;
import com.api.envio_rapido.entity.Envio;
import com.api.envio_rapido.service.EnvioService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/envios")
public class EnvioController {

    @Autowired
    private EnvioService envioService;

    public EnvioController(EnvioService envioService) {
        this.envioService = envioService;
    }

    @PostMapping
    public ResponseEntity<EnvioResponse> cadastrarEnvio(@RequestBody @Valid EnvioRequestDTO dto) {
        EnvioResponse envioResponse = envioService.criarEnvio(dto);
        return ResponseEntity.status(HttpStatus.CREATED).body(envioResponse);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Envio> buscarPorId(@PathVariable Long id) {
        Envio envio = envioService.buscarPorId(id);
        return ResponseEntity.ok(envio);
    }

    @GetMapping
    public ResponseEntity<List<Envio>> listarTodos() {
        List<Envio> envios = envioService.listarTodos();
        return ResponseEntity.ok(envios);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Envio> atualizarEnvio(@PathVariable Long id, @RequestBody EnvioRequestDTO dto) {
        Envio envioAtualizado = envioService.atualizarEnvio(id, dto);
        return ResponseEntity.ok(envioAtualizado);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletarEnvio(@PathVariable Long id) {
        envioService.deletarEnvio(id);
        return ResponseEntity.noContent().build();
    }





}
