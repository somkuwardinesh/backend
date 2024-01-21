package com.test.simplerest;

import jakarta.annotation.PostConstruct;
import lombok.extern.log4j.Log4j2;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.env.Environment;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Stream;

@RestController
@RequestMapping("/emp")
@CrossOrigin(origins = "http://34.66.113.116/")
@Log4j2
public class EmpController {

    //    Logger logger = LogManager.getLogger(EmpController.class);
    @Autowired
    private EmpRepo empRepo;

    @Autowired
    Environment environment;

    @Value("${my.data.name}")
    private String name;

    @PostConstruct
    public void addEmpData() {
        log.info("Adding Sample Data Into DB");
        Employee e = new Employee();
        e.setFirstName("Salman");
        e.setLastName("Khan");
        e.setSalary(5000d);
        Address a = new Address();
        a.setStreet("Golden road");
        a.setState("Maha");
        a.setZipCode("412022");
        a.setCity("Mumbai");
        e.setAddress(a);
        empRepo.saveAndFlush(e);
//        empRepo.saveAndFlush(e);
    }

    @GetMapping()
    public ResponseEntity<ResponseTo> getAllEmp() {
        System.out.println("I am in get all method");
        log.info("I am in get all method");

        log.info("from app.property my name is : " + name);
        log.info("from custom env name is : " + environment.getProperty("my_env_name"));
        log.info("from custom env name is : " + environment.getProperty("my.dd.env.name"));
        log.info("from sys env DESKTOP_SESSION  : " + environment.getProperty("DESKTOP_SESSION"));
        ResponseTo responseTo = new ResponseTo();

        responseTo.setEmployees(empRepo.findAll());


        return new ResponseEntity<>(responseTo, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<ResponseTo> getEmpById(@PathVariable("id") Integer id) {
        System.out.println("I am in getEmpById method");
        log.info("I am in getEmpById method");


        try (FileWriter f = new FileWriter("myfiles/output.txt")) {

            f.write("Testing!!!!!");

        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        System.out.println("Content is successfully added into the output.txt file.");








        try {

            Stream<String> lines = Files.lines(Paths.get("myfiles/output.txt"));
            lines.forEach(System.out::println);


        } catch (IOException e) {
            throw new RuntimeException(e);
        }


        System.out.println("Reading file is completed!!!");

        ResponseTo responseTo = new ResponseTo();
        responseTo.setEmp(empRepo.findById(id).get());
        return new ResponseEntity<>(responseTo, HttpStatus.OK);
    }

    @PostMapping()
    public ResponseEntity<ResponseTo> addEmp(@RequestBody Employee employee) {
        ResponseTo responseTo = new ResponseTo();
        Employee e = empRepo.saveAndFlush(employee);
        responseTo.setEmp(e);

        return new ResponseEntity<>(responseTo, HttpStatus.OK);
    }

    @PutMapping
    public ResponseEntity<ResponseTo> updateEmp(@RequestBody Employee employee) {
        ResponseTo responseTo = new ResponseTo();
        Employee e = empRepo.saveAndFlush(employee);
        responseTo.setEmp(e);

        return new ResponseEntity<>(responseTo, HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<ResponseTo> deleteEmpById(@PathVariable("id") Integer id) {
        ResponseTo responseTo = new ResponseTo();
        empRepo.delete(new Employee(id));
        responseTo.setMsg("Deleted");
        return new ResponseEntity<>(responseTo, HttpStatus.OK);
    }

}
