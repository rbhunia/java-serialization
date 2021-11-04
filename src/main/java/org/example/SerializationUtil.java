package org.example;

import java.io.*;

public class SerializationUtil {

    public static <T extends Serializable> void serialize(T object) {
        try (ObjectOutputStream objectOutputStream = new ObjectOutputStream(new FileOutputStream(getClassName(object.getClass().getCanonicalName()) + ".ser"))) {
            objectOutputStream.writeObject(object);
            objectOutputStream.flush();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static <T extends Serializable> T deserialize(String fileName) {
        try (ObjectInputStream objectInputStream = new ObjectInputStream(new FileInputStream(fileName))) {
            T deserializedObject = (T) objectInputStream.readObject();
            return deserializedObject;
        } catch (ClassNotFoundException | IOException e) {
            e.printStackTrace();
            return null;
        }
    }

    private static String getClassName(String className) {
        return className.substring(className.lastIndexOf('.') + 1);
    }
}
