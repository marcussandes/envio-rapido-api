package com.api.envio_rapido.service;

import com.api.envio_rapido.dto.*;
import com.api.envio_rapido.entity.Envio;
import com.api.envio_rapido.repository.EnvioRepository;
import org.springframework.stereotype.Service;

import java.text.DecimalFormat;
import java.util.List;
import java.util.Locale;
import java.util.Optional;

@Service
public class EnvioService {


    private final ViaCepService viaCepService;
    private final EnvioRepository envioRepository;
    private final FreteService freteService;
    DecimalFormat df = new DecimalFormat("#0.00");

    public EnvioService(ViaCepService viaCepService, EnvioRepository envioRepository, FreteService freteService) {
        this.viaCepService = viaCepService;
        this.envioRepository = envioRepository;
        this.freteService = freteService;
    }

    public EnvioResponse criarEnvio(EnvioRequestDTO dto) {

        ViaCepResponse origem = viaCepService.consultarCep(dto.getCepOrigem());
        ViaCepResponse destino = viaCepService.consultarCep(dto.getCepDestino());

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

        List<ShippingOption> freteOpcoes = freteService.calcularFrete(dto);

        if (freteOpcoes == null || freteOpcoes.isEmpty()) {
            throw new IllegalArgumentException("Nenhuma op~]ao de frete (PAC/SEDEX) foi retornada pela Melhor Envio");

        }

        Optional<ShippingOption> pacOption = freteOpcoes.stream()
                .filter(op -> op.getName().toLowerCase().contains("pac"))
                .findFirst();

        Optional<ShippingOption> sedexOption = freteOpcoes.stream()
                .filter(op -> op.getName().toLowerCase().contains("sedex"))
                .findFirst();

        FreteDetail freteDetail = new FreteDetail();
        pacOption.ifPresentOrElse(pac -> {
            if (pac.getPrice() != null) {
                freteDetail.setValorPac(df.format(pac.getPrice()));
            } else {
                freteDetail.setValorPac("Não disponivel");
            }
            freteDetail.setPrazoPac(
                    pac.getDelivery_time() != null ? pac.getDelivery_time() + " dias uteis" : "N/A"
            );
        }, () -> {
            freteDetail.setValorPac("Não disponivel");
            freteDetail.setPrazoPac("N/A");
        });

        sedexOption.ifPresentOrElse(sedex -> {
            if (sedex.getPrice() != null) {
                freteDetail.setValorSedex(df.format(sedex.getPrice()));
            } else {
                freteDetail.setValorSedex("Não disponivel");
            }
            freteDetail.setPrazoSedex(
                    sedex.getDelivery_time() != null ? sedex.getDelivery_time() + " dias uteis" : "N/A"
            );

        }, () -> {
            freteDetail.setValorSedex("Não disponivel");
            freteDetail.setPrazoSedex("N/A");
        });

        EnvioResponse response = new EnvioResponse();
        response.setId(envioSalvo.getId());
        response.setMessage("Envio cadastrado e frete calculado com sucesso");
        response.setFrete(freteDetail);
        response.setLinkPostagem("https://melhorenvio.com.br/rastreio/..."); //link genérico

        return response;
    }

    public Envio buscarPorId(Long id) {
        return envioRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Envio não encontrado com ID: " + id));
    }

    public List<Envio> listarTodos() {
        return envioRepository.findAll();
    }

    public Envio atualizarEnvio(Long id, EnvioRequestDTO dto) {
        Envio envioExistente = buscarPorId(id);
        envioExistente.setCepOrigem(dto.getCepOrigem());
        envioExistente.setCepDestino(dto.getCepDestino());

        return envioRepository.save(envioExistente);
    }

    public void deletarEnvio(Long id) {
        Envio envio = buscarPorId(id);
        envioRepository.delete(envio);
    }

}