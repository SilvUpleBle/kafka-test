package com.kafka.producer.example.service

import org.apache.kafka.clients.producer.KafkaProducer
import org.apache.kafka.clients.producer.ProducerConfig
import org.apache.kafka.clients.producer.ProducerRecord
import org.slf4j.LoggerFactory
import org.springframework.stereotype.Service
import java.util.*

@Service
class KafkaProducerService {

    val log = LoggerFactory.getLogger(KafkaProducerService::class.java)

    fun sendMessage(broker: String, topic: String, message: String) {
        val producer = KafkaProducer<String, String>(Properties().apply {
            put(ProducerConfig.BOOTSTRAP_SERVERS_CONFIG, broker)
            put(ProducerConfig.KEY_SERIALIZER_CLASS_CONFIG, "org.apache.kafka.common.serialization.StringSerializer")
            put(ProducerConfig.VALUE_SERIALIZER_CLASS_CONFIG, "org.apache.kafka.common.serialization.StringSerializer")
        })
        val record = ProducerRecord<String, String>(topic, message)
        producer.send(record).get()

        log.info("Сообщение успешно отправлено")
    }

}