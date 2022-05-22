package com.example.demo.controller;

import com.example.demo.model.*;
import com.example.demo.repository.*;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("admin")
public class AdminController {
    @Autowired
    private UserRepository userrepository;
    @Autowired
    private BugRepository bugRepository;
    @Autowired
    private TicketRepository ticketRepository;
    @Autowired
    private LogicielRepository logicielRepository;
    @Autowired
    private UrgenceRepository urgenceRepository;

    //list des bug affecter
    @GetMapping("/listaffecterbug")
    public List<Bug> listaffecterbug() {
        List<Bug> bugs = new ArrayList<>();
        for (Bug b : bugRepository.findAll()) {
            if (!(b.getEtat())) {
                bugs.add(b);
            }
        }
        return bugs;
    }

    @GetMapping("/listLogiciel")
    public List<Logiciel> listLogiciel() {
        return logicielRepository.findAll();
    }

    @GetMapping("/listurgence")
    public List<Urgence> listurgence() {
        return urgenceRepository.findAll();
    }

    @GetMapping("/tickets")
    public List<Ticket> listtickets() {
        return ticketRepository.findAll();
    }
    @GetMapping("/ticketsNonAtt")
    public List<Ticket> ticketsNonAtt() {
        List<Ticket> tickets = new ArrayList<>();
        for (Ticket ticket: ticketRepository.findAll()) {
            if(ticket.getDev()==null)
            {
                tickets.add(ticket);
            }

        }
        return tickets;
    }
    @GetMapping("/ticketsAtt")
    public List<Ticket> ticketsAtt() {
        List<Ticket> tickets = new ArrayList<>();
        for (Ticket ticket: ticketRepository.findAll()) {
            if(ticket.getDev()!=null)
            {
                tickets.add(ticket);
            }

        }
        return tickets;
    }

    @GetMapping("/bugs")
    public List<Bug> bugs() {
        return bugRepository.findAll();
    }
    //descafecter
    @GetMapping("/descafecter/{id}")
    public void descafecter(@PathVariable long id) {
        Ticket ti = new Ticket();

        for (Ticket t : ticketRepository.findAll()) {
            if (t.getId() == id) {
                ti = t;
                break;
            }
        }


        ti.setDev(null);
        ticketRepository.save(ti);
    }
    //add ticket :
    @PostMapping("/addbug")
    public void addbug(@RequestBody Bug bug) {
        Logiciel logiciel = new Logiciel();
        Urgence urgence = new Urgence();
        for (Logiciel t : logicielRepository.findAll()) {
            if (t.getId() == bug.getLogiciel().getId()) {
                logiciel = t;

            }
        }

        for (Urgence t : urgenceRepository.findAll()) {
            if (t.getId() == bug.getUrgence().getId()) {
                urgence = t;

            }
        }

        bugRepository.save(new Bug(bug.getNom(),bug.getDescription(),bug.getEtat(),urgence,logiciel));

    }
//affecter
    @PostMapping("/affecterbug")
    public void affecterbug(@RequestBody Ticket ticket) {
        Ticket ti = new Ticket();
        User dev = new User();
        for (Ticket t : ticketRepository.findAll()) {
            if (t.getId() == ticket.getBug().getId()) {
                ti = t;
                break;
            }
        }

        for (User u : userrepository.findAll()) {
            if (u.getUserId() == ticket.getUser().getUserId()) {
                dev = u;
                break;
            }
        }
        ti.setDev(dev);
        ticketRepository.save(ti);
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


}
