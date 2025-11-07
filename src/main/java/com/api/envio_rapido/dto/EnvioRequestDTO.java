package com.api.envio_rapido.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class EnvioRequestDTO {

    @NotBlank(message = "O nome do remetente é obrigatório")
    private String nomeRemetente;

    @NotBlank(message = "O CEP de origem é obrigatório")
    private String cepOrigem;

    @NotBlank(message = "O CEP de destino é obrigatório")
    private String cepDestino;

    @NotNull(message = "O peso é obrigatório")
    @Positive(message = "O peso deve ser mairo que zero")
    private double peso;

    @NotNull(message = "A altura é obrigatória")
    @Positive(message = "A altura deve ser maior que zero")
    private double altura;

    @NotNull(message = "A largura é obrigatória")
    @Positive(message = "A largura deve ser maior que zero")
    private double largura;

    @NotNull(message = "O comprimento é obrigatório")
    @Positive(message = "O comprimento deve ser maior que zero")
    private double comprimento;
}
