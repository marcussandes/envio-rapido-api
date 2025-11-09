package com.api.envio_rapido.service;

import com.api.envio_rapido.dto.*;
import com.api.envio_rapido.entity.Envio;
import com.api.envio_rapido.repository.EnvioRepository;
import org.springframework.stereotype.Service;

@Service
public class EnvioService {


    private final ViaCepService viaCepService;
    private final EnvioRepository envioRepository;
    private final FreteService freteService;

    public EnvioService(ViaCepService viaCepService, EnvioRepository envioRepository, FreteService freteService) {
        this.viaCepService = viaCepService;
        this.envioRepository = envioRepository;
        this.freteService = freteService;
    }

    public EnvioResponse criarEnvio(EnvioRequestDTO dto) {

        ViaCepResponse origem = viaCepService.consultarCep(dto.getCepOrigem());
        ViaCepResponse destino = viaCepService.consultarCep(dto.getCepDestino());

        //if(origem.getCep() == null || destino.getCep() == null) {
         //   throw new IllegalArgumentException("Um dos CEPS informados é invalido.");
        //}

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

        FreteResponse freteMock = freteService.calcularFrete();

        FreteDetail freteDetail = new FreteDetail();
        freteDetail.setValorPac(freteMock.getValorPac());
        freteDetail.setPrazoPac(freteMock.getPrazoPac());
        freteDetail.setValorSedex(freteMock.getValorSedex());
        freteDetail.setPrazoSedex(freteMock.getPrazoSedex());

        EnvioResponse response = new EnvioResponse();
        response.setId(envio.getId());
        response.setMessage("Envio cadastrado e frete calculado com sucesso");
        response.setFrete(freteDetail);
        response.setLinkPostagem(freteMock.getLinkPostagem());

        return response;




    }



}