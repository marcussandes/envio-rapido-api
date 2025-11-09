package com.api.envio_rapido.service;

import com.api.envio_rapido.dto.FreteResponse;
import org.springframework.stereotype.Service;

@Service
public class FreteService {

    public FreteResponse calcularFrete() {

        FreteResponse response = new FreteResponse();

        response.setValorPac("19.99");
        response.setPrazoPac("5 dias uteis");
        response.setValorSedex("39.50");
        response.setPrazoSedex("3 dias uteis");
        response.setLinkPostagem("http://mock.logistica/codigo123");

        return response;

    }
}
