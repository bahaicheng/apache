package utils

import java.util.Properties

import org.apache.kafka.clients.producer.{KafkaProducer, ProducerRecord}

object ProducerKafka {
  def main(args: Array[String]): Unit = {
    val props: Properties = new Properties()
    props.put("bootstrap.servers", "bourne:9092")
    props.put("acks", "all")
    props.put("retries","0")
    props.put("batch.size", "16384")
    props.put("linger.ms", "1")
    props.put("buffer.memory", "33554432")
    props.put("key.serializer", "org.apache.kafka.common.serialization.StringSerializer")
    props.put("value.serializer", "org.apache.kafka.common.serialization.StringSerializer")

    val producer = new KafkaProducer[String,String](props)
    var  line = "hadoop hadoop word hive hive spark spark hue hue kafka kafka hbase hbase word a a b b";
    for(a <- 1 to 10){
      producer.send(new ProducerRecord[String, String]("kafkatopic", a+"="+line));
    }
    producer.close()
  }
}
