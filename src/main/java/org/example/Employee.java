package org.example;

import lombok.Builder;
import lombok.ToString;

import java.io.Serializable;

@Builder
@ToString
public class Employee implements Serializable {

    private static final long serialVersionUID = 1l;

    private String name;
    private int age;
    private int salary;
}
