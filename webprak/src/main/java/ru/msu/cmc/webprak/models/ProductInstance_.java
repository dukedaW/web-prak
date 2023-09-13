package ru.msu.cmc.webprak.models;
import jakarta.persistence.metamodel.SingularAttribute;
import jakarta.persistence.metamodel.SetAttribute;
import jakarta.persistence.metamodel.StaticMetamodel;
import ru.msu.cmc.webprak.models.*;

import java.util.Date;

@StaticMetamodel(ProductInstance.class)
public class ProductInstance_ {
        public static volatile SingularAttribute<ProductInstance, Long> instance_id;
        public static volatile SingularAttribute<ProductInstance, Product> product;
        public static volatile SingularAttribute<ProductInstance, Float> amount;
        public static volatile SingularAttribute<ProductInstance, Date> exparation_date;
        public static volatile SingularAttribute<ProductInstance, Date> arrival_date;
        public static volatile SingularAttribute<ProductInstance, Supply> supply;
        public static volatile SingularAttribute<ProductInstance, Orders> orders;
        public static volatile SingularAttribute<ProductInstance, Storage> storage;

}
