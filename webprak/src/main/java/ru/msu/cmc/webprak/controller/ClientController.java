package ru.msu.cmc.webprak.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import ru.msu.cmc.webprak.DAO.ClientDAO;
import ru.msu.cmc.webprak.DAO.OrdersDAO;
import ru.msu.cmc.webprak.models.Client;

import java.util.List;
import java.util.Objects;

@Controller
public class ClientController {
    @Autowired
    private final ClientDAO clientDAO = new ClientDAO();

    @Autowired
    private final OrdersDAO ordersDAO = new OrdersDAO();

    @GetMapping("/clients")
    public String clientsListPage(Model model) {
        List<Client> clients = (List<Client>) clientDAO.getAll();
        model.addAttribute("clients", clients);
        model.addAttribute("clientService", clientDAO);
        return "clients";
    }

    @GetMapping("/client")
    public String clientPage(@RequestParam(name = "clientId") Long clientId, Model model) {
        Client client = clientDAO.getById(clientId);

        if (client == null) {
            model.addAttribute("error_msg", "В базе нет заказчика с ID = " + clientId);
            return "errorPage";
        }

        model.addAttribute("client", client);
        model.addAttribute("clientService", clientDAO);
        model.addAttribute("ordersService", ordersDAO);
        return "client";
    }

    @GetMapping("/editClient")
    public String editClientPage(@RequestParam(name = "clientId", required = false) Long Id, Model model) {
        if (Id == null) {
            model.addAttribute("client", new Client());
            model.addAttribute("clientService", clientDAO);
            return "editClient";
        }

        Client client = clientDAO.getById(Id);

        if (client == null) {
            model.addAttribute("error_msg", "В базе нет человека с ID = " + Id);
            return "errorPage";
        }

        model.addAttribute("client", client);
        model.addAttribute("clientService", clientDAO);
        return "editClient";
    }

    @PostMapping("/saveClient")
    public String saveClientPage(@RequestParam(name = "clientId") Long clientId,
                                @RequestParam(name = "clientName") String name,
                                @RequestParam(name = "clientPhone", required = false) String phone,
                                @RequestParam(name = "clientAddress", required = false) String address,
                                @RequestParam(name = "clientDescription", required = false) String description,
                                Model model) {
        List<Client> clients = (List<Client>) clientDAO.getAll();
        boolean changeIsSuccessful = false;
        Client client = null;
        if (clientId != 0) {
            client = clientDAO.getById(clientId);
        }
        if (clientId != 0 && client != null) {
            client.setName(name);

            if (phone!= null) {
                client.setPhone(phone);
            }

            if (address!= null) {
                client.setAddress(address);
            }
            if (description!= null) {
                client.setDescription(description);
            }
            clientDAO.update(client);
        } else {
            client = new Client();
            client.setName(name);

            if (phone!= null) {
                client.setPhone(phone);
            }

            if (address!= null) {
                client.setAddress(address);
            }
            if (description!= null) {
                client.setDescription(description);
            }
            clientDAO.save(client);
        }
        return "index";
    }

    @GetMapping("/searchClient")
    public String searchEmployee(@RequestParam(required = true) String name,
                                 Model model) {

        List<Client> buyers = clientDAO.findAllMatching(name);

        model.addAttribute("clients", buyers);
        model.addAttribute("clientsDAO", clientDAO);
        return "clients";
    }


    @PostMapping("/removeClient")
    public String removeBuyerPage(@RequestParam(name = "clientId") Long clientId) {
        clientDAO.deleteById(clientId);
        return "redirect:/clients";
    }

}
