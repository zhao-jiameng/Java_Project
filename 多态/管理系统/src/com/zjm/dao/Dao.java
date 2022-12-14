package com.zjm.dao;


import com.zjm.domain.Admin;
import com.zjm.domain.Student;
import com.zjm.domain.User;
import java.util.ArrayList;

public class Dao {
    public static ArrayList<User> list= new ArrayList<>();
    public static ArrayList<Student> list2 = new ArrayList<>();
    public static ArrayList<Admin> list3= new ArrayList<>();
    public static void userAdd( User user) {
        list.add(user);
    }
    public static void studentAdd(Student student) {
        list2.add(student);
    }
    public static void teacherAdd(Admin admin) {
        list3.add(admin);
    }
    public static void remove(int i) {
        list2.remove(i);
    }
    public static void setStudent(int i, String next) {
        String[] split = next.split(":");
        switch (split[0]) {
            case "学号":
                Dao.list2.get(i).setId(Integer.parseInt(split[1]));
                break;
            case "姓名":
                Dao.list2.get(i).setName(split[1]);
                break;
            case "性别":
                Dao.list2.get(i).setSex(split[1]);
                break;
            case "年龄":
                Dao.list2.get(i).setAge(Integer.parseInt(split[1]));
                break;
            case "班级":
                Dao.list2.get(i).setCls(Integer.parseInt(split[1]));
                break;
            case "专业":
                Dao.list2.get(i).setZy(split[1]);
                break;
            case "q":
                break;
            default:
                System.out.println("输入错误");
                break;
        }
    }

}
