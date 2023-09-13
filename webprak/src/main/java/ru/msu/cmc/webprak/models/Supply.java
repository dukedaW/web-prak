package ru.msu.cmc.webprak.models;
import lombok.*;

import jakarta.persistence.*;
import java.util.Objects;
import java.util.Date;

@Entity
@Table(name = "supply")
@Getter
@Setter
@ToString
@NoArgsConstructor
//@RequiredArgsConstructor
@AllArgsConstructor
public class Supply implements CommonEntity<Long> {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(nullable = false, name = "supply_id")
    private Long id;

    @ManyToOne
    @JoinColumn(name = "provider_id")
    private Provider provider;

    @ManyToOne
    @JoinColumn(name = "product_id")
    private Product product;

    @Column(nullable = false, name = "amount")
    private float amount;

    @Temporal(TemporalType.TIMESTAMP)
    @Column(nullable = false, name = "arrival_time")
    private Date arrival_time;

    @Column(nullable = false, name = "has_arrived")
    private Boolean has_arrived;

    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (obj == null || getClass() != obj.getClass()) return false;
        Supply other = (Supply) obj;
        return Objects.equals(id, other.id)
                && provider.equals(other.provider)
                && product.equals(other.product)
                && Objects.equals(amount, other.amount)
                && Objects.equals(arrival_time, other.arrival_time)
                && Objects.equals(has_arrived, other.has_arrived);
    }

}
