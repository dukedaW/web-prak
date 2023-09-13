package ru.msu.cmc.webprak.models;
import jakarta.persistence.metamodel.SingularAttribute;
import jakarta.persistence.metamodel.SetAttribute;
import jakarta.persistence.metamodel.StaticMetamodel;
import ru.msu.cmc.webprak.models.*;

import java.util.Date;
@StaticMetamodel(Product.class)
public class Product_ {
    public static volatile SingularAttribute<Product, Long> product_id;
    public static volatile SingularAttribute<Product, String> name;
    public static volatile SingularAttribute<Product, String> description;
    public static volatile SingularAttribute<Product, Category> category;

}
