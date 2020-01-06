package com.study.designpattern.creational.prototype;

import java.io.*;

/**
 * Decription
 * <p>
 *     原型模式：即采用拷贝方式生成对象
 * </p>
 * DATE 19/1/17.
 *
 * @author guijiamin.
 */
public class Prototype implements Cloneable, Serializable {
    private static final long serialVersionUID = 1L;
    private int base;
    private Integer reference;

    public Object shallowClone() throws CloneNotSupportedException {
        Prototype proto = (Prototype) super.clone();
        return proto;
    }

    public Object deepClone() throws IOException,ClassNotFoundException {
        //将当前对象序列化
        ByteArrayOutputStream bos = new ByteArrayOutputStream();
        ObjectOutputStream oos = new ObjectOutputStream(bos);
        oos.writeObject(this);
        //反序列化上面的序列化数据
        ByteArrayInputStream bis = new ByteArrayInputStream(bos.toByteArray());
        ObjectInputStream ois = new ObjectInputStream(bis);
        return ois.readObject();
    }

    public int getBase() {
        return base;
    }

    public void setBase(int base) {
        this.base = base;
    }

    public Integer getReference() {
        return reference;
    }

    public void setReference(Integer reference) {
        this.reference = reference;
    }
}
