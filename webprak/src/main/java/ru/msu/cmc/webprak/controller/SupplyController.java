package ru.msu.cmc.webprak.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import ru.msu.cmc.webprak.DAO.*;
import ru.msu.cmc.webprak.models.*;

import java.util.Date;
import java.util.List;

@Controller
public class SupplyController {

    @Autowired
    private final SupplyDAO supplyDAO = new SupplyDAO();

    @Autowired
    private final ProviderDAO providerDAO = new ProviderDAO();

    @Autowired
    private final ProductDAO productDAO = new ProductDAO();


    @GetMapping("/supplies")
    public String suppliesListPage(Model model) {
        List<Supply> supplies = (List<Supply>) supplyDAO.getAll();
        model.addAttribute("supplies", supplies);
        model.addAttribute("supplyService", supplyDAO);
        return "supplies";
    }

    @GetMapping("/supply")
    public String supplyPage(@RequestParam(name = "supplyId") Long supplyId, Model model) {
        Supply supply = supplyDAO.getById(supplyId);

        if (supply == null) {
            model.addAttribute("error_msg", "В базе нет поставки с ID = " + supplyId);
            return "errorPage";
        }

        model.addAttribute("supply", supply);
        model.addAttribute("supplyService", supplyDAO);
        //model.addAttribute("goodsService", productDAO);
        return "supply";
    }

    @GetMapping("/editSupply")
    public String editSupply(@RequestParam(name = "supplyId", required = false) Long supplyId, Model model) {
        if (supplyId == null) {
            Supply supply = new Supply(1110L);
            model.addAttribute("supply", supply);
            //model.addAttribute("supplyService", supplyDAO);
            return "editSupply";
        }

        Supply supply = supplyDAO.getById(supplyId);

        if (supply == null) {
            model.addAttribute("error_msg", "В базе нет поставки с ID = " + supplyId);
            return "errorPage";
        }

        model.addAttribute("supply", supply);
        model.addAttribute("supplyService", supplyDAO);
        //model.addAttribute("wcService", wcService);
        return "editSupply";
    }

    @PostMapping("/saveSupply")
    public String saveSupplyPage(@RequestParam(name = "supplyId", required = false) Long supplyId,
                                 @RequestParam(name = "supplyDateTime", required = false) @DateTimeFormat(pattern = "yyyy-MM-dd") Date date,
                                 @RequestParam(name = "supplyProductName", required = false) String productName,
                                 @RequestParam(name = "supplyAmount", required = false) Float amount,
                                 @RequestParam(name = "supplySellerName", required = false) String sellerName,
                                 @RequestParam(name = "supplyArrived", required = false) Boolean arrived,
                                 Model model) {

        Supply supply = supplyDAO.getById(supplyId);

        if (supply != null) {
            supply.setArrival_time(date);
            if (sellerName!= null) {
                if (providerDAO.findAllMatching(sellerName) != null && providerDAO.findAllMatching(sellerName).size() == 1) {

                    supply.setProvider(providerDAO.findAllMatching(sellerName).get(0));
                }
            }

            if (amount != null) {
                supply.setAmount(amount);
            }

            if (productName != null) {
                if (productDAO.findAllMatching(productName, null) != null && productDAO.findAllMatching(productName, null).size() == 1) {
                    supply.setProduct(productDAO.findAllMatching(productName, null).get(0));
                }
            }
            if (arrived != null) {
                supply.setHas_arrived(arrived);
            }
            supplyDAO.update(supply);
        } else {
            Provider provider = null;
            if (!providerDAO.findAllMatching(sellerName).isEmpty()) {
                provider = providerDAO.findAllMatching(sellerName).get(0);
            }

            Product product = null;

            if (!productDAO.findAllMatching(productName, null).isEmpty()) {
                product = productDAO.findAllMatching(productName, null).get(0);
            }

            supply = new Supply(supplyId, provider, product, amount, date, arrived);

            supplyDAO.save(supply);
        }
        return "index";
    }

    @GetMapping("/searchSupply")
    public String searchSupply( @RequestParam(value = "sellerName" , required = false) String sellerName,
                                @RequestParam (value = "productName", required = false) String productName,
                                @RequestParam(name = "dateLo", required = false) @DateTimeFormat(pattern = "yyyy-MM-dd") Date dateLo,
                                @RequestParam(name = "dateHi", required = false) @DateTimeFormat(pattern = "yyyy-MM-dd") Date dateHi,
                                @RequestParam(name = "amountLo", required = false) Float amountLo,
                                @RequestParam(name = "amountHi", required = false) Float amountHi,
                                @RequestParam(name = "arrived", required = false) Boolean arrived,
                                 Model model) {

        List<Provider> sellers = providerDAO.findAllMatching(sellerName);
        List<Product> products = productDAO.findAllMatching(productName, null);

        Provider seller = null;
        if (!sellers.isEmpty()) {
            seller = sellers.get(0);
        }
        Product product = null;
        if (!products.isEmpty()) {
            product = products.get(0);
        }

        List<Supply> supplies = supplyDAO.findAllMatching(seller, product,
                amountLo, amountHi, dateLo, dateHi, arrived);

        model.addAttribute("supplies", supplies);
        model.addAttribute("suppliesDAO", supplyDAO);
        return "supplies";
    }



    @PostMapping("/removeSupply")
    public String removeSupplyPage(@RequestParam(name = "supplyId") Long supplyId) {
        supplyDAO.deleteById(supplyId);
        return "redirect:/supplies";
    }
}
