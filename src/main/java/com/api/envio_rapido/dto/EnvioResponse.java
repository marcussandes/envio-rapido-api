package com.api.envio_rapido.dto;

import lombok.Data;

@Data
public class EnvioResponse {

    private Long id;
    private String message;
    private FreteDetail frete;
    private String linkPostagem;
}
