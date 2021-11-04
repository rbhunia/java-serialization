package org.example;

import lombok.*;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;

public class CustomSerializationDemo {

    private static final String FILE_NAME = "Person.ser";

    public static void main(String[] args) {
        Person person = Person.builder().name("Raj").address(new Address("WB", "MDN", "721102")).build();

        SerializationUtil.writeObject(person);
        Person deserializedPerson = SerializationUtil.readObject(FILE_NAME);
        System.out.println(deserializedPerson);
    }

}

@Builder
@ToString
class Person implements Serializable {
    private String name;
    private transient Address address;

    private void writeObject(ObjectOutputStream objectOutputStream) {
        try {
            objectOutputStream.defaultWriteObject();
            objectOutputStream.writeObject(address.getState());
            objectOutputStream.writeObject(address.getCity());
            objectOutputStream.writeObject(address.getZipCode());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void readObject(ObjectInputStream objectInputStream) {
        try {
            objectInputStream.defaultReadObject();
            String state = (String) objectInputStream.readObject();
            String city = (String) objectInputStream.readObject();
            String zipCode = (String) objectInputStream.readObject();
            address = new Address(state, city, zipCode);
        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
        }
    }
}

@Getter
@Setter
@ToString
@AllArgsConstructor
class Address {
    private String state;
    private String city;
    private String zipCode;
}
