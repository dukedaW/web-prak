package ru.msu.cmc.webprak.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import ru.msu.cmc.webprak.DAO.ProductDAO;
import ru.msu.cmc.webprak.DAO.ProviderDAO;
import ru.msu.cmc.webprak.DAO.SupplyDAO;
import ru.msu.cmc.webprak.models.Provider;

import java.util.List;

@Controller
public class ProviderController {
    @Autowired
    private final ProviderDAO providerDAO = new ProviderDAO();

    @Autowired
    private final SupplyDAO supplyDAO = new SupplyDAO();

    @GetMapping("/sellers")
    public String sellersListPage(Model model) {
        List<Provider> sellers = (List<Provider>) providerDAO.getAll();
        model.addAttribute("sellers", sellers);
        model.addAttribute("sellerService", providerDAO);
        return "sellers";
    }

    @GetMapping("/seller")
    public String sellerPage(@RequestParam(name = "sellerId") Long sellerId, Model model) {
        Provider seller = providerDAO.getById(sellerId);

        if (seller == null) {
            model.addAttribute("error_msg", "В базе нет поставщика с ID = " + sellerId);
            return "errorPage";
        }

        model.addAttribute("seller", seller);
        model.addAttribute("sellerService", providerDAO);
        model.addAttribute("suppliesService", supplyDAO);
        return "seller";
    }

    @GetMapping("/editSeller")
    public String editSellerPage(@RequestParam(name = "sellerId", required = false) Long sellerId, Model model) {
        if (sellerId == null) {
            model.addAttribute("seller", new Provider());
            model.addAttribute("sellerService", providerDAO);
            return "editSeller";
        }

        Provider seller = providerDAO.getById(sellerId);

        if (seller == null) {
            model.addAttribute("error_msg", "В базе нет поставщика с ID = " + sellerId);
            return "errorPage";
        }

        model.addAttribute("seller", seller);
        model.addAttribute("sellerService", providerDAO);
        return "editSeller";
    }

    @PostMapping("/saveSeller")
    public String saveSellerPage(@RequestParam(name = "sellerId") Long sellerId,
                                 @RequestParam(name = "sellerName") String name,
                                 @RequestParam(name = "sellerPhone", required = false) String phone,
                                 @RequestParam(name = "sellerDescription", required = false) String info,
                                 @RequestParam(name = "sellerAddress", required = false) String address,
                                 Model model) {
        List<Provider> sellers = (List<Provider>) providerDAO.getAll();
        Provider seller = null;
        if (sellerId != 0) {
            seller = providerDAO.getById(sellerId);
        }
        if (sellerId != 0 && seller != null) {
            seller.setName(name);
            if (phone!= null) {
                seller.setPhone(phone);
            }

            if (address!= null) {
                seller.setAddress(address);
            }
            if (info!= null) {
                seller.setDescription(info);
            }
            providerDAO.update(seller);
        } else {
            seller = new Provider();
            seller.setName(name);
            if (phone!= null) {
                seller.setPhone(phone);
            }

            if (address!= null) {
                seller.setAddress(address);
            }
            if (info!= null) {
                seller.setDescription(info);
            }
            providerDAO.save(seller);
        }
        return "index";
    }


    @GetMapping("/searchSeller")
    public String searchEmployee(@RequestParam(required = true) String name,
                                 Model model) {

        List<Provider> sellers = providerDAO.findAllMatching(name);
        model.addAttribute("sellers", sellers);
        model.addAttribute("sellersDAO", providerDAO);
        return "sellers";
    }

    @PostMapping("/removeSeller")
    public String removeSellerPage(@RequestParam(name = "sellerId") Long sellerId) {
        providerDAO.deleteById(sellerId);
        return "redirect:/sellers";
    }
}
