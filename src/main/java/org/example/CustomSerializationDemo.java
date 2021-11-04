package org.example;

import lombok.*;

import java.io.*;

public class CustomSerializationDemo {
    public static void main(String[] args) {
        Person person = Person.builder().name("Raj").address(new Address("WB", "MDN", "721102")).build();
        try(ObjectOutputStream objectOutputStream = new ObjectOutputStream(new FileOutputStream("custom.ser"))){
            objectOutputStream.writeObject(person);
            objectOutputStream.flush();
        } catch (IOException e) {
            e.printStackTrace();
        }

        try(ObjectInputStream objectInputStream = new ObjectInputStream(new FileInputStream("custom.ser"))){
            Person deserializedPerson = (Person) objectInputStream.readObject();
            System.out.println(deserializedPerson);
        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
        }
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
            address = new Address(state,city,zipCode);
        } catch (IOException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
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
