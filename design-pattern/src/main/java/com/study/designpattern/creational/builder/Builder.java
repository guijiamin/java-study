package com.study.designpattern.creational.builder;

/**
 * Decription
 * <p>
 *     建造者模式：先构造一个其它类（Builder），再用这个类去实例化目标类（new Student(this)）
 * </p>
 * DATE 19/1/17.
 *
 * @author guijiamin.
 */
class Student {
    private String name;
    private int age;
    private String grade;
    private String sex;

    public Student(Builder builder) {
        this.name = builder.getName();
        this.age = builder.getAge();
        this.grade = builder.getGrade();
        this.sex = builder.getSex();
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public String getGrade() {
        return grade;
    }

    public void setGrade(String grade) {
        this.grade = grade;
    }

    public String getSex() {
        return sex;
    }

    public void setSex(String sex) {
        this.sex = sex;
    }
}

public class Builder {
    private String name;
    private int age;
    private String grade;
    private String sex;

    public Builder(String name) {
        this.name = name;
    }

    public Student build() {
        return new Student(this);
    }

    public String getName() {
        return name;
    }

    public Builder name(String name) {
        this.name = name;
        return this;
    }

    public int getAge() {
        return age;
    }

    public Builder age(int age) {
        this.age = age;
        return this;
    }

    public String getGrade() {
        return grade;
    }

    public Builder grade(String grade) {
        this.grade = grade;
        return this;
    }

    public String getSex() {
        return sex;
    }

    public Builder sex(String sex) {
        this.sex = sex;
        return this;
    }

    public static void main(String[] args) {
        Builder builder = new Builder("lisi").age(10).grade("5").sex("male");
        Student student = builder.build();
    }
}
