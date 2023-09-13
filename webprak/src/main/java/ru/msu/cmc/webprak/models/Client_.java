package ru.msu.cmc.webprak.models;
import jakarta.persistence.metamodel.SingularAttribute;
import jakarta.persistence.metamodel.SetAttribute;
import jakarta.persistence.metamodel.StaticMetamodel;
import ru.msu.cmc.webprak.models.*;
@StaticMetamodel(Client.class)
public class Client_ {
    public static volatile SingularAttribute<Client, Long> client_id;
    public static volatile SingularAttribute<Client, String> name;
    public static volatile SingularAttribute<Client, String> phone;
    public static volatile SingularAttribute<Client, String> address;
    public static volatile SingularAttribute<Client, String> description;

}
