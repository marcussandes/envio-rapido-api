package com.api.envio_rapido.dto;

import lombok.Data;

@Data
public class FreteResponse {

    private String valorPac;
    private String prazoPac;

    private String valorSedex;
    private String prazoSedex;

    private String linkPostagem;


}
