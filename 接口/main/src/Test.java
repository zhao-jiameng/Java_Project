import java . util . Random ;
import java . util . Scanner ;
public class Test {
    public static void main(String[] args) {
  /*     int [] arr={1,2,3,4,5,6,7,8,9};
       int max=0;
       for (int i=0;i< arr.length;i++){
           if (max<arr[i]){
               max=arr[i];
           }

       }
       System.out.println("最大值："+max);*/

      /*  int i=100;
        Integer x=i;
        int s=x;

        String a="1234";
        int b=Integer.parseInt(a);
        String s1=String.valueOf(i);

        Integer m=Integer.valueOf(s);
        String s2=String.valueOf(m);*/


        System.out.println("-------﹣抽取幸运观众﹣--------");

        String [] audience= new String[3];

        Test.audience.addAudience(audience);

        Test.audience.printAudience(audience);

        String randomName = Test.audience.randomAudience(audience);
        System.out.println("随机抽取的幸运观众是：" + randomName);

    }

    public static class audience {
        public static void addAudience(String[] a) {
            Scanner sc = new Scanner(System.in);
            for (int i = 0; i < a.length; i++) {
                System.out.println("存储第" + (i + 1) + "个姓名：");
                a[i] = sc.next();
            }
        }

        public static void printAudience(String[] a) {

            for (int i = 0; i < a.length; i++) {
                String name = a[i];
                System.out.println("第" + (i + 1) + "个观众姓名：" + name);
            }
        }

        public static String randomAudience(String[] a){

                int index = new Random().nextInt(a.length);
                String name = a[index];
                return name;
        }
    }
}
