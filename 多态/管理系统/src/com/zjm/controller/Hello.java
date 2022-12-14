package com.zjm.controller;

import java.util.InputMismatchException;
import java.util.Scanner;

public class Hello {


    public static void hello() throws ClassNotFoundException {
        Scanner scanner = new Scanner(System.in);
        while (true) {
            System.out.println("-------山西工院管理系统--------");
            System.out.println("欢迎使用管理系统：\n请输入您要登录的页面\n1.学生端\n2.教师端\n3.退出系统");
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
                        StudentHello.hello();
                        break;
                    case 2:
                        TeacherHello.hello();
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
}
