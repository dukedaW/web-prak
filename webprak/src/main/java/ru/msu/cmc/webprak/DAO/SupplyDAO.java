package ru.msu.cmc.webprak.DAO;

import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.Expression;
import jakarta.persistence.criteria.Root;
import org.hibernate.Session;
import org.springframework.stereotype.Component;
import ru.msu.cmc.webprak.models.Product;
import ru.msu.cmc.webprak.models.Supply;
import ru.msu.cmc.webprak.models.Provider;
import ru.msu.cmc.webprak.models.Supply_;

import java.util.Date;
import java.util.List;

@Component
public class SupplyDAO extends CommonDAO<Supply, Long> {
    public SupplyDAO() {
        super(Supply.class);
    }

    public List<Supply> findAllMatching(Provider provider, Product product, Float amountLo,
                                        Float amountHi,
                                        Date arrivalLo, Date arrivalHi, Boolean has_arrived) {
        CriteriaBuilder builder = sessionFactory.getCriteriaBuilder();
        Session session = sessionFactory.openSession();
        CriteriaQuery<Supply> query = builder.createQuery(Supply.class);
        Root<Supply> root = query.from(Supply.class);

        Expression<Boolean> restr = null;

        if (product != null) {
            Expression<Boolean> n = builder.equal(root.get(Supply_.product), product);
            restr = restr != null ? builder.and(restr, n) : n;
        }

        if (provider != null) {
            Expression<Boolean> n = builder.equal(root.get(Supply_.provider), provider);
            restr = restr != null ? builder.and(restr, n) : n;
        }

        if (arrivalLo!= null) {
            Expression<Boolean> n = builder.greaterThanOrEqualTo(root.get(Supply_.arrival_time), arrivalLo);
            restr = restr != null ? builder.and(restr, n) : n;
        }

        if (arrivalHi!= null) {
            Expression<Boolean> n = builder.lessThanOrEqualTo(root.get(Supply_.arrival_time), arrivalHi);
            restr = restr != null ? builder.and(restr, n) : n;
        }

        if (amountHi!= null) {
            Expression<Boolean> n = builder.lessThanOrEqualTo(root.get(Supply_.amount), amountHi);
            restr = restr != null ? builder.and(restr, n) : n;
        }

        if (amountLo!= null) {
            Expression<Boolean> n = builder.greaterThanOrEqualTo(root.get(Supply_.amount), amountLo);
            restr = restr != null ? builder.and(restr, n) : n;
        }

        if (has_arrived!= null) {
            Expression<Boolean> n = builder.equal(root.get(Supply_.has_arrived), has_arrived);
            restr = restr != null ? builder.and(restr, n) : n;
        }

        if (restr != null) {
            query = query.where(restr);
        }


        List<Supply> result = session.createQuery(query).getResultList();
        session.close();
        return result;
    }

}
