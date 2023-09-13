package ru.msu.cmc.webprak.DAO;

import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.Expression;
import jakarta.persistence.criteria.Root;
import org.hibernate.Session;
import org.springframework.stereotype.Component;
import ru.msu.cmc.webprak.models.Provider;
import ru.msu.cmc.webprak.models.Provider_;

import java.util.List;

@Component
public class ProviderDAO extends CommonDAO<Provider, Long> {

    public ProviderDAO() {
        super(Provider.class);
    }

    public List<Provider> findAllMatching(String name) {
        CriteriaBuilder builder = sessionFactory.getCriteriaBuilder();
        Session session = sessionFactory.openSession();
        CriteriaQuery<Provider> query = builder.createQuery(Provider.class);
        Root<Provider> root = query.from(Provider.class);
        Expression<Boolean> restr = null;

        if (name != null) {
            Expression<Boolean> n = builder.like(root.get(Provider_.name), name);
            restr = restr != null ? builder.and(restr, n) : n;
        }

        if (restr != null) {
            query = query.where(restr);
        }

        List<Provider> result = session.createQuery(query).getResultList();
        session.close();
        return result;
    }
}
