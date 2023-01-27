package apirelatorios.Relatorio.application.service;

import apirelatorios.Relatorio.application.request.ContatosCampanhaRequest;
import apirelatorios.Relatorio.application.service.relatorio.GerarRelatorioDetalhado;
import apirelatorios.Relatorio.application.service.relatorio.GerarRelatorioUnico;
import apirelatorios.Relatorio.domain.entity.ContatosCampanha;
import apirelatorios.Relatorio.utils.DateConverter;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletResponse;
import java.util.HashSet;
import java.util.List;

@Service
@RequiredArgsConstructor
public class ContatosCampanhaService {

    private final DateConverter dateConverter;

    private final GerarRelatorioDetalhado gerarRelatorioDetalhado;
    private final GerarRelatorioUnico gerarRelatorioUnico;

    public final CampanhaService campanhaService;

    public ContatosCampanhaRequest request;


    public ResponseEntity<?> findDetalhado(ContatosCampanhaRequest request,
                                           String type, HttpServletResponse httpServletResponse) {

        this.request = request;
        return gerarRelatorioDetalhado.gerarArquivo(request.getDatas(), type, httpServletResponse, this);
    }

    public ResponseEntity<?> findUnico(ContatosCampanhaRequest request,
                                       String type, HttpServletResponse httpServletResponse) {

        this.request = request;

        return gerarRelatorioUnico.gerarArquivo(request.getDatas(), type, httpServletResponse, this);
    }

    public ResponseEntity<HashSet<String>> findContacts(ContatosCampanhaRequest request) {

        String datas = request.getDatas();

        HashSet<String> contatcs = new HashSet<>();

        for (String data : datas.split(",")) {
            dateConverter.setInitalAndFinalDate(data);
            List<ContatosCampanha> listContatosCampanha =
                    campanhaService.findByCampanhaidAndDataimportacaoBetween
                            (request.getCampanhaid(), dateConverter.getDateInit()
                                    , dateConverter.getDataEnd());

            for (ContatosCampanha campanha : listContatosCampanha) {
                for (String cont : campanha.getContacts()) {
                    contatcs.add(cont);
                }
            }
        }
        return ResponseEntity.ok(contatcs);
    }


//    public void mockDados() {
//        Scanner scan = new Scanner(System.in);
//        int totalGrupo;
//        int totalContatos;
//        List<String> numContatos = new ArrayList<>();
//
//        System.out.print("Quantos grupos deseja criar: ");
//        totalGrupo = scan.nextInt();
//        System.out.print("Quantos contatos para cada grupo deseja criar: ");
//        totalContatos = scan.nextInt();
//
//
//        for(int i=0; i<totalContatos; i++) {
//            numContatos.add("11999999999");
//        }
//
//
//        int num = 0;
//        for(int i=0;  i<totalGrupo;i++) {
//            num++;
//            ContatosCampanha contatos = new ContatosCampanha();
//            contatos.setHostname(null);
//            contatos.setCampanhaid("6303f74b9ade8a9550f90b95");
//            contatos.setNamegroup("GRUPO "+String.valueOf(num)+" PARA TESTE, DEVZAPP");
//            contatos.setIdgrupo(null);
//            contatos.setContacts(numContatos);
//            contatos.setOutcontacts(null);
//            contatos.setNewcontacts(null);
//            contatos.setDataimportacao(1671032253000L);
//            repository.save(contatos);
//        }
//    }
//
//    public void removeDados() {
//        List<ContatosCampanha> byCampanhaid = repository.findByCampanhaid("6303f74b9ade8a9550f90b95");
//        repository.deleteAll(byCampanhaid);
//    }
}

