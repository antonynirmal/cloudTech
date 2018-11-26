package my.exercise.cryptocurrency.model.projection;


import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonProperty;
import my.exercise.cryptocurrency.model.PriceList;
import my.exercise.cryptocurrency.model.Quote;
import org.springframework.data.rest.core.config.Projection;

import java.util.Date;
import java.util.List;

@Projection(name = "default", types = PriceList.class)
public interface PriceProjection {
    @JsonProperty("id")
    Long getId();

    String getCurrency();

    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd-MMM-yy")
    Date getDate();

    List<Quote> getQuote();

}
