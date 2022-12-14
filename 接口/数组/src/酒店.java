public class 酒店 {
    private Room[][] rooms;

    public 酒店() {
       rooms=new Room[3][10];
       for (int i=0;i<rooms.length;i++){
           for (int j=0;j<rooms[1].length;j++){
               if (i==0)
                rooms[i][j]=new Room((i+1)*100+j+1,"单人间",true);
               if (i==1)
                   rooms[i][j]=new Room((i+1)*100+j+1,"大床房",true);
               if (i==2)
                   rooms[i][j]=new Room((i+1)*100+j+1,"总统套",true);
           }
       }
    }
    public void print(){
        for (int i=0;i<rooms.length;i++){
            for (int j=0;j<rooms[1].length;j++){
                System.out.print(rooms[i][j]+"  ");

            }
            System.out.println();
        }
    }
    public void  订房(int 房间编号){
        Room room=rooms[房间编号/100-1][房间编号%100-1];
        if(room.get空闲()==true){
            room.set空闲(false);
            System.out.println(房间编号+"已成功预定");

        }else{
            System.out.println("已被预定");
        }

    }
    public void  退房(int 房间编号){
        Room room=rooms[房间编号/100-1][房间编号%100-1];
        if(room.get空闲()==true){
            System.out.println("无人居住");
        }else {
            room.set空闲(true);
            System.out.println(房间编号+"已退房成功");
        }


    }
}
