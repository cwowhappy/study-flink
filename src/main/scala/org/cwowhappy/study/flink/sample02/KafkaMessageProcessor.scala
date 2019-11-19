package org.cwowhappy.study.flink.sample02

import java.util.Properties

import org.apache.flink.api.common.serialization.SimpleStringSchema
import org.apache.flink.streaming.api.scala._
import org.apache.flink.streaming.connectors.kafka.FlinkKafkaConsumer

object KafkaMessageProcessor {
    def main(args: Array[String]): Unit = {
        val environment = StreamExecutionEnvironment.getExecutionEnvironment
        val properties = new Properties()
        properties.setProperty("api.version.request", "true")
        properties.setProperty("security.protocol", "SASL_PLAINTEXT")
        properties.setProperty("sasl.mechanism", "PLAIN")
        properties.setProperty("sasl.username", "account5")
        properties.setProperty("sasl.password", "zpuXRGuO")
        properties.setProperty("enable.auto.commit", "true")
        properties.setProperty("auto.offset.reset", "earliest")
        properties.setProperty("bootstrap.servers", "106.12.11.34:30091,106.12.35.226:30092,106.12.27.21:30093")
        properties.setProperty("group.id", "lixiaoyi-study-flink")
        properties.setProperty("sasl.jaas.config",
            "org.apache.kafka.common.security.plain.PlainLoginModule required username=\"account5\" password=\"zpuXRGuO\";");

        val topic = "NL_trafficlite_online_recognition_result"
        val kafkaConsumer: FlinkKafkaConsumer[String] = new FlinkKafkaConsumer[String](topic, new SimpleStringSchema(), properties)

        environment.addSource(kafkaConsumer).uid(topic).name(topic).setParallelism(6).print()

        environment.execute("study-flink-kafka-consumer")
    }
}
