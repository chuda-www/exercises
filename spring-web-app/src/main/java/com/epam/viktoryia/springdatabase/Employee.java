package com.epam.viktoryia.springdatabase;

public class Employee {

    private String name;
    private Integer age;
    private Integer id;

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
