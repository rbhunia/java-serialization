package org.example;

import lombok.Data;

import java.io.*;

public class SerializeChainOfClasses {

    public static void main(String[] args) throws IOException, ClassNotFoundException {
        Reference3 reference3 = new Reference3();
        reference3.setData(1);

        Reference2 reference2 = new Reference2();
        reference2.setData(2);
        reference2.setReference3(reference3);

        Reference1 reference1 = new Reference1();
        reference1.setData(3);
        reference1.setReference2(reference2);

        ObjectOutputStream objectOutputStream = new ObjectOutputStream(new FileOutputStream("custom.ser"));
        objectOutputStream.writeObject(reference1);
        objectOutputStream.flush();
        objectOutputStream.close();

        ObjectInputStream objectInputStream = new ObjectInputStream(new FileInputStream("custom.ser"));
        Reference1 readObject = (Reference1) objectInputStream.readObject();
        objectInputStream.close();

        System.out.println(readObject.getReference2().getReference3().getData());
    }

    @Data
    static class Reference1 implements Serializable {
        private int data;
        private Reference2 reference2;
    }

    @Data
    static class Reference2 implements Serializable {
        private int data;
        private Reference3 reference3;
    }

    @Data
    static class Reference3 implements Serializable {
        private int data;
    }
}
