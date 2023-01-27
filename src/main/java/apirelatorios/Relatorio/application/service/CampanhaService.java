package apirelatorios.Relatorio.application.service;

import apirelatorios.Relatorio.application.controller.exception.NotFoundException;
import apirelatorios.Relatorio.application.mapper.CampanhaMapper;
import apirelatorios.Relatorio.application.response.CampanhaResponse;
import apirelatorios.Relatorio.domain.entity.Campanha;
import apirelatorios.Relatorio.domain.entity.ContatosCampanha;
import apirelatorios.Relatorio.domain.repository.CampanhaRepository;
import apirelatorios.Relatorio.domain.repository.ContatosCampanhaRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class CampanhaService {

    private final CampanhaRepository campanhaRepository;
    private final ContatosCampanhaRepository contatosCampanhaRepository;
    private final CampanhaMapper campanhaMapper;

    public CampanhaResponse findById(String id) {
        Optional<Campanha> campanha = campanhaRepository.findById(id);
        if (!campanha.isPresent()) {
            throw new NotFoundException("Campanha ID: [" + id + "] não encontrada!");
        }
        return campanhaMapper.toCampanhaResponse(campanha.get());
    }


    public List<ContatosCampanha> findByCampanhaidAndDataimportacaoBetween
            (String campanhaId, Long dataInit, Long dataEnd) {
        List<ContatosCampanha> list = contatosCampanhaRepository
                .findByCampanhaidAndDataimportacaoBetween(campanhaId, dataInit, dataEnd);

        if (list.isEmpty()) {
            throw new NotFoundException("Não foram encontrados dados para esta data");
        }
        return list;
    }
}
