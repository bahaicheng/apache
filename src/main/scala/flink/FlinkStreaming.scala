package flink

import org.apache.flink.api.java.utils.ParameterTool
import org.apache.flink.streaming.api.scala.StreamExecutionEnvironment
import org.apache.flink.streaming.connectors.kafka.FlinkKafkaConsumer09
import org.apache.flink.streaming.util.serialization.SimpleStringSchema

class FlinkStreaming {
  def main(args: Array[String]): Unit = {
    val params = ParameterTool.fromArgs(args)
    if (params.getNumberOfParameters < 4) {
      return
    }

    val prefix = params.get("prefix", "PREFIX")
    val env = StreamExecutionEnvironment.getExecutionEnvironment
    env.getConfig.disableSysoutLogging()
    env.enableCheckpointing(5000)
    env.getConfig.setGlobalJobParameters(params)

    val kafkaConsumer = new FlinkKafkaConsumer09[String](params.getRequired("input-topic"),
      new SimpleStringSchema,
      params.getProperties)

    val messageStream = env
      .addSource(kafkaConsumer)
      .map(in => prefix + in)

    val kafkaProducer = new FlinkKafkaConsumer09(
      params.getRequired("output-topic"),
      new SimpleStringSchema,
      params.getProperties)

    // write data into Kafka
    //     messageStream.addSink(kafkaProducer)

    env.execute("Kafka 0.10 Example")


  }
}
