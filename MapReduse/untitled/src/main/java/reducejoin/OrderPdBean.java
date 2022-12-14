package reducejoin;

import org.apache.hadoop.io.Writable;

import java.io.DataInput;
import java.io.DataOutput;
import java.io.IOException;

public class OrderPdBean implements Writable {
    private String orderId;
    private String pid;
    private Integer amount;
    private String pname;
    private String title;

    public String getOrderId() {
        return orderId;
    }

    public void setOrderId(String orderId) {
        this.orderId = orderId;
    }

    public String getPid() {
        return pid;
    }

    public void setPid(String pid) {
        this.pid = pid;
    }

    public Integer getAmount() {
        return amount;
    }

    public void setAmount(Integer amount) {
        this.amount = amount;
    }

    public String getPname() {
        return pname;
    }

    public void setPname(String pname) {
        this.pname = pname;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public OrderPdBean() {
    }

    @Override
    public void write(DataOutput dataOutput) throws IOException {
        dataOutput.writeUTF(orderId);
        dataOutput.writeUTF(pid);
        dataOutput.writeUTF(pname);
        dataOutput.writeUTF(title);
        dataOutput.writeInt(amount);

    }

    @Override
    public void readFields(DataInput dataInput) throws IOException {
        orderId= dataInput.readUTF();
        pid= dataInput.readUTF();
        amount=dataInput.readInt();
        pname= dataInput.readUTF();
        title= dataInput.readUTF();
    }

    @Override
    public String toString() {
        return orderId+"\t"+pname+"\t"+amount;
    }
}
