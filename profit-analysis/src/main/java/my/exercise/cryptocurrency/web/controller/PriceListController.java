package my.exercise.cryptocurrency.web.controller;

import lombok.extern.slf4j.Slf4j;
import my.exercise.cryptocurrency.exception.PriceListException;
import my.exercise.cryptocurrency.model.PriceList;
import my.exercise.cryptocurrency.repository.PriceListRepository;
import my.exercise.cryptocurrency.service.PriceListService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Collection;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api")
@Slf4j
public class PriceListController {

    private PriceListRepository priceListRepository;
    private PriceListService priceListService;

    public PriceListController(PriceListService priceListService) {
        this.priceListService = priceListService;
    }

    @GetMapping("/quotes")
    Collection<PriceList> priceLists() {
        List<PriceList> priceList = priceListService.getQuotes();
        if(!Optional.ofNullable(priceList).isPresent()) new PriceListException("Data Unavailable or Invalid request!");
        return  priceList;
    }

    @GetMapping("/quotes/{currency}")
    ResponseEntity<?> getQuotesByCurrency(@PathVariable String currency) {
        Optional<PriceList> priceList = Optional.ofNullable(priceListService.findbyCurrency(currency));
        if(!priceList.isPresent()) new PriceListException("Data Unavailable or Invalid request!");
        return priceList.map(response -> ResponseEntity.ok().body(response))
                .orElse(new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    @GetMapping("/profits/{currency}")
    ResponseEntity<?> getMaxProfitByCurrency(@PathVariable String currency) {

        Optional<PriceList> priceList = Optional.ofNullable(priceListService.findByCurrencyOrderByIdAsc(currency));
        if(!priceList.isPresent()) new PriceListException("Data Unavailable or Invalid request!");
        return priceList.map(response -> ResponseEntity.ok().body(response))
                .orElse(new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }
}