package serializable;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

/**
 * 序列化测试
 *
 * @author lxk on 2017/11/1
 */
public class SerializableTest {
    public static void main(String[] args) throws Exception {
        //serializeFlyPig();
        FlyPig flyPig = deserializeFlyPig();
        System.out.println(flyPig.toString());
 
    }
 
    /**
     * 序列化
     */
    private static void serializeFlyPig() throws IOException {
        FlyPig flyPig = new FlyPig();
        flyPig.setColor("black");
        flyPig.setName("naruto");
        flyPig.setCar("0000");
        // ObjectOutputStream 对象输出流，将 flyPig 对象存储到E盘的 flyPig.txt 文件中，完成对 flyPig 对象的序列化操作
        ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(new File("d:/flyPig.txt")));
        oos.writeObject(flyPig);
        System.out.println("FlyPig 对象序列化成功！");
        oos.close();
    }
 
    /**
     * 反序列化
     */
    private static FlyPig deserializeFlyPig() throws Exception {
        ObjectInputStream ois = new ObjectInputStream(new FileInputStream(new File("d:/flyPig.txt")));
        FlyPig person = (FlyPig) ois.readObject();
        System.out.println("FlyPig 对象反序列化成功！");
        return person;
    }


}
