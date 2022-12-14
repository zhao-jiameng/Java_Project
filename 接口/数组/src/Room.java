public class Room {
    private  int 房间编号;
    private String 房间类型;
    private boolean 状态;

    public Room() {
    }

    public Room(int 房间编号, String 房间类型, boolean 空闲) {
        this.房间编号 = 房间编号;
        this.房间类型 = 房间类型;
        this.状态 = 空闲;
    }

    public int get房间编号() {
        return 房间编号;
    }

    public void set房间编号(int 房间编号) {
        this.房间编号 = 房间编号;
    }

    public String get房间类型() {
        return 房间类型;
    }

    public void set房间类型(String 房间类型) {
        this.房间类型 = 房间类型;
    }

    public boolean get空闲() {
        return 状态;
    }

    public void set空闲(boolean 空闲) {
        this.状态 = 空闲;
    }

    @Override
    public boolean equals(Object obj) {
        if(obj==null||!(obj instanceof Room))
                return false;
        if(this==obj)
                return true;
        Room a=(Room) obj;
        return this.房间编号==a.get房间编号();

    }

    @Override
    public String toString() {
        return "["+房间编号+","+房间类型+","+(状态?"空闲":"有人")+"]";
    }
}
