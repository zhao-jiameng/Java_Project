public class 模拟栈 {

    private Object[] 栈;
    private int 栈帧;
    public Object[] get栈() {
        return 栈;
    }

    public void set栈(Object[] 栈) {
        this.栈 = 栈;
    }

    public int get栈帧() {
        return 栈帧;
    }

    public void set栈帧(int 栈帧) {
        this.栈帧 = 栈帧;
    }



    public 模拟栈() {
        this.栈=new Object[10];
        this.栈帧=-1;
    }

    public  void push(Object obj) throws 我的栈异常 {
        if (this.栈帧>1){
//            System.out.println("栈已满，压栈失败");
//            return;
            throw new 我的栈异常("栈已满，压栈失败");
        }else{

            栈[++栈帧]=obj;

            System.out.println("压栈"+obj+"成功，当前栈帧指向"+栈帧);
        }
    }
    public  Object pop() throws 我的栈异常 {
        if (栈帧<0){
//            System.out.println("栈已空，弹栈失败");
//            return null;
            throw new 我的栈异常("栈已空，弹栈失败");
        }else{
            Object a=栈[栈帧];
            System.out.println("弹栈"+a+"成功");
            栈帧--;
            System.out.println("当前栈帧指向"+栈帧);
            return a;
        }
    }

    public static void main(String[] args) {
        模拟栈 b=new 模拟栈();

        try {
            b.push(new Object());

            b.pop();
            b.pop();
            b.pop();
            b.pop();
            b.pop();
            b.pop();


        } catch (我的栈异常 e) {

            System.out.println(e.getMessage());
        }



    }
}
