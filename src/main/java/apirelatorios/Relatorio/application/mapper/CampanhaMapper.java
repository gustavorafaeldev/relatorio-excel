package apirelatorios.Relatorio.application.mapper;

import apirelatorios.Relatorio.application.response.CampanhaResponse;
import apirelatorios.Relatorio.domain.entity.Campanha;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class CampanhaMapper {

    private final ModelMapper mapper;

    public CampanhaResponse toCampanhaResponse(Campanha campanha) {
        return mapper.map(campanha, CampanhaResponse.class);
    }
}
