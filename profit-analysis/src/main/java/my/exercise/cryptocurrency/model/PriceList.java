package my.exercise.cryptocurrency.model;

import lombok.*;

import javax.persistence.*;
import java.util.Date;
import java.util.List;

@NoArgsConstructor
@RequiredArgsConstructor
@Entity
@Getter
@Setter
@Table(name = "price_list")
public class PriceList {

    @Id
    @GeneratedValue
    private Long id;
    @NonNull
    private String currency;
    private Date date;
    @Transient
    private Double profit;
    @Transient
    private Double buy;
    @Transient
    private Double sell;
    @OneToMany(cascade=CascadeType.ALL, fetch = FetchType.EAGER)
    @JoinColumn(name="price_list_id")
    @OrderBy("time ASC")
    private List<Quote> quote;

}



