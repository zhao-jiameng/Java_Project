package source;

import org.apache.flume.Context;
import org.apache.flume.Event;
import org.apache.flume.EventDeliveryException;
import org.apache.flume.PollableSource;
import org.apache.flume.conf.Configurable;
import org.apache.flume.event.SimpleEvent;
import org.apache.flume.source.AbstractSource;

import java.nio.charset.StandardCharsets;
import java.util.UUID;
import java.util.concurrent.TimeUnit;

/**
 * 需求：每一秒钟随机生成一个字符串（模拟日志）
 * 自定义Source需要继承AstractSource，并实现Configurable PollableSource接口
 */
public class MySource extends AbstractSource implements Configurable, PollableSource {
    public  String prefix;
    @Override
    public Status process() throws EventDeliveryException {
        try {
            TimeUnit.SECONDS.sleep(1);      //休息1s
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        Status status;
        try {
            //采集数据（从文件、端口、数据库读取）
            Event e=getSomeData();
            //将event传入channelProcessor进行处理（拦截器、channelSelector）
            getChannelProcessor().processEvent(e);
            //event处理成功
            status=Status.READY;
        }catch (Throwable e){
            //失败
            status=Status.BACKOFF;
        }

        return status;
    }

    private Event getSomeData() {
        String log = UUID.randomUUID().toString();
        SimpleEvent event = new SimpleEvent();
        event.setBody((prefix +log).getBytes());
        //event.getHeaders().put
        return event;
    }

    @Override
    public long getBackOffSleepIncrement() {    //每次退避增长时间
        return 1000;
    }

    @Override
    public long getMaxBackOffSleepInterval() {  //最大BackOff时间
        return 10000;
    }

    @Override
    public void configure(Context context) {    //读取配置文件内容
        context.getString("prefix","logs-");
    }
}
