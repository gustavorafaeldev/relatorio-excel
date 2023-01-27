package apirelatorios.Relatorio.domain.repository;

import apirelatorios.Relatorio.domain.entity.Campanha;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CampanhaRepository extends MongoRepository<Campanha, String> {
}
