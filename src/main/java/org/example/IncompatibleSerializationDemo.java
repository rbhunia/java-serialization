package org.example;

import lombok.Builder;
import lombok.ToString;

import java.io.*;

public class IncompatibleSerializationDemo {

    public static void main(String[] args) {
//        IncompatibleSerialization serialization = IncompatibleSerialization.builder().data("data").id(1).build();
//
//        try(ObjectOutputStream objectOutputStream = new ObjectOutputStream(new FileOutputStream("IncompatibleSerialization.ser"))){
//            objectOutputStream.writeObject(serialization);
//            objectOutputStream.flush();
//        } catch (IOException e) {
//            e.printStackTrace();
//        }
        try(ObjectInputStream objectInputStream = new ObjectInputStream(new FileInputStream("IncompatibleSerialization.ser"))){
            IncompatibleSerialization serialization = (IncompatibleSerialization) objectInputStream.readObject();
            System.out.println(serialization);
        } catch (ClassNotFoundException | IOException e) {
            e.printStackTrace();
        }

    }
}

@ToString
@Builder
class IncompatibleSerialization implements Serializable{

    private static final long serialVersionUID = 1l;
    private String data;
   // private int id;
}
