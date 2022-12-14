import java.util.Scanner;
public class 酒店管理系统 {
    public static void main(String[] args) {

        酒店 酒店 = new 酒店();
        Scanner s = new Scanner(System.in);
        System.out.println("欢迎使用酒店管理系统");

        while (true) {
            System.out.println("输入【1】查看房间，输入【2】订房，输入【3】退房，【4】退出系统");
            int a = s.nextInt();

            if (a == 1) {
                酒店.print();

            }else if(a==2){
                System.out.println("输入你要订的房间");
                int b= s.nextInt();
                if (b<100||b>110&&b<200||b>210&&b<300||b>310){
                    System.out.println("输入错误");
                }else{
                    酒店.订房(b);
                }


            }else if(a==3){
                System.out.println("输入你要退的房间");
                int b= s.nextInt();
                if (b<100||b>110&&b<200||b>210&&b<300||b>310){
                    System.out.println("输入错误");
                }else {
                    酒店.退房(b);
                }

            }else if(a==4){
                return;
            }else{
                System.out.println("输入错误");

            }
        }
    }
}
