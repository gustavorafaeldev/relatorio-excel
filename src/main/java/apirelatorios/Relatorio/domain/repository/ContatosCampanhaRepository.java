package apirelatorios.Relatorio.domain.repository;

import apirelatorios.Relatorio.domain.entity.ContatosCampanha;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ContatosCampanhaRepository extends MongoRepository<ContatosCampanha, String> {

    List<ContatosCampanha> findByCampanhaidAndDataimportacaoBetween(String campanhaid
            , Long dateInit, Long dateEnd);

}
