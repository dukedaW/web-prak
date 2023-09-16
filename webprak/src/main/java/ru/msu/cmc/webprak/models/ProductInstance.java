package ru.msu.cmc.webprak.models;
import lombok.*;

import jakarta.persistence.*;
import java.util.Objects;
import java.util.Date;

@Entity
@Table(name = "product_instance")
@Getter
@Setter
@ToString
@NoArgsConstructor
//@RequiredArgsConstructor
@AllArgsConstructor
public class ProductInstance implements CommonEntity<Long> {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(nullable = false, name = "instance_id")
    private Long id;

    @ManyToOne
    @JoinColumn(name = "product_id")
    private Product product;

    @Column(nullable = false, name = "amount")
    private Float amount;

    @Temporal(TemporalType.TIMESTAMP)
    @Column(nullable = false, name = "exparation_date")
    private Date exparation_date;


    @ManyToOne
    @JoinColumn(name = "supply_id")
    private Supply supply;

    @Temporal(TemporalType.TIMESTAMP)
    @Column(nullable = false, name = "arrival_date")
    private Date arrival_date;
    @ManyToOne
    @JoinColumn(name = "order_id")
    private Orders orders;

    @ManyToOne
    @JoinColumn(name = "storage_id")
    private Storage storage;


    public String getProductName() {
        if (this.getProduct() != null) {
            return this.getProduct().getName();
        }
        return null;
    }

    public Long getSupplyId() {
        if (this.getSupply() != null) {
            return this.getSupply().getId();
        }
        return null;
    }

    public Long getOrderId() {
        if (this.getOrders()!=null) {
            return this.getOrders().getId();
        }
        return null;
    }

    public Long getStorageId() {
        if (this.getStorage() != null) {
            return this.getStorage().getId();
        }
        return null;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (obj == null || getClass() != obj.getClass()) return false;
        ProductInstance other = (ProductInstance) obj;
        return  Objects.equals(id, other.id)
                && product.equals(other.product)
                && Objects.equals(amount, other.amount)
                && Objects.equals(exparation_date, other.exparation_date)
                && supply.equals(other.supply)
                && Objects.equals(arrival_date, other.arrival_date)
                && orders.equals(other.orders)
                && storage.equals(other.storage);
    }

}
