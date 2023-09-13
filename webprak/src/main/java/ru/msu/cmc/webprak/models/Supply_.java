package ru.msu.cmc.webprak.models;

import jakarta.persistence.metamodel.SingularAttribute;
import jakarta.persistence.metamodel.SetAttribute;
import jakarta.persistence.metamodel.StaticMetamodel;
import ru.msu.cmc.webprak.models.*;

import java.util.Date;
@StaticMetamodel(Supply.class)
public class Supply_ {
    public static volatile SingularAttribute<Supply, Long> supply_id;
    public static volatile SingularAttribute<Supply, Provider> provider;
    public static volatile SingularAttribute<Supply, Product> product;
    public static volatile SingularAttribute<Supply, Date> arrival_time;
    public static volatile SingularAttribute<Supply, Float> amount;
    public static volatile SingularAttribute<Supply, Boolean> has_arrived;

}

