package reducejoin;

import org.apache.commons.beanutils.BeanUtils;
import org.apache.hadoop.io.NullWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Reducer;

import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.List;

public class ReduceJoinReduce extends Reducer<Text,OrderPdBean,OrderPdBean, NullWritable> {
    private List<OrderPdBean> orders=new ArrayList<>();//存储一组数据中的所有order数据
    private OrderPdBean pdBean=new OrderPdBean();
    @Override
    protected void reduce(Text key, Iterable<OrderPdBean> values, Reducer<Text, OrderPdBean, OrderPdBean, NullWritable>.Context context) throws IOException, InterruptedException {
        //迭代该组数据，将来自不同文件kv分别存储
        for (OrderPdBean value : values) {
            //判断来自那个文件
            if (value.getTitle().equals("order")){              //order数据

                try {
                    OrderPdBean currentPdBean=new OrderPdBean();
                    BeanUtils.copyProperties(currentPdBean,value); //拷贝对象
                    orders.add(currentPdBean);
                } catch (IllegalAccessException e) {
                    e.printStackTrace();
                } catch (InvocationTargetException e) {
                    e.printStackTrace();
                }
            }else {                                             //pd数据
                try {
                    BeanUtils.copyProperties(pdBean,value);
                } catch (IllegalAccessException e) {
                    e.printStackTrace();
                } catch (InvocationTargetException e) {
                    e.printStackTrace();
                }

            }
        }
        //迭代order集合的数据，进行join操作并写出
        for (OrderPdBean bean : orders) {
           bean.setPname(pdBean.getPname());
        //写出
        context.write(bean,NullWritable.get());
        }
        //清空orders
        orders.clear();
    }
}
