package ru.msu.cmc.webprak.DAO;
import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.Expression;
import jakarta.persistence.criteria.Root;
import org.hibernate.Session;
import org.springframework.stereotype.Component;
import ru.msu.cmc.webprak.models.Category;
import ru.msu.cmc.webprak.models.Category_;

import java.util.List;
@Component
public class CategoryDAO extends CommonDAO<Category, Long> {
    public CategoryDAO() {

        super(Category.class);
    }

    public List<Category> findAllMatching(String name) {
        CriteriaBuilder builder = sessionFactory.getCriteriaBuilder();
        Session session = sessionFactory.openSession();
        CriteriaQuery<Category> query = builder.createQuery(Category.class);
        Root<Category> root = query.from(Category.class);
        Expression<Boolean> restr = null;

        if (name != null) {
            Expression<Boolean> n = builder.like(root.get(Category_.name), name);
            restr = restr != null ? builder.and(restr, n) : n;
        }

        if (restr != null) {
            query = query.where(restr);
        }

        List<Category> result = session.createQuery(query).getResultList();
        session.close();
        return result;
    }
}
