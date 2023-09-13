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
public class ProviderDAOTest {
    @Autowired
    private ProviderDAO providerDAO;
    @Autowired
    private SessionFactory sessionFactory;

    @Test
    void testGetProviderByName() {
        String name = "Chemicals source";
        List<Provider> providers = providerDAO.findAllMatching(name);
        assertEquals(1, providers.size());
        assertEquals(102, providers.get(0).getId());
    }
}
