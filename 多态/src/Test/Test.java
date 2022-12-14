package Test;

import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.Scanner;
public class Test {
    public static void main(String[] args) {
        ArrayList<User> list2 = new ArrayList<>();
        Scanner scanner = new Scanner(System.in);
        l:
        while (true) {
            System.out.println("--------------------------");
            System.out.println("欢迎使用学生管理系统：\n请输入您要实现的功能\n 1.登录\n2.注册\n3.退出");
            int a = 0;
            try {
                a = scanner.nextInt();
            } catch (InputMismatchException e) {
                System.out.println("--------------------------");
                System.out.println("输入异常：" + e);
                System.out.println("请按规定重新输入：");
                scanner.next();
            }
            if (a > 0 && a < 4) {
                switch (a) {
                    case 1:
                        dl(list2);

                        break;
                    case 2:
                        create(list2);
                        break;
                    case 3:
                        System.out.println("--------------------------");
                        System.out.println("正在退出，感谢使用！");
                        break l;
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

    private static void dl(ArrayList<User> list2) {
        Scanner scanner = new Scanner(System.in);
        System.out.println("--------------------------");
        System.out.println("请输入用户名：");
        String next = scanner.next();
        System.out.println("请输入密码：");
        String next2 = scanner.next();
        if (list2.size() == 0) {
            System.out.println("----------------");
            System.out.println("请先注册在尝试登录");
        } else {
            for (User user : list2) {
                if (user.getUser().equals(next) && user.getPassword().equals(next2)) {
                    System.out.println("登录成功，欢迎进入学生管理系统！");
                    System.out.println("--------------------------");
                    glxt();
                } else {
                    System.out.println("--------------------------");
                    System.out.println("登录失败，请检查用户名或密码是否正确");
                    break;
                }
            }
        }

    }

    private static void create(ArrayList<User> list2) {
        Scanner scanner = new Scanner(System.in);
        User user = new User();
        System.out.println("--------------------------");
        System.out.println("请输入用户名：");
        user.setUser(scanner.next());
        System.out.println("请输入密码：");
        user.setPassword(scanner.next());
        list2.add(user);
    }

    private static void glxt() {
        ArrayList<Student> list = new ArrayList<>();
        Scanner scanner = new Scanner(System.in);
        l:
        while (true) {

            System.out.println("--------------------------");
            System.out.println("欢迎登录学生管理系统：\n请输入您要实现的功能\n 1.添加学生\n2.删除学生\n3.修改信息\n4.查询信息\n5.显示所有\n6.退出系统");
            try {
                int a = scanner.nextInt();
                if (a > 0 && a < 7) {
                    switch (a) {
                        case 1:
                            Student insertxs = insertxs();
                            list.add(insertxs);
                            break;
                        case 2:
                            remove(list);
                            break;
                        case 3:
                            Student setxs = setxs(list);
                            list.set(list.indexOf(setxs), setxs);
                            break;
                        case 4:
                            select(list);
                            break;
                        case 5:
                            show(list);
                            break;
                        case 6:
                            System.out.println("正在退出登录，感谢使用！");
                            break l;
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
                System.out.println("输入异常：" + e);
                System.out.println("请按规定重新输入：");
                scanner.next();
            }
        }
    }

    private static void show(ArrayList<Student> list) {
        System.out.println("--------------------------");
        list.forEach(System.out::println);
    }

    private static void select(ArrayList<Student> list) {
        Scanner scanner = new Scanner(System.in);
        int a = 0;
        System.out.println("--------------------------");
        System.out.println("请输入你要查找的学号：");
        int index = scanner.nextInt();
        for (Student student : list) {
            if (student.getId() == index) {
                System.out.println(student);
                a++;
                break;
            }

        }
        if (a == 0)
            System.out.println("查无此人");


    }

    private static Student setxs(ArrayList<Student> list) {
        Scanner scanner = new Scanner(System.in);
        System.out.println("--------------------------");
        System.out.println("请输入你要修改的学生学号");
        int index = scanner.nextInt();
        int a = 0;
        int b = -1;

        for (int i = 0; i < list.size(); i++, b++) {
            Student student = list.get(i);
            if (student.getId() == index) {
                System.out.println(student);
                System.out.println("请输入你要修改的内容\n例如 学号：xxx\t按q退出");
                String next = scanner.next();
                String[] split = next.split(":");
                a++;
                switch (split[0]) {
                    case "学号":
                        list.get(i).setId(Integer.parseInt(split[1]));
                        break;
                    case "姓名":
                        list.get(i).setName(split[1]);
                        break;
                    case "年龄":
                        list.get(i).setAge(Integer.parseInt(split[1]));
                        break;
                    case "班级":
                        list.get(i).setCls(Integer.parseInt(split[1]));
                        break;
                    case "专业":
                        list.get(i).setZy(split[1]);
                        break;
                    case "q":
                        break;
                    default:
                        System.out.println("输入错误");
                        break;
                }
            }
        }
        if (a == 0)
            System.out.println("查无此人");
        return list.get(b);
    }

    private static void remove(ArrayList<Student> list) {
        Scanner scanner = new Scanner(System.in);
        System.out.println("--------------------------");
        System.out.println("请输入你要删除的学号：");
        int index = scanner.nextInt();
        int a = 0;
        if (list.size() == 0) {
            System.out.println("----------------");
            System.out.println("信息库为空，请先添加");
            System.out.println("----------------");
        } else {
            for (int i = 0; i < list.size(); i++) {
                Student student = list.get(i);
                if (student.getId() == index) {
                    list.remove(i);
                    System.out.println("--------------\n成功删除！！！");
                    a++;
                    break;
                }

            }
            if (a == 0)
                System.out.println("查无此人");
        }
    }

    private static Student insertxs() {
        //去重
        Scanner scanner = new Scanner(System.in);
        Student student = new Student();
        System.out.println("--------------------------");
        System.out.println("请依次录入学生信息：\n请输入学生ID：");
        student.setId(scanner.nextInt());
        System.out.println("请输入学生姓名");
        student.setName(scanner.next());
        System.out.println("请输入学生年龄");
        student.setAge(scanner.nextInt());
        System.out.println("请输入学生班级");
        student.setCls(scanner.nextInt());
        System.out.println("请输入学生专业");
        student.setZy(scanner.next());

        System.out.println("新建成功！！！");
        return student;


    }
}
