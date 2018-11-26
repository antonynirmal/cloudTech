package my.exercise.cryptocurrency.service;

import my.exercise.cryptocurrency.model.PriceList;

import java.util.List;

public interface PriceListService {

    List<PriceList> getQuotes();
    PriceList findbyCurrency(String currency);
    PriceList findByCurrencyOrderByIdAsc(String name);
}
