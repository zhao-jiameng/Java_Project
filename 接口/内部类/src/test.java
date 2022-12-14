public class test {
    public static void main(String[] args) {
        计算 c=new 计算();
        c.计算(new 接口() {
            @Override
            public int sum(int a, int b) {
                return a+b;
            }
        },
                100, 100);
//        c.计算(new 实现(),100,20 );
    }
}
