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
import ru.msu.cmc.webprak.models.Storage;
//import

@SpringBootTest
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
@TestPropertySource(locations="classpath:application.properties")
public class StorageDAOTest {
    @Autowired
    private StorageDAO storageDAO;

    @Test
    void testGetStorageByType() {
        List<Storage> storages = storageDAO.findAllMatching("refrigirator", null);
        assertEquals(101, storages.get(0).getId());
    }

    @Test
    void testGetStorageByFreeSpace() {
        List<Storage> storages = storageDAO.findAllMatching(null, 95L);
        assertEquals(1, storages.size());
        assertEquals(103, storages.get(0).getId());
    }
}
