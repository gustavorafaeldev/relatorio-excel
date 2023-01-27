package apirelatorios.Relatorio.application.mapper;


import apirelatorios.Relatorio.application.response.ContatosCampanhaResponse;
import apirelatorios.Relatorio.domain.entity.ContatosCampanha;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class ContatosCampanhaMapper {

    private final ModelMapper mapper;

    public ContatosCampanhaResponse toContatosCampanhaResponse(ContatosCampanha contatosCampanha) {
        return mapper.map(contatosCampanha, ContatosCampanhaResponse.class);
    }
}
