package kafka;

import com.google.common.collect.Lists;
import org.apache.kafka.clients.consumer.Consumer;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.apache.kafka.clients.consumer.ConsumerRecords;
import org.apache.kafka.clients.consumer.KafkaConsumer;

import java.util.Properties;

/**
 * Created by DELL on 2017/11/22.
 */
public class KafkaConsumerUtil {
    public static void main(String[] args){
        Properties props = new Properties();
        props.put("bootstrap.servers", "bourne:9092");
        props.put("key.deserializer", "org.apache.kafka.common.serialization.StringDeserializer");
        props.put("value.deserializer", "org.apache.kafka.common.serialization.StringDeserializer");
        props.setProperty("group.id", "3");
        props.setProperty("enable.auto.commit", "true");
        props.setProperty("auto.offset.reset", "earliest");

        Consumer<String, String> consumer = new KafkaConsumer<String, String>(props);
        consumer.subscribe(Lists.newArrayList("kafkatopic"));

        for (int i = 0; i < 2; i++) {
            ConsumerRecords<String, String> records = consumer.poll(1000);
            System.out.println(records.count());
            for (ConsumerRecord<String, String> record : records) {
                System.out.println(record);
                //consumer.seekToBeginning(new TopicPartition(record.topic(), record.partition()));
            }
        }
    }
}
