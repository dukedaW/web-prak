package ru.msu.cmc.webprak.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.testng.TestNGAntTask;
import ru.msu.cmc.webprak.DAO.StorageDAO;
import ru.msu.cmc.webprak.models.Client;
import ru.msu.cmc.webprak.models.Storage;

import java.util.List;

@Controller
public class StorageController {

    @Autowired
    private final StorageDAO storageDAO = new StorageDAO();

   @GetMapping("/storages")
    public String storagesPage(Model model) {
        List<Storage> storages = (List<Storage>) storageDAO.getAll();

       model.addAttribute("storages", storages);
       model.addAttribute("storageService", storageDAO);
       return "storages";
    }

    @GetMapping("/storage")
    public String storagePage(@RequestParam(name = "storageId") Long storageId, Model model) {
        Storage storage = storageDAO.getById(storageId);

        if (storage == null) {
            model.addAttribute("error_msg", "В базе нет заказчика с ID = " + storageId);
            return "errorPage";
        }

        model.addAttribute("storage", storage);
        model.addAttribute("storageService", storageDAO);
        model.addAttribute("storageService", storageDAO);
        return "storage";
    }

    @GetMapping("/editStorage")
    public String editStoragePage(@RequestParam(name = "storageId", required = false) Long Id, Model model) {
        if (Id == null) {
            model.addAttribute("storage", new Storage());
            model.addAttribute("storageService", storageDAO);
            return "editStorage";
        }

        Storage storage = storageDAO.getById(Id);

        if (storage == null) {
            model.addAttribute("error_msg", "В базе нет скалада с ID = " + Id);
            return "errorPage";
        }

        model.addAttribute("storage", storage);
        model.addAttribute("storageService", storageDAO);
        return "editStorage";
    }

    @PostMapping("/saveStorage")
    public String saveStoragePage(@RequestParam(name = "storageId") Long storageId,
                                 @RequestParam(name = "storageType") String storageType,
                                 @RequestParam(name = "storageFreeSpace") Long freeSpace,
                                 Model model) {

        Storage storage = null;
        if (storageId != 0) {
            storage = storageDAO.getById(storageId);
        }

        if (storageId != 0 && storage != null) {
            storage.setStorage_type(storageType);
            storage.setFree_space(freeSpace);

            storageDAO.update(storage);
        } else {
            storage = new Storage();
            storage.setStorage_type(storageType);
            storage.setFree_space(freeSpace);

            storageDAO.save(storage);
        }
        return "index";
    }

    @GetMapping("/searchStorage")
    public String searchStorage(@RequestParam(name = "storageType", required = false) String type,
            @RequestParam(name = "storageSpaceLo", required = false) Long spaceLo,
            Model model) {

       List<Storage> storages = storageDAO.findAllMatching(type, spaceLo);

        model.addAttribute("storages", storages);
        model.addAttribute("storagesDAO", storageDAO);
        return "storages";
    }

    @PostMapping("/removeStorage")
    public String removeStoragePage(@RequestParam(name = "storageId") Long storageId) {
        storageDAO.deleteById(storageId);
        return "redirect:/storages";
    }

}
