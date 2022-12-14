package writable;

import org.apache.hadoop.io.Writable;

import java.io.DataInput;
import java.io.DataOutput;
import java.io.IOException;

/**
 * 用于封装一个手机号的 上行 下行 总流量
 * 因为FlowBean要作为value在mr过程中传入，且会落盘，因此需要支持序列化和反序列化
 */
public class FlowBean  implements Writable {
    public FlowBean() {
    }

    private Long upFlow;
    private Long downFlow;
    private Long sumFlow;

    public Long getUpFlow() {
        return upFlow;
    }

    public void setUpFlow(Long upFlow) {
        this.upFlow = upFlow;
    }

    public Long getDownFlow() {
        return downFlow;
    }

    public void setDownFlow(Long downFlow) {
        this.downFlow = downFlow;
    }

    public Long getSumFlow() {
        return sumFlow;
    }

    public void setSumFlow(Long sumFlow) {
        this.sumFlow = sumFlow;
    }
    public void setSumFlow(){
        this.sumFlow=this.getUpFlow()+this.getDownFlow();
    }

    /**
     * 序列化
     * @param dataOutput
     * @throws IOException
     */
    @Override
    public void write(DataOutput dataOutput) throws IOException {
        dataOutput.writeLong(upFlow);
        dataOutput.writeLong(downFlow);
        dataOutput.writeLong(sumFlow);
    }


    /**
     * 反序列化
     * 注意：反序列化读取的过程需要和序列化的写出数据顺序一致
     * @param dataInput
     * @throws IOException
     */
    @Override
    public void readFields(DataInput dataInput) throws IOException {
        this.upFlow= dataInput.readLong();
        this.downFlow= dataInput.readLong();
        this.sumFlow=dataInput.readLong();
    }

    @Override
    public String toString() {
        return this.upFlow+"\t"+this.downFlow+"\t"+this.sumFlow;
    }
}
