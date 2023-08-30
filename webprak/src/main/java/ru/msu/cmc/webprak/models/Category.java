package ru.msu.cmc.webprak.models;
import jakarta.persistence.*;
import lombok.*;
import java.util.Objects;
@Entity
@Table(name = "category")
@Getter
@Setter
@ToString
@NoArgsConstructor
@RequiredArgsConstructor
@AllArgsConstructor
public class Category implements CommonEntity<Long> {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(nullable = false, name = "category_id")
    private Long id;

    @Column(nullable = false, name = "name")
    @NonNull
    private String name;

    @Column(nullable = false, name = "description")
    @NonNull
    private String description;

    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (obj == null || getClass() != obj.getClass()) return false;
        Category other = (Category) obj;
        return Objects.equals(id, other.id)
                && name.equals(other.name)
                && description.equals(other.description);
    }

}
