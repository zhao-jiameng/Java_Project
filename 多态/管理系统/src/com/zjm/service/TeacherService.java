package com.zjm.service;


import com.zjm.controller.TeacherHello;
import com.zjm.dao.Dao;
import com.zjm.domain.Admin;

import java.util.List;

public class TeacherService {
    public static void dl( String user, String password) throws ClassNotFoundException {
        Class.forName("com.zjm.dao.Dao");
        if (Dao.list3.size() == 0) {
            System.out.println("--------------------------");
            System.out.println("请先注册在尝试登录");
        } else {
            for (int i = 0; i < Dao.list3.size(); i++) {
                if (Dao.list3.get(i).getUser().equals(user) && Dao.list3.get(i).getPassword().equals(password)) {
                    System.out.println("登录成功，欢迎进入教师管理系统！");
                    System.out.println("--------------------------");
                    TeacherHello.jsglxt();
                } else {
                    System.out.println("-----------------------------");
                    System.out.println("登录失败，请检查用户名或密码是否正确");
                }
            }
        }

    }
    public static void zc( Admin admin) throws ClassNotFoundException {
        Class.forName("com.zjm.dao.Dao");
        if (Dao.list3.size()==0){
            Dao.teacherAdd(admin);
            System.out.println("--------------------------");
            System.out.println("注册成功，请返回登录");
        }else {
            for (int i = 0; i < Dao.list3.size(); i++) {
                if (Dao.list3.get(i).getUser().equals(admin.getUser())){
                    System.out.println("--------------------------");
                    System.out.println("您输入的用户已经存在，请返回登录");
                }else {
                    Dao.teacherAdd(admin);
                    System.out.println("--------------------------");
                    System.out.println("注册成功，请返回登录");
                }
            }
        }


    }
    public static void sr(int nextInt) throws ClassNotFoundException {
        Class.forName("com.zjm.dao.Dao");
        if (Dao.list2.size()==0){
            System.out.println("系统为空，请先添加学生");
        }
        for (int i = 0; i < Dao.list2.size(); i++) {
            if (Dao.list2.get(i).getAge()<=nextInt){
                System.out.println(Dao.list2.get(i));
            }else {
                System.out.println("系统内没有小于"+nextInt+"岁的同学");
            }
        }

    }
    public static void nr() throws ClassNotFoundException {
        Class.forName("com.zjm.dao.Dao");
        int index=0;
        if (Dao.list2.size()==0){
            System.out.println("系统为空，请先添加学生");
        }
        for (int i = 0; i < Dao.list2.size(); i++) {
            if (Dao.list2.get(i).getSex().equals("男")){
                index++;
            }
        }
        if (index==0){
            System.out.println("系统内没有男同学的信息");
        }else {
            System.out.println("系统内男同学人数为："+index+"人");
        }

    }
    public static void ntx() throws ClassNotFoundException {
        Class.forName("com.zjm.dao.Dao");
        int index=0;
        if (Dao.list2.size()==0){
            System.out.println("系统为空，请先添加学生");
        }
        for (int i = 0; i < Dao.list2.size(); i++) {
            if (Dao.list2.get(i).getSex().equals("女")){
                index++;
            }
        }
        if (index==0){
            System.out.println("系统内没有女同学的信息");
        }else {
            System.out.println("系统内女同学人数为："+index+"人");
        }
    }
    public static void nxx() throws ClassNotFoundException {
        Class.forName("com.zjm.dao.Dao");
        int index=0;
        if (Dao.list2.size()==0){
            System.out.println("系统为空，请先添加学生");
        }
        for (int i = 0; i < Dao.list2.size(); i++) {
            if (Dao.list2.get(i).getSex().equals("男")){
                System.out.println(Dao.list2.get(i).toString());
                index++;
            }
        }
        if (index==0){
            System.out.println("系统内没有男同学的信息");
        }

    }
    public static void ntxxx() throws ClassNotFoundException {
        Class.forName("com.zjm.dao.Dao");
        int index=0;
        if (Dao.list2.size()==0){
            System.out.println("系统为空，请先添加学生");
        }
        for (int i = 0; i < Dao.list2.size(); i++) {
            if (Dao.list2.get(i).getSex().equals("女")){
                System.out.println(Dao.list2.get(i).toString());
                index++;
            }
        }
        if (index==0){
            System.out.println("系统内没有女同学的信息");
        }

    }
}
