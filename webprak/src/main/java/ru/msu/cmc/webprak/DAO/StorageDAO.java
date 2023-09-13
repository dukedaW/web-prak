package ru.msu.cmc.webprak.DAO;

import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.Expression;
import jakarta.persistence.criteria.Root;
import org.hibernate.Session;
import org.springframework.stereotype.Component;
import ru.msu.cmc.webprak.models.Storage;
import ru.msu.cmc.webprak.models.Storage_;

import java.util.List;

@Component
public class StorageDAO extends CommonDAO<Storage, Long> {

    public StorageDAO() {
        super(Storage.class);
    }

    public List<Storage> findAllMatching(String storage_type, Long free_space_min) {
        CriteriaBuilder builder = sessionFactory.getCriteriaBuilder();
        Session session = sessionFactory.openSession();
        CriteriaQuery<Storage> query = builder.createQuery(Storage.class);
        Root<Storage> root = query.from(Storage.class);
        Expression<Boolean> restr = null;

        if (storage_type != null) {
            Expression<Boolean> n = builder.equal(root.get(Storage_.storage_type), storage_type);
            restr = restr != null ? builder.and(restr, n) : n;
        }

        if (free_space_min != null) {
            Expression<Boolean> n = builder.greaterThanOrEqualTo(root.get(Storage_.free_space), free_space_min);
            restr = restr != null ? builder.and(restr, n) : n;
        }

        if (restr != null) {
            query = query.where(restr);
        }

        List<Storage> result = session.createQuery(query).getResultList();
        session.close();
        return result;
    }

}
