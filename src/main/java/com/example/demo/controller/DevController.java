package com.example.demo.controller;

import com.example.demo.model.*;
import com.example.demo.repository.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
@RestController
@RequestMapping("dev")
public class DevController {

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

        //list of ticket
        @GetMapping("/listticket/{id}")
        public List<Ticket> listaffecterbug(@PathVariable long id) {
            List<Ticket> tickets = new ArrayList<>();
            for (Ticket b : ticketRepository.findAll()) {
                if(b.getDev()!=null)
                {
                    if (b.getDev().getUserId()==id) {
                        tickets.add(b);
                    }
                }

            }
            return tickets;
        }
    //affecter
    @PostMapping("/affecterbug")
    public void affecterbug(@RequestBody Ticket ticket) {
        Ticket ti = new Ticket();

        for (Ticket t : ticketRepository.findAll()) {
            if (t.getId() == ticket.getBug().getId()) {
                ti = t;
                break;
            }
        }
        ti.setStatut(ticket.getStatut());
        ticketRepository.save(ti);
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


    }

