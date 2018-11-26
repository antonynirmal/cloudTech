package my.exercise.cryptocurrency.repository;

import my.exercise.cryptocurrency.model.Quote;
import org.springframework.data.jpa.repository.JpaRepository;

public interface QuoteRepository extends JpaRepository<Quote, Long> {
    Quote findById(String id);
}