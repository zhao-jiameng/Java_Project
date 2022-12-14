package com.zjm.controller;


import com.zjm.dao.Dao;
import com.zjm.service.StudentService;
import com.zjm.domain.Student;
import com.zjm.domain.User;

import java.util.InputMismatchException;
import java.util.Scanner;

public class StudentHello {
    public static void hello() throws ClassNotFoundException {
        Scanner scanner = new Scanner(System.in);
        while (true) {
            System.out.println("--------------------------");
            System.out.println("欢迎使用学生管理系统：\n请输入您要实现的功能\n1.登录\n2.注册\n3.退出");
            int a = 0;
            try {
                a = scanner.nextInt();
            } catch (InputMismatchException e) {
                System.out.println("--------------------------");
                System.out.println("输入异常：" + e.toString());
                System.out.println("请按规定重新输入：");
                scanner.next();
            }
            if (a > 0 && a < 4) {
                switch (a) {
                    case 1:
                        System.out.println("--------------------------");
                        System.out.println("请输入用户名：");
                        String username = scanner.next();
                        System.out.println("请输入密码：");
                        String password = scanner.next();
                        StudentService.dl(username,password);
                        break;
                    case 2:
                        User user = new User();
                        System.out.println("--------------------------");
                        System.out.println("请输入用户名：");
                        user.setUser(scanner.next());
                        System.out.println("请输入密码：");
                        user.setPassword(scanner.next());
                        StudentService.zc(user);
                        break;
                    case 3:
                        System.out.println("--------------------------");
                        System.out.println("正在退出，感谢使用！");
                        System.exit(0);
                    default:
                        System.out.println("输入错误，请重新输入：");
                        break;
                }
            } else {
                System.out.println("--------------------------");
                System.out.println("输入错误，请重新输入：");
            }
        }
    }
    public static void xsglxt() throws ClassNotFoundException {
        Scanner scanner = new Scanner(System.in);
        while (true) {

            System.out.println("--------------------------");
            System.out.println("欢迎登录学生管理系统：\n请输入您要实现的功能\n1.添加学生\n2.删除学生\n3.修改信息\n4.查询信息\n5.显示所有\n6.退出系统");
            try {
                int a = scanner.nextInt();
                if (a > 0 && a < 7) {
                    switch (a) {
                        case 1:
                            Student student = new Student();
                            System.out.println("--------------------------");
                            System.out.println("请依次录入学生信息：\n请输入学生ID：");
                            student.setId(scanner.nextInt());
                            System.out.println("请输入学生姓名");
                            student.setName(scanner.next());
                            System.out.println("请输入学生性别");
                            student.setSex(scanner.next());
                            System.out.println("请输入学生年龄");
                            student.setAge(scanner.nextInt());
                            System.out.println("请输入学生班级");
                            student.setCls(scanner.nextInt());
                            System.out.println("请输入学生专业");
                            student.setZy(scanner.next());
                            StudentService.qc(student);
                            break;
                        case 2:
                            System.out.println("--------------------------");
                            System.out.println("请输入你要删除的学号：");
                            StudentService.sc(scanner.nextInt());
                            break;
                        case 3:
                            System.out.println("--------------------------");
                            System.out.println("请输入你要修改的学生学号");
                            StudentService.xg(scanner.nextInt());
                            break;
                        case 4:
                            System.out.println("--------------------------");
                            System.out.println("请输入你要查找的学号：");
                            StudentService.select(scanner.nextInt());
                            break;
                        case 5:
                            new Dao();
                            System.out.println("--------------------------");
                            Dao.list2.forEach(li -> System.out.println(li));
                            break;
                        case 6:
                            System.out.println("正在退出登录，感谢使用！");
                            System.exit(0);
                        default:
                            System.out.println("输入错误，请重新输入：");
                            break;
                    }
                } else {
                    System.out.println("------------------");
                    System.out.println("输入错误，请重新输入：");
                }
            } catch (InputMismatchException e) {
                System.out.println("--------------------------");
                System.out.println("输入异常：" + e.toString());
                System.out.println("请按规定重新输入：");
                scanner.next();
            }
        }
    }
}
