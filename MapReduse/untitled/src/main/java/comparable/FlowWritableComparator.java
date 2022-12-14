package comparable;

import org.apache.hadoop.io.WritableComparable;
import org.apache.hadoop.io.WritableComparator;

/**
 * 比较器对象
 */
public class FlowWritableComparator extends WritableComparator {


    public FlowWritableComparator() {
        super(FlowBean.class,true);
    }
    @Override
    public int compare(WritableComparable a, WritableComparable b) {
        FlowBean abean=(FlowBean) a;
        FlowBean bbean=(FlowBean) b;
        return -abean.getSumFlow().compareTo(bbean.getSumFlow());

    }
}
