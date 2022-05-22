package com.example.demo.controller;

import java.util.ArrayList;
import java.util.List;

import com.example.demo.model.Bug;
import com.example.demo.model.Ticket;
import com.example.demo.repository.BugRepository;
import com.example.demo.repository.TicketRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import com.example.demo.model.User;
import com.example.demo.repository.UserRepository;

@RestController
@RequestMapping("users")
public class userController {

    @Autowired
    private UserRepository userRepository;
    @Autowired
    private BugRepository bugRepository;
    @Autowired
    private TicketRepository ticketRepository;

    @PostMapping("/save")
    public void save(@RequestBody User employe) {
        userRepository.save(employe);
    }


    @GetMapping("/all")
    public List<User> findAll() {
        return userRepository.findAll();
    }

    //nbr bugs
    @GetMapping("/nbbugs")
    public int nbbugs() {
        int cpt = 0;
        for (Bug b : bugRepository.findAll()) {
            cpt++;
        }
        return cpt;
    }
    //nbr ticket
    @GetMapping("/nbticket")
    public int nbticket() {
        int cpt = 0;
        for (Ticket b : ticketRepository.findAll()) {
            cpt++;
        }
        return cpt;
    }
    //nbr client
    @GetMapping("/nbclient")
    public int nbclient() {
        int cpt = 0;
        for (User u : userRepository.findAll()) {
            if(u.getRole().getId()==2)
            {
                cpt++;
            }
        }
        return cpt;
    }
    //nbr dev
    @GetMapping("/nbdev")
    public int nbdev() {
        int cpt = 0;
        for (User u : userRepository.findAll()) {
            if(u.getRole().getId()==3)
            {
                cpt++;
            }
        }
        return cpt;
    }

    @GetMapping("/getuser/{id}")
    public User getuser(@PathVariable int id) {
        User cuser = new User();
        for (User user : userRepository.findAll()) {
            if (user.getUserId() == id) {
                cuser = user;
            }
        }
        return cuser;
    }

    @GetMapping("/alldevs")
    public List<User> alldevsl() {
        List<User> devs = new ArrayList<>();
        for (User u : userRepository.findAll()) {
            if (u.getRole().getId() == 3) {
                devs.add(u);
            }
        }
        return devs;
    }

    //update user profile
    @PostMapping("/update")
    public void alldevsl(@RequestBody User user) {
        User us = new User();
        for (User u : userRepository.findAll()) {
            if (u.getUserId() == user.getUserId()) {
                us = u;
            }
        }
        us.setEmail(user.getEmail());
        us.setPassword(user.getPassword());
        us.setUsername(user.getUsername());
        userRepository.save(us);
    }
}
