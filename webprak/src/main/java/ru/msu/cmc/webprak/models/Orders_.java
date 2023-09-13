package ru.msu.cmc.webprak.models;

import jakarta.persistence.metamodel.SingularAttribute;
import jakarta.persistence.metamodel.StaticMetamodel;

import java.util.Date;
@StaticMetamodel(Orders_.class)
public class Orders_ {
    public static volatile SingularAttribute<Orders, Long> order_id;
    public static volatile SingularAttribute<Orders, Client> client;
    public static volatile SingularAttribute<Orders, Product> product;
    public static volatile SingularAttribute<Orders, Date> departure_date;
    public static volatile SingularAttribute<Orders, Float> amount;
    public static volatile SingularAttribute<Orders, Boolean> has_departed;
}
