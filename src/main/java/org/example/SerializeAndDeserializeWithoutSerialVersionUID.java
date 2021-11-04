package org.example;

import java.io.IOException;

public class SerializeAndDeserializeWithoutSerialVersionUID {

    private static final String FILE_NAME = "emp.ser";

    public static void main(String[] args) throws IOException, ClassNotFoundException {

        //SerializationUtil.serialize(Employee.builder().name("Raj").age(32).build());

        Employee employee = SerializationUtil.deserialize(FILE_NAME);
        System.out.println(employee);
    }


}
