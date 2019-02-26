package com.epam.viktoryia.springdatabase.model;

public class Employee {

    private Integer id;
    private String name;
    private Integer age;


    public void setName(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setAge(Integer age) {
        this.age = age;
    }

    public Integer getAge() {
        return age;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getId() {
        return id;
    }

    @Override
    public String toString() {
        return "ID : " + id + " " + "Name : " + name + " " + "Age : " + age;
    }
}
