package ru.msu.cmc.webprak.DAO;
import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.Expression;
import jakarta.persistence.criteria.Root;
import org.hibernate.Session;
import org.springframework.stereotype.Component;
import ru.msu.cmc.webprak.models.Category;
import ru.msu.cmc.webprak.models.Product;
import ru.msu.cmc.webprak.models.Product_;

import java.util.List;

@Component
public class ProductDAO extends CommonDAO<Product, Long> {
    public ProductDAO() {
        super(Product.class);
    }

//    public List<Product> getProductsByCategory(Category category) {
//        try (Session session = sessionFactory.openSession()) {
//            Query<Product> query = session.createQuery("FROM Product WHERE category = :categoryId", Product.class)
//                    .setParameter("categoryId", category);
//
//            return query.getResultList();
//        }
//    }

    public List<Product> findAllMatching(String name, Category category) {
        CriteriaBuilder builder = sessionFactory.getCriteriaBuilder();
        Session session = sessionFactory.openSession();
        CriteriaQuery<Product> query = builder.createQuery(Product.class);
        Root<Product> root = query.from(Product.class);
        Expression<Boolean> restr = null;

        if (name != null) {
            Expression<Boolean> n = builder.like(root.get(Product_.name), name);
            restr = restr != null ? builder.and(restr, n) : n;
        }

        if (category != null) {
            Expression<Boolean> n = builder.equal(root.get(Product_.category), category);
            restr = restr != null ? builder.and(restr, n) : n;
        }

        if (restr != null) {
            query = query.where(restr);
        }

        List<Product> result = session.createQuery(query).getResultList();
        session.close();
        return result;
    }
}
