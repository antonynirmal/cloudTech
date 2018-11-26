package my.exercise.cryptocurrency.repository;

import my.exercise.cryptocurrency.model.PriceList;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PriceListRepository extends JpaRepository<PriceList, Long> {

    PriceList findByCurrency(String name);
    PriceList findByCurrencyOrderByIdAsc(String name);
}