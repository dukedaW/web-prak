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
@RequiredArgsConstructor
@AllArgsConstructor
public class Supply implements CommonEntity<Long> {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(nullable = false, name = "supply_id")
    @NonNull
    private Long id;

    @ManyToOne
    @JoinColumn(name = "provider_id")
    private Provider provider;

    @ManyToOne
    @JoinColumn(name = "product_id")
    private Product product;

    @Column(name = "amount")
    private Float amount;

    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "arrival_time")
    private Date arrival_time;

    @Column(name = "has_arrived")
    private Boolean has_arrived;

    public String getProductName() {
        if (this.getProduct() != null) {
            return this.getProduct().getName();
        }

        return null;
    }

    public String getProviderName() {
        if (this.getProvider() != null) {
            return this.getProvider().getName();
        }

        return null;
    }

    public Long getProviderId() {
        if (this.getProvider() != null) {
            return this.getProvider().getId();
        }
        return null;
    }

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
