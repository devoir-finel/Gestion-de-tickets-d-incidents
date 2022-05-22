package com.example.demo.repository;

import com.example.demo.model.Logiciel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface LogicielRepository  extends JpaRepository<Logiciel, Integer>{

}
