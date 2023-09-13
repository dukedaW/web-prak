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
import ru.msu.cmc.webprak.models.*;

@SpringBootTest
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
@TestPropertySource(locations="classpath:application.properties")
public class SupplyDAOTest {

    @Autowired
    private SupplyDAO supplyDAO;

    @Autowired
    private ProviderDAO providerDAO;

    @Test
    void testGetSupplyByAmount() {
        Float amountLo = 9f;
        Float amountHi = 19f;
        List<Supply> supplies = supplyDAO.findAllMatching(null, null, amountLo, amountHi, null, null,
                null);
        assertEquals(6, supplies.size());
    }

    @Test
    void testGetSupplyByProvider() {
        Provider provider = providerDAO.getById(101L);
        List<Supply> supplies = supplyDAO.findAllMatching(provider, null, null, null, null,
                null, null);
        assertEquals(3, supplies.size());
    }

    @Test
    void testSupplyIfArrived() {
        List<Supply> supplies = supplyDAO.findAllMatching(null, null, null, null, null,
                null, false);
        assertEquals(1, supplies.size());
        assertEquals(105, supplies.get(0).getId());
    }


}
