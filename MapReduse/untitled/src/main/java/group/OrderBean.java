package group;

import org.apache.hadoop.io.WritableComparable;

import java.io.DataInput;
import java.io.DataOutput;
import java.io.IOException;

public class OrderBean implements WritableComparable<OrderBean> {
    private String orderId;
    private Double price;

    public String getOrderId() {
        return orderId;
    }

    public void setOrderId(String orderId) {
        this.orderId = orderId;
    }

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }

    public OrderBean() {}

    @Override
    public void write(DataOutput dataOutput) throws IOException {
        //dataOutput.writeChars();
        dataOutput.writeUTF(orderId);
        dataOutput.writeDouble(price);
    }

    @Override
    public void readFields(DataInput dataInput) throws IOException {
        this.orderId= dataInput.readUTF();
        this.price=dataInput.readDouble();
    }
    @Override
    public int compareTo(OrderBean o) {     //订单升序金额降序
        return this.orderId.compareTo(o.getOrderId())!=0?this.orderId.compareTo(o.getOrderId()):o.getPrice().compareTo(this.price);
    }

    @Override
    public String toString() {
        return orderId + '\t' + price ;
    }
}
