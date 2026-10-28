package com.example.main;

import com.example.entity.Employee;
import com.example.repo.EmployeeRepo;
import com.example.service.EmployeeService;
import java.util.Scanner;

public class EmployeeDriver {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        EmployeeService service = new EmployeeService();

        try {
            while (true) {

                System.out.println("\n===== Employee Menu =====");
                System.out.println("1. Insert using Hibernate");
                System.out.println("2. Insert using JPA");
                System.out.println("0. Exit");
                System.out.print("Enter your choice: ");

                int choice;

                try {
                    choice = Integer.parseInt(sc.nextLine().trim());
                } catch (NumberFormatException e) {
                    System.out.println("Please enter a valid number.");
                    continue;
                }

                switch (choice) {

                    case 1:
                    case 2: {
                        System.out.print("Enter employee name: ");
                        String name = sc.nextLine().trim();

                        System.out.print("Enter employee salary: ");

                        double salary;

                        try {
                            salary = Double.parseDouble(
                                    sc.nextLine().trim()
                            );

                            if (name.isEmpty()
                                    || !Double.isFinite(salary)
                                    || salary < 0) {
                                System.out.println(
                                        "Enter a valid name and salary."
                                );
                                break;
                            }

                        } catch (NumberFormatException e) {
                            System.out.println(
                                    "Please enter a valid salary."
                            );
                            break;
                        }

                        Employee employee =
                                new Employee(name, salary);

                        try {
                            switch (choice) {
                                case 1:
                                    service.insertEmployeeByCore(employee);
                                    break;

                                case 2:
                                    service.insertEmployeeByJPA(employee);
                                    break;
                            }
                        } catch (RuntimeException e) {
                            System.out.println(
                                    "Insert failed: " + e.getMessage()
                            );
                        }

                        break;
                    }

                    case 0:
                        System.out.println("Application closed.");
                        return;

                    default:
                        System.out.println("Invalid choice!");
                }
            }
        } finally {
            sc.close();
            EmployeeRepo.closeResources();
        }
    }
    }

