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
public class InstanceController {
    @Autowired
    private final ProductInstanceDAO productInstanceDAO = new ProductInstanceDAO();

    @Autowired
    private final ProductDAO productDAO = new ProductDAO();

    @Autowired
    private final SupplyDAO supplyDAO = new SupplyDAO();

    @Autowired
    private final OrdersDAO ordersDAO = new OrdersDAO();

    @Autowired
    private final StorageDAO storageDAO = new StorageDAO();


    @GetMapping("/goods")
    public String goodsListPage(Model model) {
        List<ProductInstance> goods = (List<ProductInstance>) productInstanceDAO.getAll();
        model.addAttribute("goods", goods);
        model.addAttribute("goodService", productInstanceDAO);
        return "goods";
    }

    @GetMapping("/good")
    public String goodPage(@RequestParam(name = "goodId") Long goodId, Model model) {
        ProductInstance good = productInstanceDAO.getById(goodId);

        if (good == null) {
            model.addAttribute("error_msg", "В базе нет товара с ID = " + goodId);
            return "errorPage";
        }

        model.addAttribute("good", good);
        model.addAttribute("goodDAO", productInstanceDAO);
       // model.addAttribute("wcService", s);
        return "good";
    }

    @GetMapping("/editGood")
    public String editGoodPage(@RequestParam(name = "goodId", required = false) Long goodId, Model model) {
        if (goodId == null) {
            model.addAttribute("good", new ProductInstance());
            model.addAttribute("goodService", productInstanceDAO);
            return "editGood";
        }

       ProductInstance good = productInstanceDAO.getById(goodId);

        if (good == null) {
            model.addAttribute("error_msg", "В базе нет товара с ID = " + goodId);
            return "errorPage";
        }

        model.addAttribute("good", good);
        model.addAttribute("goodsDAO", productInstanceDAO);
        return "editGood";
    }

    @PostMapping("/saveGood")
    public String saveGoodPage(@RequestParam(name = "goodId") Long goodId,
                               @RequestParam(name = "goodProductName") String name,
                               @RequestParam(name = "goodAmount") Float amount,
                               @RequestParam(name = "goodArrivalDate")
                                   @DateTimeFormat(pattern = "yyyy-MM-dd") Date arrivalDate,
                               @RequestParam(name = "goodExparationDate")
                                   @DateTimeFormat(pattern = "yyyy-MM-dd") Date exparationDate,
                               @RequestParam(name = "goodSupplyId") Long supplyId,
                               @RequestParam(name = "goodOrderId") Long orderId,
                               @RequestParam(name = "goodStorageId") Long storageId,
                               Model model) {
        List<ProductInstance> goods = (List<ProductInstance>) productInstanceDAO.getAll();
        ProductInstance good = null;
        if (goodId != null && goodId != 0) {
            good = productInstanceDAO.getById(goodId);
        }
        if (goodId != 0 && good != null) {

            if (name != null) {
                List<Product> products = productDAO.findAllMatching(name, null);
                good.setProduct(products.get(0));
            }

            if (amount!= null) {
                good.setAmount(amount);
            }

            if (arrivalDate != null) {
                good.setArrival_date(arrivalDate);
            }

            if (exparationDate != null) {
                good.setExparation_date(exparationDate);
            }

            if (orderId != null) {

               Orders order = ordersDAO.getById(orderId);

                good.setOrders(order);
            }

            if (supplyId != null) {
                Supply supply = supplyDAO.getById(supplyId);
                good.setSupply(supply);
            }

            if (storageId != null) {
                Storage storage = storageDAO.getById(storageId);
                good.setStorage(storage);
            }
            productInstanceDAO.update(good);

        } else {
            Supply supply = null;
            if (supplyId != null) {
                supply = supplyDAO.getById(supplyId);
            }
            Orders order = null;
            if (orderId != null) {
                order = ordersDAO.getById(orderId);
            }

            List<Product> products = productDAO.findAllMatching(name, null);
            Storage storage = storageDAO.getById(storageId);
            good = new ProductInstance(goodId, products.get(0), amount, exparationDate, supply, arrivalDate, order, storage);
            productInstanceDAO.save(good);
        }

        return "index";
    }

    @GetMapping("/searchGood")
    public String searchGood(@RequestParam(name = "goodProductName", required = false) String name,
                             @RequestParam(name = "goodAmount", required = false) Float amount,
                             @RequestParam(name = "goodArrivalDateLo", required = false)
                                 @DateTimeFormat(pattern = "yyyy-MM-dd") Date arrivalDateLo,
                             @RequestParam(name = "goodArrivalDateHi", required = false)
                                 @DateTimeFormat(pattern = "yyyy-MM-dd") Date arrivalDateHi,
                             @RequestParam(name = "goodExparationDateLo", required = false)
                                 @DateTimeFormat(pattern = "yyyy-MM-dd") Date exparationDateLo,
                             @RequestParam(name = "goodExparationDateHi", required = false)
                                 @DateTimeFormat(pattern = "yyyy-MM-dd") Date exparationDateHi,
                             @RequestParam(name = "goodSupplyId", required = false) Long supplyId,
                             @RequestParam(name = "goodOrderId", required = false) Long orderId,
                             @RequestParam(name = "goodStorageId", required = false) Long storageId,
                             Model model) {



        Supply supply = null;
        if (supplyId != null) {
             supply = supplyDAO.getById(supplyId);
        }

        Orders order = null;

        if (orderId != null) {
             order = ordersDAO.getById(orderId);
        }

        Product product = null;

        if (name != null) {
            product = productDAO.findAllMatching(name, null).get(0);
        }

        Storage storage = null;
        if (storageId != null) {
            storage = storageDAO.getById(storageId);
        }


        List<ProductInstance> goods = productInstanceDAO.findAllMatching(product, amount,
               arrivalDateLo, arrivalDateHi, exparationDateLo, exparationDateHi, supply, order, storage );

        model.addAttribute("goods", goods);
        model.addAttribute("goodsDAO", productInstanceDAO);
        return "goods";
    }

    @PostMapping("/removeGood")
    public String removeGoodPage(@RequestParam(name = "goodId") Long goodId) {
        productInstanceDAO.deleteById(goodId);
        return "redirect:/goods";
    }


}
