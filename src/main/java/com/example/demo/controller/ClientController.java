package com.example.demo.controller;

import com.example.demo.model.*;
import com.example.demo.repository.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("client")
public class ClientController {
    @Autowired
    private UserRepository userrepository;
    @Autowired
    private BugRepository bugRepository;
    @Autowired
    private TicketRepository ticketRepository;



    //list of ticket
    @GetMapping("/listticket/{id}")
    public List<Ticket> listaffecterbug(@PathVariable long id) {
        List<Ticket> tickets = new ArrayList<>();
        for (Ticket b : ticketRepository.findAll()) {
            if (b.getUser().getUserId()==id) {
                tickets.add(b);
            }
        }
        return tickets;
    }


    @PostMapping("/creeticket")
    public void creeticket(@RequestBody Ticket ticket) {
        Bug bug = new Bug();
        User user = new User();
        for (Bug b : bugRepository.findAll()) {
            if (b.getId() == ticket.getBug().getId()) {
                bug = b;
                break;
            }
        }
        for (User u : userrepository.findAll()) {
            if (u.getUserId() == ticket.getUser().getUserId()) {
                user = u;
                break;
            }
        }
        ticketRepository.save(new Ticket(ticket.getNom(), ticket.getDescription(), ticket.getPriorite(), user, bug));
    }

    @DeleteMapping("/deleteticket/{id}")
    public void deleteticket(@PathVariable long id) {
        Ticket ticket = new Ticket();
        for (Ticket t : ticketRepository.findAll()) {
            if (t.getId() == id) {
                ticket = t;
            }
        }
        ticketRepository.delete(ticket);
    }
    //deletebug
    @DeleteMapping("/deletebug/{id}")
    public void deletebug(@PathVariable long id) {
        Bug bug = new Bug();
        for (Bug t : bugRepository.findAll()) {
            if (t.getId() == id) {
                bug = t;
            }
        }
        bugRepository.delete(bug);
    }


}

