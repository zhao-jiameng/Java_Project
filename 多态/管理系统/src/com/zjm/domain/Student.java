package com.zjm.domain;

public class Student {
    private int id;
    private String name;
    private String sex;

    public String getSex() {
        return sex;
    }

    public void setSex(String sex) {
        this.sex = sex;
    }

    private int age;
    private int cls;
    private String zy;



    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
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
        if (age<30&&age>0){
            this.age = age;
        }else {
            System.out.println("输入错误，请重新输入");
        }

    }

    public int getCls() {
        return cls;
    }

    public void setCls(int cls) {
        this.cls = cls;
    }

    public String getZy() {
        return zy;
    }

    public void setZy(String zy) {
        this.zy = zy;
    }

    public Student() {
    }

    public Student(int id, String name, String sex, int age, int cls, String zy) {
        this.id = id;
        this.name = name;
        this.sex = sex;
        this.age = age;
        this.cls = cls;
        this.zy = zy;
    }

    @Override
    public String toString() {
        return "学生信息{" +
                "学号=" + id +
                ", 姓名='" + name + '\'' +
                ", 性别='" + sex + '\'' +
                ", 年龄=" + age +
                ", 班级=" + cls +
                ", 专业='" + zy + '\'' +
                '}';
    }
}
