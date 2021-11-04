package org.example;

import java.io.*;

public class SerializeAndDeserializeWithoutSerialVersionUID {

    public static void main(String[] args) throws IOException, ClassNotFoundException {
//        ObjectOutputStream objectOutputStream = new ObjectOutputStream(new FileOutputStream("emp.ser"));
//        objectOutputStream.writeObject(Employee.builder().name("Raj").age(32).build());
//        objectOutputStream.close();

        ObjectInputStream objectInputStream = new ObjectInputStream(new FileInputStream("emp.ser"));
        Employee employee = (Employee) objectInputStream.readObject();
        System.out.println(employee);
    }


}
