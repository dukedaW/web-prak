package ru.msu.cmc.webprak.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import ru.msu.cmc.webprak.DAO.*;
import ru.msu.cmc.webprak.models.Client;
import ru.msu.cmc.webprak.models.Orders;
import ru.msu.cmc.webprak.models.Product;

import java.util.Date;
import java.util.List;

@Controller
public class OrdersController {
    @Autowired
    private final OrdersDAO ordersDAO = new OrdersDAO();

    @Autowired
    private final ClientDAO clientDAO = new ClientDAO();

    @Autowired
    private final  ProductDAO productDAO = new ProductDAO();


    @GetMapping("/orders")
    public String ordersListPage(Model model) {
        List<Orders> orders = (List<Orders>) ordersDAO.getAll();
        model.addAttribute("orders", orders);
        model.addAttribute("ordersService", ordersDAO);
        return "orders";
    }

    @GetMapping("/order")
    public String orderPage(@RequestParam(name = "orderId") Long orderId, Model model) {
        Orders order = ordersDAO.getById(orderId);

        if (order == null) {
            model.addAttribute("error_msg", "В базе нет заказа с ID = " + orderId);
            return "errorPage";
        }

        model.addAttribute("order", order);
        model.addAttribute("ordersService", ordersDAO);
        model.addAttribute("goodsService", productDAO);
        return "order";
    }

    @GetMapping("/editOrder")
    public String editOrderPage(@RequestParam(name = "orderId", required = false) Long orderId, Model model) {
        if (orderId == null) {
            model.addAttribute("order", new Orders());
            model.addAttribute("ordersService", ordersDAO);
            return "editOrder";
        }

        Orders order = ordersDAO.getById(orderId);

        if (order == null) {
            model.addAttribute("error_msg", "В базе нет поставки  с ID = " + orderId);
            return "errorPage";
        }

        model.addAttribute("order", order);
        model.addAttribute("ordersService", ordersDAO);
        return "editOrder";
    }

    @PostMapping("/saveOrder")
    public String saveOrderPage(@RequestParam(name = "orderId") Long orderId,
                                   @RequestParam(name = "orderDateTime", required = true) @DateTimeFormat(pattern = "yyyy-MM-dd") Date date,
                                   @RequestParam(name = "orderProductName", required = false) String productName,
                                   @RequestParam(name = "orderBuyerName", required = false) String buyerName,
                                   @RequestParam(name = "orderAmount", required = false) Float amount,
                                   @RequestParam(name = "orderDeparted", required = false) Boolean departed,
                                   Model model) {
        Orders order = ordersDAO.getById(orderId);

        if (order != null) {
            order.setDeparture_date(date);
            if (buyerName!= null) {
                List<Client> buyers = clientDAO.findAllMatching(buyerName);
                if (buyers != null && buyers.size() > 0) {
                    order.setClient(buyers.get(0));
                }
            }
            if (productName != null) {
                List<Product> products = productDAO.findAllMatching(productName, null);
                if (products != null && products.size() == 1) {
                    order.setProduct(products.get(0));
                }
            }
            if (departed != null) {
                order.setHas_departed(departed);
            }

            if (amount != null) {
                order.setAmount(amount);
            }
            ordersDAO.update(order);
        } else if (ordersDAO.getById(orderId) == null){
            List<Product> products = productDAO.findAllMatching(productName, null);
            order = new Orders(orderId, clientDAO.findAllMatching(buyerName).get(0),  date , products.get(0),
                    amount, departed);
            ordersDAO.save(order);
        }
        return "index";
    }


    @GetMapping("/searchOrder")
    public String searchOrders( @RequestParam(value = "clientName" , required = false) String clientName,
                                @RequestParam (value = "productName", required = false) String productName,
            @RequestParam(name = "dateLo", required = false) @DateTimeFormat(pattern = "yyyy-MM-dd") Date dateLo,
                                 @RequestParam(name = "dateHi", required = false) @DateTimeFormat(pattern = "yyyy-MM-dd") Date dateHi,
                                 @RequestParam(name = "amountLo", required = false) Float amountLo,
                                @RequestParam(name = "amountHi", required = false) Float amountHi,
                               @RequestParam(name = "departed", required = false) Boolean departed,
                                 Model model) {


        List<Client> clients = clientDAO.findAllMatching(clientName);
        List<Product> products = productDAO.findAllMatching(productName, null);

        List<Orders> orders = ordersDAO.findAllMatching(clients.get(0),products.get(0) ,
                amountLo, amountHi, dateLo, dateHi, departed);

        model.addAttribute("orders", orders);
        model.addAttribute("ordersDAO", ordersDAO);
        return "orders";
    }

    @PostMapping("/removeDelivery")
    public String removeDeliveryPage(@RequestParam(name = "orderId") Long orderId) {
        ordersDAO.deleteById(orderId);
        return "redirect:/orders";
    }

}
