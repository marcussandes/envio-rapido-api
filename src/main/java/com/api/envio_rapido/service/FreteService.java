package com.api.envio_rapido.service;

import com.api.envio_rapido.dto.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpHeaders;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;

import java.util.List;

@Service
public class FreteService {

    private final WebClient melhorEnvioClient;

    @Value("${melhor-envio.token}")
    private final String melhorEnvioToken;

    @Autowired
    public FreteService(
            WebClient melhorEnvioClient,
            @Value("${melhor-envio.token}") String melhorEnvioToken ){
        this.melhorEnvioClient = melhorEnvioClient;
        this.melhorEnvioToken = melhorEnvioToken;
    }



    public List<ShippingOption> calcularFrete(EnvioRequestDTO dto) {

        MelhorEnvioRequest requestBody = mapToMelhorEnvioRequest(dto);

    try {
        return melhorEnvioClient.post()
                .uri("/api/v2/me/shipment/calculate")
                .header(HttpHeaders.AUTHORIZATION, "Bearer " + melhorEnvioToken.trim())
                .header(HttpHeaders.CONTENT_TYPE, "Application/json")
                .bodyValue(requestBody)
                .retrieve()
                .bodyToMono(new ParameterizedTypeReference<List<ShippingOption>>() {
                })

                .block();
    } catch (Exception e) {

        System.out.println("Erro na chamada da Melhor Envio " + e.getMessage());

        throw new RuntimeException("Falha na comunicação com a Melhor Envio. Verifique credencias e log de erro. Detalhes: " + e.getMessage());
        }
    }


    private MelhorEnvioRequest mapToMelhorEnvioRequest(EnvioRequestDTO dto) {
        MelhorEnvioRequest.Product product = new MelhorEnvioRequest.Product();
        product.setWeight(dto.getPeso());
        product.setWidth(dto.getLargura());
        product.setHeight(dto.getAltura());
        product.setLength(dto.getComprimento());
        product.setInsurance_value(0.0);

        MelhorEnvioRequest.ZipCode fromZip = new MelhorEnvioRequest.ZipCode();
        fromZip.setPostal_code(dto.getCepOrigem());

        MelhorEnvioRequest.ZipCode toZip = new MelhorEnvioRequest.ZipCode();
        toZip.setPostal_code(dto.getCepDestino());

        MelhorEnvioRequest request = new MelhorEnvioRequest();
        request.setFrom(fromZip);
        request.setTo(toZip);
        request.setProducts(List.of(product));

        return request;
    }
}
