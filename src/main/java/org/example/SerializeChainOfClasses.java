package org.example;

import lombok.Data;

import java.io.IOException;
import java.io.Serializable;

public class SerializeChainOfClasses {

    private static final String FILE_NAME = "Reference1.ser";

    public static void main(String[] args) throws IOException, ClassNotFoundException {
        Reference3 reference3 = new Reference3();
        reference3.setData(1);

        Reference2 reference2 = new Reference2();
        reference2.setData(2);
        reference2.setReference3(reference3);

        Reference1 reference1 = new Reference1();
        reference1.setData(3);
        reference1.setReference2(reference2);

        SerializationUtil.serialize(reference1);

        Reference1 readObject = SerializationUtil.deserialize(FILE_NAME);

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
