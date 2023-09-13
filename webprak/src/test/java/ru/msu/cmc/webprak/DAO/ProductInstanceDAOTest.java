package ru.msu.cmc.webprak.DAO;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.Date;
import java.util.List;
import java.util.Locale;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.junit.jupiter.api.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.TestPropertySource;
import ru.msu.cmc.webprak.models.*;

@SpringBootTest
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
@TestPropertySource(locations="classpath:application.properties")
public class ProductInstanceDAOTest {
    @Autowired
    private ProductInstanceDAO productInstanceDAO;
    @Autowired
    private ProductDAO productDAO;
    @Autowired
    private OrdersDAO ordersDAO;
    @Autowired
    private SupplyDAO supplyDAO;
    @Autowired
    private SessionFactory sessionFactory;

    @Test
    void testGetInstancesByExparationDate() {
        Date expiresLo;
        Date expiresHi;
        Long potatoesId = 101L;
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MMM-dd", Locale.ENGLISH);
        try {
            expiresLo = formatter.parse("2021-May-01");
            expiresHi = formatter.parse("2021-Jul-01");
        } catch (ParseException e) {
            throw new RuntimeException(e);
        }
        Product product = productDAO.getById(potatoesId);
        List<ProductInstance> instances = productInstanceDAO.findAllMatching(product, null, null, null, expiresLo, expiresHi, null,
                null, null);
        assertEquals(4, instances.size());
    }

    @Test
    void testGetInstancesByArrivalDate() {
        Date arrivalLo;
        Date arrivalHi;
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MMM-dd", Locale.ENGLISH);
        try {
            arrivalLo = formatter.parse("2020-Oct-01");
            arrivalHi = formatter.parse("2021-Jan-01");
        } catch (ParseException e) {
            throw new RuntimeException(e);
        }
        List<ProductInstance> instances = productInstanceDAO.findAllMatching(null, null, arrivalLo, arrivalHi, null, null, null,
                null, null);
        assertEquals(9, instances.size());
    }

    @Test
    void testGetInstancesByOrder() {
        Orders order = ordersDAO.getById(103L);
        List<ProductInstance> instances = productInstanceDAO.findAllMatching(null, null, null, null, null, null, null,
                order, null);
        assertEquals(3, instances.size());
    }

    @Test
    void testGetInstancesBySupply() {
        Supply supply = supplyDAO.getById(101L);
        List<ProductInstance> instances = productInstanceDAO.findAllMatching(null, null, null, null, null, null, supply,
                null, null);
        assertEquals(4, instances.size());
    }
}
