package apirelatorios.Relatorio.domain.entity;

import lombok.Data;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.List;

@Document(collection = "ContatosCampanha")
@Data
public class ContatosCampanha {

    private String id;
    private String hostname;
    private String campanhaid;
    private String namegroup;
    private String idgrupo;
    private List<String> contacts;
    private List<String> outcontacts;
    private List<String> newcontacts;
    private Long dataimportacao;
}
