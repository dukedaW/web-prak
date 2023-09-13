package ru.msu.cmc.webprak.models;
import jakarta.persistence.metamodel.SingularAttribute;
import jakarta.persistence.metamodel.SetAttribute;
import jakarta.persistence.metamodel.StaticMetamodel;
import ru.msu.cmc.webprak.models.*;

import java.util.Date;
@StaticMetamodel(Storage.class)
public class Storage_ {
    public static volatile SingularAttribute<Storage, Long> storage_id;
    public static volatile SingularAttribute<Storage, String> storage_type;
    public static volatile SingularAttribute<Storage, Long> free_space;


}
