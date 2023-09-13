package ru.msu.cmc.webprak.DAO;

import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.Expression;
import jakarta.persistence.criteria.Root;
import org.hibernate.Session;
import org.springframework.stereotype.Component;
import ru.msu.cmc.webprak.models.Client;
import ru.msu.cmc.webprak.models.Client_;

import java.util.List;

@Component
public class ClientDAO extends CommonDAO<Client, Long> {
    public ClientDAO() {
        super(Client.class);
    }

    public List<Client> findAllMatching(String name) {
        CriteriaBuilder builder = sessionFactory.getCriteriaBuilder();
        Session session = sessionFactory.openSession();
        CriteriaQuery<Client> query = builder.createQuery(Client.class);
        Root<Client> root = query.from(Client.class);
        Expression<Boolean> restr = null;

        if (name != null) {
            Expression<Boolean> n = builder.like(root.get(Client_.name), name);
            restr = restr != null ? builder.and(restr, n) : n;
        }

        if (restr != null) {
            query = query.where(restr);
        }

        List<Client> result = session.createQuery(query).getResultList();
        session.close();
        return result;
    }
}
