package com.kafka.producer.example.service

import org.apache.kafka.clients.consumer.KafkaConsumer
import org.apache.kafka.clients.producer.ProducerConfig
import org.slf4j.LoggerFactory
import org.springframework.stereotype.Service
import java.util.*

@Service
class KafkaConsumerService {

    val log = LoggerFactory.getLogger(KafkaConsumerService::class.java)

    fun getMessages(broker: String, topic: String, time: Long): List<Any> {
        val consumer = KafkaConsumer<String, String>(Properties().apply {
            put(ProducerConfig.BOOTSTRAP_SERVERS_CONFIG, broker)
            put(ProducerConfig.KEY_SERIALIZER_CLASS_CONFIG, "org.apache.kafka.common.serialization.StringSerializer")
            put(ProducerConfig.VALUE_SERIALIZER_CLASS_CONFIG, "org.apache.kafka.common.serialization.StringSerializer")
        })
        consumer.subscribe(listOf(topic))

        val mas = mutableListOf<Any>()
        val records = consumer.poll(time)
        for (record in records) {
            mas.add("\"${record.key()}\": \"${record.value()}\"")
        }
        log.info(mas.toString())
        return mas
    }

}