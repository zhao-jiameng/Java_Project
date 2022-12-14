package Test;

import java.util.ArrayList;
import java.util.stream.Collectors;

public class Test2 {
    public static void main(String[] args) {
        ArrayList<Student> list = new ArrayList<>();
        list.add(new Student(1, "zhangsan", 15, 2101, "bigdata"));
        list.add(new Student(2, "lisi", 18, 2101, "bigdata"));
        list.add(new Student(3, "wangwu", 10, 2101, "bigdata"));
        list.forEach(System.out::println);       //需求1
        ArrayList<Student> age = age(list);
        System.out.println("年龄小于18的为：");
        age.forEach(System.out::println);        //需求2

    }

    private static ArrayList<Student> age(ArrayList<Student> list) {
        ArrayList<Student> list2 = list.stream().filter(student -> student.getAge() < 18).collect(Collectors.toCollection(ArrayList::new));
        return list2;

    }
}
