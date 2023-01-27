package apirelatorios.Relatorio.application.response;

import lombok.Data;

@Data
public class CampanhaResponse {

    private String id;
    private String nomeCampanha;
    private boolean situacao = true;
}
