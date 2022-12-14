import java.util.Scanner;
public class 酒店管理 {
//        public class Room{
//            int 房间编号=0;
//            String 房间类型=null;
//            boolean 空闲=false;
//        }
//
//
//    public 酒店管理() {
//    }
    //    public String[][] get房间() {
//        return 房间;
//    }
//
//    public void set房间(String[][] 房间) {
//        this.房间 = 房间;
//    }
//
//    public 酒店管理(String[][] 房间) {
//        this.房间 = 房间;
//    }

    public static void main(String[] args) {
        Scanner s=new Scanner(System.in);
         String [][] 房间 ={
                {"001","普通房","RU"},
                {"002","普通房","空闲"},
                {"003","普通房","空闲"},
                {"011","大床房","空闲"},
                {"012","大床房","空闲"},
                {"013","大床房","空闲"},
                {"101","影院房","空闲"},
                {"102","影院房","空闲"},
                {"103","影院房","空闲"}
        };
        for (int i=0;i< 房间.length;i++){
            for (int j=0;j<3;j++){
                System.out.print(房间[i][j]+" ");
            }
            System.out.println();
        }
        String a= s.next();
        for (int i=0;i< 房间.length;i++){

                if (a.equals(房间[i][0])){
                    if (房间[i][2].equals("空闲")){
                        System.out.println("预订成功");
                        return;
                    }else{
                        System.out.println("房间已近有人");
                        return;
                    }


            }



        }
        System.out.println("没有这个房间");
        return;


    }
}
