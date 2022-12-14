import java.util.*;

public class Maptest {
    public static void main(String[] args) {
        Map<Integer, String> m=new HashMap<>();
        m.put(1,"zhangsan");
        m.put(2,"lishi");
        m.put(3,"wangwu");
        String a=m.get(2);
        int b=m.size();
        System.out.println(b);
        System.out.println(a);
        Collection<String> values=m.values();
        for (String s:values) {
            System.out.println(s);
        }
        //取出元素第一种方式
        Set<Integer> set=m.keySet();
        for(Integer in:set){
            System.out.println(in+"       "+m.get(in));
        }
        System.out.println("--------------------第二种------------------------");
        Set<Map.Entry<Integer,String>>set1=m.entrySet();
        Iterator<Map.Entry<Integer,String>> it=set1.iterator();
        while (it.hasNext()) {
            Map.Entry<Integer, String> entry = it.next();
            System.out.println(entry);
            Integer key = entry.getKey();
            String value = entry.getValue();
            System.out.println(key + "=" + value);
        }
        for(Map.Entry<Integer,String> node:set1){
            System.out.println(node);
        }

    }
}
