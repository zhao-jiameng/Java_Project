public class Ztransportation extends Tranportation implements Careable{

    public Ztransportation() {
        super();
    }

    public Ztransportation(String number, String model, String admin) {
        super(number, model, admin);
    }

    @Override
    public void upKeep() {
        System.out.println("货物运输车保养完毕");
    }

    @Override
    public void transport() {
        System.out.println("运输进行中……");
    }
}
