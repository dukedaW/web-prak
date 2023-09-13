package ru.msu.cmc.webprak.DAO;

import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.Expression;
import jakarta.persistence.criteria.Root;
import org.hibernate.Session;
import org.springframework.stereotype.Component;
import ru.msu.cmc.webprak.models.*;

import java.util.Date;
import java.util.List;

import ru.msu.cmc.webprak.models.ProductInstance_;

@Component
public class ProductInstanceDAO extends CommonDAO<ProductInstance, Long> {
    public ProductInstanceDAO() {
        super(ProductInstance.class);
    }
    public List<ProductInstance> findAllMatching(Product product, Float amount, Date arrivalLo, Date arrivalHi, Date expiresLo, Date expiresHi, Supply supply,
                                                 Orders order, Storage storage) {
        CriteriaBuilder builder = sessionFactory.getCriteriaBuilder();
        Session session = sessionFactory.openSession();
        CriteriaQuery<ProductInstance> query = builder.createQuery(ProductInstance.class);
        Root<ProductInstance> root = query.from(ProductInstance.class);

        Expression<Boolean> restr = null;

        if (product != null) {
            Expression<Boolean> n = builder.equal(root.get(ProductInstance_.product), product);
            restr = restr != null ? builder.and(restr, n) : n;
        }
        if (amount != null) {
            Expression<Boolean> n = builder.greaterThanOrEqualTo(root.get(ProductInstance_.amount), amount);
            restr = restr != null ? builder.and(restr, n) : n;
        }

        if (arrivalHi != null) {
            Expression<Boolean> n = builder.lessThanOrEqualTo(root.get(ProductInstance_.arrival_date), arrivalHi);
            restr = restr != null ? builder.and(restr, n) : n;
        }

        if (arrivalLo != null) {
            Expression<Boolean> n = builder.greaterThanOrEqualTo(root.get(ProductInstance_.arrival_date), arrivalLo);
            restr = restr != null ? builder.and(restr, n) : n;
        }

        if (expiresHi != null) {
            Expression<Boolean> n = builder.lessThanOrEqualTo(root.get(ProductInstance_.exparation_date), expiresHi);
            restr = restr != null ? builder.and(restr, n) : n;
        }

        if (expiresLo != null) {
            Expression<Boolean> n = builder.greaterThanOrEqualTo(root.get(ProductInstance_.exparation_date), expiresLo);
            restr = restr != null ? builder.and(restr, n) : n;
        }
        if (expiresHi != null) {
            Expression<Boolean> n = builder.lessThanOrEqualTo(root.get(ProductInstance_.exparation_date), expiresHi);
            restr = restr != null ? builder.and(restr, n) : n;
        }
        if (supply != null) {
            Expression<Boolean> n = builder.equal(root.get(ProductInstance_.supply), supply);
            restr = restr != null ? builder.and(restr, n) : n;
        }
        if (order != null) {
            Expression<Boolean> n = builder.equal(root.get(ProductInstance_.orders), order);
            restr = restr != null ? builder.and(restr, n) : n;
        }

        if (storage != null) {
            Expression<Boolean> n = builder.equal(root.get(ProductInstance_.storage), storage);
            restr = restr != null ? builder.and(restr, n) : n;
        }

        if (restr != null) {
            query = query.where(restr);
        }

        List<ProductInstance> result = session.createQuery(query).getResultList();
        session.close();
        return result;
    }

}
