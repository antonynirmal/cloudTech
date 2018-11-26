package my.exercise.cryptocurrency.service;

import lombok.extern.slf4j.Slf4j;
import my.exercise.cryptocurrency.exception.PriceListException;
import my.exercise.cryptocurrency.model.PriceList;
import my.exercise.cryptocurrency.model.Quote;
import my.exercise.cryptocurrency.repository.PriceListRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.TreeMap;

@Slf4j
@Service
public class PriceListServiceServiceImpl implements PriceListService {

    PriceListRepository priceListRepository;

    PriceList priceList;

    @Autowired
    public PriceListServiceServiceImpl(PriceListRepository priceListRepository) {
        this.priceListRepository = priceListRepository;
    }

    @Override
    public List<PriceList> getQuotes() {

        List<PriceList> priceList = priceListRepository.findAll();
        return priceList;
    }

    @Override
    public PriceList findbyCurrency(String currency) {
        return  priceListRepository.findByCurrency(currency);
    }

    @Override
    public PriceList findByCurrencyOrderByIdAsc(String currency) {
        PriceList priceList = priceListRepository.findByCurrency(currency);
        List<Quote> quote = priceList.getQuote();
        Map<String, Double> items = new TreeMap<>();
        quote.forEach(item -> items.put(item.getTime(),item.getPrice()));
        if(Optional.ofNullable(items).isPresent())
            priceList = maxProfit(items, priceList);
        else
            new PriceListException("Invalid data or priceList is empty");
        return priceList;
    }

    private PriceList maxProfit(Map<String, Double> quote, PriceList priceList) {
        Double minimum = -1.0, diff = -1.0;
        Double iChange = -1.0;
        Double value;
        int count = 0;

        for(Map.Entry<String, Double> v: quote.entrySet()) {
            value = v.getValue();
            if (count == 0) {
                minimum = value;
                count++;
            } else {

                diff = Math.max(diff, v.getValue() - minimum);

                if (!diff.equals(iChange) || diff.equals(-1.0)) {
                    iChange = diff;
                    priceList.setBuy(minimum);
                    priceList.setSell(value);
                }
                minimum = Math.min(minimum, value);
                count++;
            }
        }
        System.out.print("  Diff "+ diff );

        priceList.setProfit(diff);
        return priceList;
    }

}
