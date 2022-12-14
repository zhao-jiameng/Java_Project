package Sink;

import org.apache.flume.*;
import org.apache.flume.conf.Configurable;
import org.apache.flume.sink.AbstractSink;
import org.slf4j.LoggerFactory;

import java.util.concurrent.TimeUnit;
import java.util.logging.Logger;

/**
 * 需求：将event通过logger打印到控制台
 */
public class MySink extends AbstractSink implements Configurable {
    Logger logger= (Logger) LoggerFactory.getLogger(MySink.class);
    @Override
    public Status process() throws EventDeliveryException {
        Status status;
        //获取Channel
        Channel channel = getChannel();
        //获取事务
        Transaction transaction = channel.getTransaction();
        //开启事务
        transaction.begin();
        try {
            //take数据(保证能take到数据)
            Event event;
            while (true){
                 event = channel.take();
                if (event==null){
                    TimeUnit.SECONDS.sleep(1);
                    continue;
                }
                break;
            }
        //处理event
         storeSomeData(event);
            //提交事务
            status=Status.READY;
        }catch (Throwable e){
            transaction.rollback();
            status=Status.BACKOFF;
        }finally {
            transaction.close();
        }
        return status;
    }

    private void storeSomeData(Event event) {
        //对event的处理（通过log4j）
        logger.info(String.valueOf(event.getBody()));
    }


    @Override
    public void configure(Context context) {

    }
}
