package kafka;

import org.apache.kafka.clients.producer.KafkaProducer;
import org.apache.kafka.clients.producer.Producer;
import org.apache.kafka.clients.producer.ProducerRecord;

import java.util.Properties;

/**
 * Created by DELL on 2017/11/22.
 */
public class KafkaProducerUtil {
    public static void main(String[] args){
        Properties props = new Properties();
        props.put("bootstrap.servers", "bourne:9092");
        props.put("acks", "all");
        props.put("retries", 0);
        props.put("batch.size", 16384);
        props.put("linger.ms", 1);
        props.put("buffer.memory", 33554432);
        props.put("key.serializer", "org.apache.kafka.common.serialization.StringSerializer");
        props.put("value.serializer", "org.apache.kafka.common.serialization.StringSerializer");

        Producer<String, String> producer = new KafkaProducer<>(props);
        String line = "hadoop hadoop word hive hive spark spark hue hue kafka kafka hbase hbase word a a b b";
        for(int i = 0; i < 100; i++){
            producer.send(new ProducerRecord<String, String>("kafkatopic", line));
        }
        producer.close();
    }
}
