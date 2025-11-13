package com.api.envio_rapido.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;

@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class ShippingOption {
    private Integer id;
    private String name;
    private Double price;
    private Double custom_price;
    private String company_name;
    private Integer delivery_time;
}
