package my.exercise.cryptocurrency.model;

import lombok.*;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Builder
@NoArgsConstructor
@AllArgsConstructor
@Setter
@Getter
@Entity
public class Quote {

    @Id
    @GeneratedValue
    private Long id;
    @NonNull
    private String time;
    @NonNull
    private Double price;
}