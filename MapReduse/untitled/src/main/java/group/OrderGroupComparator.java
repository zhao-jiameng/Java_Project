package group;

import org.apache.hadoop.io.WritableComparable;
import org.apache.hadoop.io.WritableComparator;

import java.io.DataInput;
import java.io.DataOutput;
import java.io.IOException;

public class OrderGroupComparator extends WritableComparator {
    public OrderGroupComparator() {
        super(OrderBean.class,true);
    }


    @Override
    public int compare(WritableComparable a, WritableComparable b) {
        OrderBean abean=(OrderBean) a;
        OrderBean bbean=(OrderBean) b;

        return abean.getOrderId().compareTo(((OrderBean) b).getOrderId());
    }
}
