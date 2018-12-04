package br.com.ehsolucoes.OSsMap.dbservice.resource;

import br.com.ehsolucoes.OSsMap.dbservice.model.Quote;
import br.com.ehsolucoes.OSsMap.dbservice.model.Quotes;
import br.com.ehsolucoes.OSsMap.dbservice.repository.QuotesRepository;
import com.sun.org.apache.xpath.internal.operations.Quo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/rest/db")
public class DbServiceResource {

    @Autowired
    private QuotesRepository quotesRepository;

    public DbServiceResource(QuotesRepository quotesRepository){
        this.quotesRepository = quotesRepository;
    }

    @GetMapping("/{username}")
    public List<String> getQuotes(@PathVariable("username") final String username){

        return quotesRepository.findByUserName(username)
            .stream()
            .map(quote -> {
                return quote.getQuote();
            })
            .collect(Collectors.toList());
    }

    private List<String> getQuotesByUserName(@PathVariable("username") String username){
        return quotesRepository.findByUserName(username)
                .stream()
                .map(quote -> {
                    return quote.getQuote();
                })
                .collect(Collectors.toList());
    }

    @PostMapping("/add")
    public List<String> add(@RequestBody final Quotes quotes){
        quotes.getQuotes()
                .stream()
                .map(quote -> new Quote(quotes.getUserName(), quote))
                .forEach(quote -> quotesRepository.save(quote));
        return getQuotesByUserName(quotes.getUserName());
    }

    @PostMapping("/delete/{username}")
    public boolean delete(@PathVariable("username") final String username){
        List<Quote> quotes = quotesRepository.findByUserName(username);
        quotesRepository.delete(quotes);
        return getQuotesByUserName(username);
    }


}
