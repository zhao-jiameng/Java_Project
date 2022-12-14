package com.zjm.service;


import com.zjm.controller.StudentHello;
import com.zjm.dao.Dao;
import com.zjm.domain.Student;
import com.zjm.domain.User;

import java.util.Scanner;

public class StudentService {
    public static void dl( String username, String password) throws ClassNotFoundException {
        Class.forName("com.zjm.dao.Dao");
        if (Dao.list.size() == 0) {
            System.out.println("--------------------------");
            System.out.println("请先注册在尝试登录");
        } else {
            for (int i = 0; i < Dao.list.size(); i++) {
                if (Dao.list.get(i).getUser().equals(username) && Dao.list.get(i).getPassword().equals(password)) {
                    System.out.println("登录成功，欢迎进入学生管理系统！");
                    System.out.println("--------------------------");
                    StudentHello.xsglxt();
                } else {
                    System.out.println("-----------------------------");
                    System.out.println("登录失败，请检查用户名或密码是否正确");
                }
            }
        }

    }
    public static void zc( User user) throws ClassNotFoundException {
        Class.forName("com.zjm.dao.Dao");
        if (Dao.list.size()==0){
            Dao.userAdd(user);
            System.out.println("--------------------------");
            System.out.println("注册成功，请返回登录");
        }else {
            for (int i = 0; i < Dao.list.size(); i++) {
                if (Dao.list.get(i).getUser().equals(user.getUser())){
                    System.out.println("--------------------------");
                    System.out.println("您输入的用户已经存在，请返回登录");
                }else {
                    Dao.userAdd(user);
                    System.out.println("--------------------------");
                    System.out.println("注册成功，请返回登录");
                }
            }
        }


    }
    public static void qc( Student student) throws ClassNotFoundException {
        Class.forName("com.zjm.dao.Dao");
        if (Dao.list2.size()==0){
            Dao.studentAdd(student);
            System.out.println("--------------------------");
            System.out.println("添加成功!!!");
        }else {
            for (int i = 0; i < Dao.list2.size(); i++) {
                if (Dao.list2.get(i).getId()==(student.getId())){
                    System.out.println("--------------------------");
                    System.out.println("您输入的用户已经存在，请返回登录");
                    break;
                }else {
                    Dao.studentAdd(student);
                    System.out.println("--------------------------");
                    System.out.println("添加成功!!!");
                }
            }
        }


    }
    public static void sc(int id) throws ClassNotFoundException {
        Class.forName("com.zjm.dao.Dao");
        int a=0;
        if (Dao.list2.size() == -1) {
            System.out.println("----------------");
            System.out.println("信息库为空，请先添加");
            System.out.println("----------------");
        }else {
            for (int i = 0; i < Dao.list2.size(); i++) {
                Student student = Dao.list2.get(i);
                if (student.getId() == id) {
                    Dao.remove(i);
                    System.out.println("--------------------------");
                    System.out.println("成功删除！！！");
                    a++;
                    break;
                }
            }
            if (a == 0)
                System.out.println("--------------------------\n查无此人!!!");
        }
    }
    public static void xg(int id) throws ClassNotFoundException {
        Class.forName("com.zjm.dao.Dao");
        Scanner scanner = new Scanner(System.in);
        int a=0;
        int i;
        if (Dao.list2.size() == -1) {
            System.out.println("----------------");
            System.out.println("信息库为空，请先添加");
            System.out.println("----------------");
        }else {
            for (i=0; i < Dao.list2.size(); i++) {
                Student student = Dao.list2.get(i);
                if (student.getId() == id) {
                    System.out.println(student.toString());
                    System.out.println("请输入你要修改的内容\n例如 学号：xxx\t按q退出");
                    String next = scanner.next();
                    Dao.setStudent(i,next);
                    a++;
                    System.out.println("--------------------------\n修改成功！！！");
                }
            }
            if (a == 0) {
                System.out.println("--------------------------\n查无此人!!!");
            }
        }
    }
    public static void select(int id) throws ClassNotFoundException {
        Class.forName("com.zjm.dao.Dao");
        int a=0;
        for (int i = 0; i < Dao.list2.size(); i++) {
            Student student = Dao.list2.get(i);
            if (student.getId() == id) {
                System.out.println("学号为："+id+"的学生信息如下");
                System.out.println("--------------------------");
                System.out.println(student.toString());
                a++;
                break;
            }
            if (a == 0)
                System.out.println("查无此人");
        }

    }
}


