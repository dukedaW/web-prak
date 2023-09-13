package ru.msu.cmc.webprak.DAO;

import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.Expression;
import jakarta.persistence.criteria.Root;
import org.hibernate.Session;
import org.springframework.stereotype.Component;
import ru.msu.cmc.webprak.models.*;
import ru.msu.cmc.webprak.models.Orders_;

import java.util.Date;
import java.util.List;

@Component
public class OrdersDAO extends CommonDAO<Orders, Long> {

    public OrdersDAO() {
        super(Orders.class);
    }

    public List<Orders> findAllMatching(Client client, Product product, Float amountLo,
                                        Float amountHi,
                                        Date departureLo, Date departureHi, Boolean has_departed) {
        CriteriaBuilder builder = sessionFactory.getCriteriaBuilder();
        Session session = sessionFactory.openSession();
        CriteriaQuery<Orders> query = builder.createQuery(Orders.class);
        Root<Orders> root = query.from(Orders.class);

        Expression<Boolean> restr = null;

        if (client != null) {
            Expression<Boolean> n = builder.equal(root.get(Orders_.client), client);
            restr = restr != null ? builder.and(restr, n) : n;
        }

        if (product != null) {
            Expression<Boolean> n = builder.equal(root.get(Orders_.product), product);
            restr = restr != null ? builder.and(restr, n) : n;
        }
        if (departureLo!= null) {
            Expression<Boolean> n = builder.greaterThanOrEqualTo(root.get(Orders_.departure_date), departureLo);
            restr = restr != null ? builder.and(restr, n) : n;
        }

        if (departureHi!= null) {
            Expression<Boolean> n = builder.lessThanOrEqualTo(root.get(Orders_.departure_date), departureHi);
            restr = restr != null ? builder.and(restr, n) : n;
        }

        if (amountHi!= null) {
            Expression<Boolean> n = builder.lessThanOrEqualTo(root.get(Orders_.amount), amountHi);
            restr = restr != null ? builder.and(restr, n) : n;
        }

        if (amountLo!= null) {
            Expression<Boolean> n = builder.greaterThanOrEqualTo(root.get(Orders_.amount), amountLo);
            restr = restr != null ? builder.and(restr, n) : n;
        }

        if (has_departed!= null) {
            Expression<Boolean> n = builder.equal(root.get(Orders_.has_departed), has_departed);
            restr = restr != null ? builder.and(restr, n) : n;
        }

        if (restr != null) {
            query = query.where(restr);
        }


        List<Orders> result = session.createQuery(query).getResultList();
        session.close();
        return result;
    }


}
