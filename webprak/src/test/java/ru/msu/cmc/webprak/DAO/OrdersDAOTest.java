package ru.msu.cmc.webprak.DAO;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
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
public class OrdersDAOTest {
    @Autowired
    private OrdersDAO ordersDAO;
    @Autowired
    private ClientDAO clientDAO;

    @Test
    void testGetOrdersByClient() {
        Client client = clientDAO.getById(101L);
        List<Orders> orders = ordersDAO.findAllMatching(client, null, null, null, null,
                null, null);
        assertEquals(4, orders.size());

    }


    @Test
    void testSupplyIfArrived() {
        List<Orders> orders = ordersDAO.findAllMatching(null, null, null, null, null,
                null, false);
        assertEquals(3, orders.size());
        assertEquals(102, orders.get(0).getId());
        assertEquals(103, orders.get(1).getId());
        assertEquals(104, orders.get(2).getId());
    }

    @Test
    void testGetSupplyByAmount() {
        List<Orders> orders = ordersDAO.findAllMatching(null, null, 29f, 39f, null,
                null, null);
        assertEquals(2, orders.size());
        assertEquals(101, orders.get(0).getId());
        assertEquals(102, orders.get(1).getId());
    }

    @Test
    void testGetSupplyByDepartureTime() {
        Date departureLo;
        Date departureHi;
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MMM-dd", Locale.ENGLISH);
        try {
            departureLo = formatter.parse("2020-Jan-01");
            departureHi = formatter.parse("2020-Jun-01");
        } catch (ParseException e) {
            throw new RuntimeException(e);
        }
        List<Orders> orders = ordersDAO.findAllMatching(null, null, null, null, departureLo,
                departureHi, null);
        assertEquals(3, orders.size());
    }

}
