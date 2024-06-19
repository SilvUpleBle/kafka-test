package com.kafka.producer.example

import org.apache.kafka.clients.admin.AdminClient
import org.apache.kafka.clients.admin.NewTopic
import org.apache.kafka.clients.producer.KafkaProducer
import org.apache.kafka.clients.producer.ProducerConfig
import org.apache.kafka.clients.producer.ProducerRecord
import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication
import java.util.*

@SpringBootApplication
class ExampleApplication

val broker: String = "localhost:9092"
val topic: String = "test-topic"

fun main(args: Array<String>) {
    runApplication<ExampleApplication>(*args)
}