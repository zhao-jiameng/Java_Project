public class text {
    public static void main(String[] args) {
        hanoi(3, 'x', 'y', 'z');
    }
    public static void move(char x,int n,char z){
        System.out.println("移动"+n+"从"+x+"到"+z);
    }
    public static void hanoi(int n,char a,char b,char c){
        if (n==1){
            move(a,1,c);

        }else{
            hanoi(n-1,a,b,c);
            move(a,n,c);
            hanoi(n-1,b,a,c);
        }
    }
}
