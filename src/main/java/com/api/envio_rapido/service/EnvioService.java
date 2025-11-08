package com.api.envio_rapido.service;

import com.api.envio_rapido.dto.EnvioRequestDTO;
import com.api.envio_rapido.dto.ViaCepResponse;
import com.api.envio_rapido.entity.Envio;
import com.api.envio_rapido.repository.EnvioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Map;

@Service
public class EnvioService {


    private final ViaCepService viaCepService;
    private final EnvioRepository envioRepository;

    public EnvioService(ViaCepService viaCepService, EnvioRepository envioRepository) {
        this.viaCepService = viaCepService;
        this.envioRepository = envioRepository;
    }

    public Envio criarEnvio(EnvioRequestDTO dto) {

        ViaCepResponse origem = viaCepService.consultarCep(dto.getCepOrigem());
        ViaCepResponse destino = viaCepService.consultarCep(dto.getCepDestino());

        //if(origem.getCep() == null || destino.getCep() == null) {
         //   throw new IllegalArgumentException("Um dos CEPS informados é invalido.");
        //}

        Envio envio = new Envio();
        envio.setNomeRemetente(dto.getNomeRemetente());
        envio.setCepOrigem(dto.getCepOrigem());
        envio.setCepDestino(dto.getCepDestino());
        envio.setPeso(dto.getPeso());
        envio.setAltura(dto.getAltura());
        envio.setLargura(dto.getLargura());
        envio.setComprimento(dto.getComprimento());

        envio.setLogradouro(destino.getLogradouro());
        envio.setUf(destino.getUf());

        Envio envioSalvo = envioRepository.save(envio);

        return envioSalvo;




    }



}