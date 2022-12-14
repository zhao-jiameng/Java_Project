import java.util.TreeSet;

public class TreeSetTest {
    public static void main(String[] args) {
        user a1=new user(122);
        user a2=new user(2);
        user a3=new user(12);
        user a4=new user(22);
        user a5=new user(256);
        TreeSet<user> set=new TreeSet<>();
        set.add(a1);
        set.add(a2);
        set.add(a3);
        set.add(a4);
        set.add(a5);
        for(user ue:set){
            System.out.println(ue);
        }

    }


}
class user implements Comparable<user>{             //自定义类需要实现接口
    int age;

    public user(int age) {
        this.age = age;
    }

    @Override
    public String toString() {
        return "user{" + "age=" + age + '}';
    }
    @Override       //重写比较规则
    public int compareTo(user o) {
       return this.age-o.age;
    }
}
