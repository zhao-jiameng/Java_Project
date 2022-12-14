package kafka;

import org.apache.kafka.clients.consumer.*;
import org.apache.kafka.common.TopicPartition;
import org.apache.kafka.common.protocol.types.Field;

import java.time.Duration;
import java.util.Arrays;
import java.util.Iterator;
import java.util.Map;
import java.util.Properties;

public class ConsumerDemo {
    public static void main(String[] args) {
        //1、创建消费者的客户端
            //配置消费者
        Properties properties = new Properties();
            //指定key的反序列化
        properties.setProperty("key.deserializer", "org.apache.kafka.common.serialization.StringDeserializer");
            //指定value的反序列化
        properties.setProperty("value.deserializer", "org.apache.kafka.common.serialization.StringDeserializer");
            //指定kafka集群，通过节点发现集群
        properties.setProperty("bootstrap.servers", "hadoop101:9092,hadoop102:9092");
            //指定消费者组
        properties.setProperty("group.id", "last_group");
            //指定那个位置开始消费    latest：最新 earliest：最开始  只在统一消费者组第一次消费起作用
        properties.setProperty("auto.offset.reset", "latest");
            //是否自定提交offset
        properties.setProperty("enable.auto.commit", "true");
        KafkaConsumer<String, String> consumer = new KafkaConsumer<>(properties);
        //2、指定消费的topic
        while (true){
            //指定消费的topic
            consumer.subscribe(Arrays.asList("last"));
            //3、消费
                //Duration.ofSeconds(5):当消费者从kafka拉取数据，发现没有拉取到的时候，可以停留5s再去拉
            ConsumerRecords<String, String> poll = consumer.poll(Duration.ofSeconds(5));
            for (ConsumerRecord<String, String> r : poll) {
                System.out.println(r.topic() + "---" + r.partition() + "---" + r.offset() + "---" + r.value());
            }
        //手动提交offset
            //  consumer.commitAsync();     异步
            consumer.commitAsync(new OffsetCommitCallback() {
                //提交成功调用
                @Override
                public void onComplete(Map<TopicPartition, OffsetAndMetadata> map, Exception e) {
                    System.out.println(map);
                }
            });
            //  consumer.commitSync();      同步
        }

    }
}
