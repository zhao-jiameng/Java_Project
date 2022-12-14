package kafka;

import org.apache.kafka.clients.producer.Callback;
import org.apache.kafka.clients.producer.KafkaProducer;
import org.apache.kafka.clients.producer.ProducerRecord;
import org.apache.kafka.clients.producer.RecordMetadata;

import java.util.Properties;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Future;

public class ProducerDemo {
    public static void main(String[] args) throws ExecutionException, InterruptedException {
        //1、创建生产者
            //进行参数配置
        Properties properties = new Properties();
            //指定k的序列化类
        properties.setProperty("key.serializer", "org.apache.kafka.common.serialization.StringSerializer");
            //指定v的序列化类
        properties.setProperty("value.serializer", "org.apache.kafka.common.serialization.StringSerializer");
            //设置ack
        properties.setProperty("acks", "-1");
            //指定kafka节点，通过节点找到集群
        properties.setProperty("bootstrap.servers", "hadoop101:9092,hadoop102:9092");
            //指定批次大小
            //properties.setProperty("batch.size", "byte");
        KafkaProducer<String, String> producer = new KafkaProducer<String, String>(properties);
        //2、封装消息
        for (int i = 0; i < 100; i++) {
            ProducerRecord<String, String> first = new ProducerRecord<>("first", "这是发送的第" + i + "条消息");
            //3、异步发送消息
                //producer.send(first);
            //发送消息回调
            //接收到leader返回的确认信息后调用
            Future<RecordMetadata> send = producer.send(first, (recordMetadata, e) -> System.out.println(recordMetadata.topic() + "---" + recordMetadata.partition() + "---" + recordMetadata.offset()));
            //同步发送(阻塞方法，会等到结果返回再停止阻塞)
            send.get();
        }
        //4、关闭生产者
        producer.close();
    }
}
