package org.example;

import lombok.Builder;
import lombok.ToString;

import java.io.*;

@Builder
@ToString
public class ProjectObject implements Serializable {

    private int id;
    private transient ThirdPartyObject thirdPartyObject;

    private static final long serialVersionUID = 1l;

    private void writeObject(ObjectOutputStream objectOutputStream)  {
        try {
            objectOutputStream.defaultWriteObject();
            objectOutputStream.writeObject(thirdPartyObject.getName());
            objectOutputStream.writeInt(thirdPartyObject.getMoreThirdPartyObject().getCount());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void readObject(ObjectInputStream objectInputStream) throws Exception {
        objectInputStream.defaultReadObject();
        String name = (String) objectInputStream.readObject();
        int count = objectInputStream.readInt();
        thirdPartyObject = new ThirdPartyObject(name, new MoreThirdPartyObject(count));
    }

    public static void main(String[] args) throws IOException, ClassNotFoundException {
        ProjectObject projectObject = ProjectObject
                .builder()
                .id(2)
                .thirdPartyObject(new ThirdPartyObject("TPO", new MoreThirdPartyObject(3)))
                .build();
        ObjectOutputStream objectOutputStream = new ObjectOutputStream(new FileOutputStream("ProjectObject.ser"));
        objectOutputStream.writeObject(projectObject);
        objectOutputStream.close();

        ObjectInputStream objectInputStream = new ObjectInputStream(new FileInputStream("ProjectObject.ser"));
        ProjectObject deserializedProjectObject = (ProjectObject) objectInputStream.readObject();
        objectInputStream.close();

        System.out.println(deserializedProjectObject);
    }
}
