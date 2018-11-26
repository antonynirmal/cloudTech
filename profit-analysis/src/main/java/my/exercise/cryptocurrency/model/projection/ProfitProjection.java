package my.exercise.cryptocurrency.model.projection;


import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonProperty;
import my.exercise.cryptocurrency.model.PriceList;
import org.springframework.data.rest.core.config.Projection;

import java.util.Date;

@Projection(types = PriceList.class)
public interface ProfitProjection {
    @JsonProperty("id")
    Long getId();

    String getCurrency();

    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd-MMM-yy")
    Date getDate();

    @JsonFormat(shape = JsonFormat.Shape.NUMBER, pattern = "99999999.99")
    Double getProfit();

    Double getBuy();

    Double getSell();

}
