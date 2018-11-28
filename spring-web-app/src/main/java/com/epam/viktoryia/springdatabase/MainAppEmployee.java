package com.epam.viktoryia.springdatabase;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import java.util.List;

public class MainAppEmployee {

    private static Employee createEmployeeObject(String name, Integer age) {
        Employee employee = new Employee();
        employee.setName(name);
        employee.setAge(age);
        return employee;
    }

    public static void main(String[] args) {
        ApplicationContext context = new ClassPathXmlApplicationContext("/spring-context.xml");
        EmployeeDAO employeeDAO = context.getBean(EmployeeDAO.class);

        Employee employee1 = createEmployeeObject("Zara", 11);
        Employee employee2 = createEmployeeObject("Nuha", 2);
        Employee employee3 = createEmployeeObject("Ayan", 15);

        System.out.println("------Records Creation--------");
        employeeDAO.create(employee1);
        employeeDAO.create(employee2);
        employeeDAO.create(employee3);

        System.out.println("------Listing Multiple Records--------");
        List<Employee> employeeList = employeeDAO.listEmployee();

        for (Employee record : employeeList) {
            System.out.print("ID : " + record.getId());
            System.out.print(" Name : " + record.getName());
            System.out.println(" Age : " + record.getAge());
        }

        System.out.println("----Updating Record with ID = 2 -----");
        employeeDAO.update(2, 222);
        System.out.println("Record with ID = 2 \n" + employeeDAO.getById(2));
    }
}