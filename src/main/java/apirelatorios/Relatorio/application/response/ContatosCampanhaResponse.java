package apirelatorios.Relatorio.application.response;

import lombok.Data;

import java.util.List;

@Data
public class ContatosCampanhaResponse {

    private String id;
    private String hostName;
    private String campanhaid;
    private String namegroup;
    private String idgrupo;
    private List<String> contacts;
    private List<String> outcontacts;
    private List<String> newcontacts;
    private String dataimportacao;
}
