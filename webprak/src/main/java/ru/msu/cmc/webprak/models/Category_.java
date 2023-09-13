package ru.msu.cmc.webprak.models;
import jakarta.persistence.metamodel.SingularAttribute;
import jakarta.persistence.metamodel.SetAttribute;
import jakarta.persistence.metamodel.StaticMetamodel;
import org.springframework.stereotype.Component;
import ru.msu.cmc.webprak.models.*;
@StaticMetamodel(Category.class)
public class Category_ {
    public static volatile SingularAttribute<Category, Long> category_id;
    public static volatile SingularAttribute<Category, String> name;
    public static volatile SingularAttribute<Category, String> description;

}
