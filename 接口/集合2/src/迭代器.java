import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;

public class 迭代器 {
    public static void main(String[] args) {
        Collection c=new ArrayList();
        c.add("abc");
        c.add("bcd");
        c.add("efg");
        System.out.println(c.size());
        c.clear();
        System.out.println(c.size());
        c.add("abc");
        c.add("bcd");
        c.remove("abc");
        System.out.println(c.isEmpty());
        Iterator it=c.iterator();
        while (it.hasNext()){
            Object o=it.next();
            System.out.println(o);
        }
    }


}
