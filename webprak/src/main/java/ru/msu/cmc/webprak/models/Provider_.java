package ru.msu.cmc.webprak.models;
import jakarta.persistence.metamodel.SingularAttribute;
import jakarta.persistence.metamodel.SetAttribute;
import jakarta.persistence.metamodel.StaticMetamodel;
import ru.msu.cmc.webprak.models.*;

@StaticMetamodel(Provider.class)
public class Provider_ {
    public static volatile SingularAttribute<Provider, Long> provider_id;
    public static volatile SingularAttribute<Provider, String> name;
    public static volatile SingularAttribute<Provider, String> phone;
    public static volatile SingularAttribute<Provider, String> address;
    public static volatile SingularAttribute<Provider, String> description;
}
