package com.api.envio_rapido.service;

import com.api.envio_rapido.dto.ViaCepResponse;
import com.api.envio_rapido.exception.CepInvalidoException;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class ViaCepService {

    private final RestTemplate restTemplate;
    private final String VIA_CEP_URL = "https://viacep.com.br/ws/{cep}/json/";


    public ViaCepService(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    public ViaCepResponse consultarCep(String cep) {

        ViaCepResponse response = restTemplate.getForObject(VIA_CEP_URL, ViaCepResponse.class, cep);

        if (response != null && response.isErro()) {
            throw new CepInvalidoException("CEP " + cep + " inválido");
        }
        return response;
    }
}

