package ru.msu.cmc.webprak.models;
import lombok.*;

import jakarta.persistence.*;
import java.util.Objects;
import java.util.Date;

@Entity
@Table(name = "storage")
@Getter
@Setter
@ToString
@NoArgsConstructor
//@RequiredArgsConstructor
@AllArgsConstructor
public class Storage implements CommonEntity<Long> {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(nullable = false, name = "storage_id")
    private Long id;

    @Column(nullable = false, name = "storage_type")
    private String storage_type;

    @Column(nullable = false, name = "free_space")
    private Long free_space;

    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (obj == null || getClass() != obj.getClass()) return false;
        Storage other = (Storage) obj;
        return Objects.equals(id, other.id)
                && storage_type.equals(other.storage_type)
                && Objects.equals(free_space, other.free_space);
    }
}
