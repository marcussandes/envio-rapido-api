package com.api.envio_rapido.dto;

import lombok.Data;

import java.util.List;


@Data
public class MelhorEnvioRequest {
    private ZipCode from;
    private ZipCode to;
    private List<Product> products;
    private Options options = new Options();


    @Data
    public static class ZipCode {
        private String postal_code;
    }

    @Data
    public static class Product {
        private Double weight;
        private Double width;
        private Double height;
        private Double length;
        private Integer quantity = 1;
        private Double insurance_value = 0.0;
    }

    @Data
    public static class Options {
        private Boolean receipt = false;
        private Boolean own_hand = false;
        private Boolean reverse = false;
        private Boolean non_comercial = false;
    }
}
