package org.example;

import java.io.*;

public class SuperNotSerial {

    public static void main(String[] args) {
        try(ObjectOutputStream objectOutputStream = new ObjectOutputStream(new FileOutputStream("dog.ser"))){
            Dog dog = new Dog(35, "Volu");
            objectOutputStream.writeObject(dog);
            objectOutputStream.flush();
        } catch (IOException e) {
            e.printStackTrace();
        }

        try(ObjectInputStream objectInputStream = new ObjectInputStream(new FileInputStream("dog.ser"))){
            Dog dog = (Dog) objectInputStream.readObject();
            System.out.println(dog.name + " " + dog.weight);
        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
        }
    }
}

class Animal{
     int weight = 42;

    Animal(){
        System.out.println("Calling super class constructor.");
    }
}

class Dog extends Animal implements Serializable{
    String name;
    Dog(int w, String n){
        weight = w;
        name = n;
    }
}
