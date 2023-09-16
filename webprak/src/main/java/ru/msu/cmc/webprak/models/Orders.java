package ru.msu.cmc.webprak.models;

import lombok.*;

import jakarta.persistence.*;
import java.util.Objects;
import java.util.Date;

@Entity
@Table(name = "orders")
@Getter
@Setter
@ToString
@NoArgsConstructor
//@RequiredArgsConstructor
@AllArgsConstructor
public class Orders implements CommonEntity<Long> {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(nullable = false, name = "order_id")
    private Long id;

    @ManyToOne
    @JoinColumn(name = "client_id")
    private Client client;

    @Temporal(TemporalType.TIMESTAMP)
    @Column(nullable = false, name = "departure_date")
    private Date departure_date;

    @ManyToOne
    @JoinColumn(name = "product_id")
    private Product product;

    @Column(nullable = true, name = "amount")
    private Float amount;

    @Column(nullable = true, name = "has_departed")
    private Boolean has_departed;

    public String getProductName() {
        if (this.getProduct() != null) {
            return this.getProduct().getName();
        }

        return null;
    }

    public String getClientName() {
        if (this.getClient() != null) {
            return this.getClient().getName();
        }

        return null;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (obj == null || getClass() != obj.getClass()) return false;
        Orders other = (Orders) obj;
        return Objects.equals(id, other.id)
                && client.equals(other.client)
                && Objects.equals(departure_date, other.departure_date)
                && product.equals(other.product)
                && Objects.equals(amount, other.amount)
                && Objects.equals(has_departed, other.has_departed);
    }

}
