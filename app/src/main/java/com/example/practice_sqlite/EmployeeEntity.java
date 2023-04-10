package com.example.practice_sqlite;

import java.util.Date;

public class EmployeeEntity {

    private int Eid;
    private int Salary;
    private String Ename;
    private Date Created_At;
    private int id;

    public EmployeeEntity() {
    }

    public EmployeeEntity(int eid, int salary, String ename, Date created_At) {
        Eid = eid;
        Salary = salary;
        Ename = ename;
        Created_At = created_At;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getEid() {
        return Eid;
    }

    public void setEid(int eid) {
        Eid = eid;
    }

    public String getEname() {
        return Ename;
    }

    public void setEname(String ename) {
        Ename = ename;
    }

    public int getSalary() {
        return Salary;
    }

    public void setSalary(int salary) {
        Salary = salary;
    }

    public Date getCreated_At() {
        return Created_At;
    }

    public void setCreated_At(Date created_At) {
        Created_At = created_At;
    }
}
