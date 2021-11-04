package org.example;

import lombok.AllArgsConstructor;
import lombok.ToString;

import java.io.*;

public class ExternalizableDemo {

    private static final String FILE_NAME = "country.ser";

    public static void main(String[] args)  {
        Country country = new Country();
        country.setCode(91);
        country.setName("India");


        try(ObjectOutputStream objectOutputStream
                    = new ObjectOutputStream(new FileOutputStream(FILE_NAME))){
            country.writeExternal(objectOutputStream);
            objectOutputStream.flush();
        } catch (IOException e) {
            e.printStackTrace();
        }

        Country country1 = new Country();
        try(ObjectInputStream objectInputStream = new ObjectInputStream(new FileInputStream(FILE_NAME))){
            country1.readExternal(objectInputStream);
        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
        }

        System.out.println(country.getCode() == country1.getCode());
        System.out.println(country.getName().equals(country1.getName()));
    }

//    public static void main(String[] args) {
//        Foo foo = new Foo(12);
//        foo.setData();
//        SerializationUtil.writeObject(foo);
//
//        foo = SerializationUtil.readObject("foo.ser");
//        System.out.println(foo);
//    }
}

//@AllArgsConstructor
//@ToString
//class Foo implements Serializable{
//    private int data;
//
//    public void setData(){
//        data = 15;
//    }
//}
