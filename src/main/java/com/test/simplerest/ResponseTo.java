package com.test.simplerest;

import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Setter
@Getter
public class ResponseTo {
    private List<Employee> employees;

    private Employee emp;

    private String msg;

//    public Employee getEmp() {
//        return emp;
//    }
//
//    public void setEmp(Employee emp) {
//        this.emp = emp;
//    }
//
//    public List<Employee> getEmployees() {
//        return employees;
//    }
//
//    public void setEmployees(List<Employee> employees) {
//        this.employees = employees;
//    }
//
//    public String getMsg() {
//        return msg;
//    }
//
//    public void setMsg(String msg) {
//        this.msg = msg;
//    }
}
