package com.api.envio_rapido.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.*;

@Entity
@Table(name = "envio")
@Data
public class Envio {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String nomeRemetente;
    private String cepOrigem;
    private String cepDestino;
    private Double peso;
    private Double altura;
    private Double largura;
    private Double comprimento;
    private String logradouro;
    private String uf;


}
