package ru.msu.cmc.webprak.DAO;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.springframework.test.util.AssertionErrors.assertTrue;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.junit.jupiter.api.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.TestPropertySource;
import ru.msu.cmc.webprak.models.Category;

@SpringBootTest
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
@TestPropertySource(locations="classpath:application.properties")
public class CategoryDAOTest {

    @Autowired
    private CategoryDAO categoryDAO;
    @Autowired
    private SessionFactory sessionFactory;

    @Test
    void testSimpleManipulation() {
        List<Category> categoryListAll = (List<Category>) categoryDAO.getAll();
        assertEquals(4, categoryListAll.size());
    }
    @Test
    void testGetCategoryByName() {
        List<Category> categories = categoryDAO.findAllMatching("Consumer electronics");
        assertEquals(1, categories.size());
    }

    @Test
    void testSaveAndDelete() {
        Category cat = new Category(112L, "G", "D");
        Long res = categoryDAO.save(cat);
        Category saved = categoryDAO.findAllMatching("G").get(0);
        assertEquals(cat, saved);
        categoryDAO.delete(saved);
        assertEquals(true, categoryDAO.findAllMatching("G").isEmpty());
    }

    @Test
    void testDeleteById() {
        Category cat = new Category(112L, "G", "D");
        Long res = categoryDAO.save(cat);
        System.out.println(res);
        Category saved = categoryDAO.findAllMatching("G").get(0);
        assertEquals(cat, saved);

        categoryDAO.deleteById(res);
        assertNull(categoryDAO.getById(res));
    }

    @Test
    void testUpdate() {
        Category cat = new Category(112L, "G", "D");
        Long res = categoryDAO.save(cat);
        System.out.println(res);
        Category saved = categoryDAO.findAllMatching("G").get(0);
        assertEquals(cat, saved);

        saved.setDescription("Dave");
        categoryDAO.update(saved);
        Category updated = categoryDAO.getById(res);
        assertEquals(saved, updated);
        assertNotEquals(updated, cat);

        saved.setName("Gosha");
        categoryDAO.update(saved);
        Category updated1 = categoryDAO.getById(res);
        assertEquals(saved, updated1);
        assertNotEquals(updated1, updated);

        categoryDAO.deleteById(res);
        assertNull(categoryDAO.getById(res));

    }

    @Test
    void testSaveCollection() {
        Category cat1 = new Category(112L, "G", "D");
        Category cat2 = new Category(113L, "A", "B");

        List<Category> categories = new ArrayList<Category>();
        categories.add(cat1);
        categories.add(cat2);

        categoryDAO.saveCollection(categories);

        assertEquals(1, categoryDAO.findAllMatching("G").size());
        assertEquals(1, categoryDAO.findAllMatching("A").size());

        Long id1 = categoryDAO.findAllMatching("G").get(0).getId();
        Long id2 = categoryDAO.findAllMatching("A").get(0).getId();
        categoryDAO.deleteById(id1);;
        categoryDAO.deleteById(id2);
        assertNull(categoryDAO.getById(id1));
        assertNull(categoryDAO.getById(id2));
    }


}

