package br.com.ehsolucoes.OSsMap.dbservice.repository;

import br.com.ehsolucoes.OSsMap.dbservice.model.Quote;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface QuotesRepository extends JpaRepository<Quote, Integer> {

    List<Quote> findByUserName(String username);

    public void delete(List<Quote> quotes);
}
