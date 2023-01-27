package apirelatorios.Relatorio.domain.entity;

import lombok.Data;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "Campanha")
@Data
public class Campanha {

    private String id;
    private String nomeCampanha;
    private boolean situacao = true;

}
