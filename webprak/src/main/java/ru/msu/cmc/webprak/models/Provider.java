package ru.msu.cmc.webprak.models;
import lombok.*;

import jakarta.persistence.*;
import java.util.Objects;

@Entity
@Table(name = "provider")
@Getter
@Setter
@ToString
@NoArgsConstructor
//@RequiredArgsConstructor
@AllArgsConstructor
public class Provider implements CommonEntity<Long> {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(nullable = false, name = "provider_id")
    private Long id;

    @Column(nullable = false, name = "name")
    String name;
    @Column(nullable = false, name = "phone")
    String phone;

    @Column(nullable = false, name = "description")
    String description;

    @Column(nullable = false, name = "address")
    String address;

    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (obj == null || getClass() != obj.getClass()) return false;
        Provider other = (Provider) obj;
        return Objects.equals(id, other.id)
                && name.equals(other.name)
                && description.equals(other.description)
                && phone.equals(other.phone)
                && address.equals(other.address);
    }

}
