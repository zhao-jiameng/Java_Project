public class Test {
    public static void main(String[] args) {
        SendTask task=new SendTask("123456",12.34);
        task.sendBefore();
        System.out.println("------------------------------------------");
        Ztransportation t=new Ztransportation("z123","吉普","小赵");
        Phone phone = new Phone();
        task.sand(t,phone);
        System.out.println("------------------------------------------");
        task.sendAfter(t);
        t.upKeep();
    }
}
