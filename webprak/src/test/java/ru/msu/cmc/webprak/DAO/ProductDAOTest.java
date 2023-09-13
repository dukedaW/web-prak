package ru.msu.cmc.webprak.DAO;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.junit.jupiter.api.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.TestPropertySource;
import ru.msu.cmc.webprak.models.Category;
import ru.msu.cmc.webprak.models.Product;
//import

@SpringBootTest
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
@TestPropertySource(locations="classpath:application.properties")
public class ProductDAOTest {
    @Autowired
    private ProductDAO productDAO;

    @Autowired
    private CategoryDAO categoryDAO;
    @Autowired
    private SessionFactory sessionFactory;

    @Test
    void testGetProductByCategory() {
        List<Category> categoryListAll = (List<Category>) categoryDAO.getAll();
        List<Product> products = productDAO.findAllMatching(null, categoryListAll.get(0)); // category = Food
        assertEquals(2, products.size());
    }

    @Test
    void testGetProductByName() {
        String name = "Potatoes";
        List<Product> products = productDAO.findAllMatching(name, null);
        assertEquals(1, products.size());
        assertEquals(101, products.get(0).getId());
    }

}
