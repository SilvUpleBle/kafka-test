package com.kafka.producer.example.service

import lombok.extern.slf4j.Slf4j
import org.apache.kafka.clients.admin.AdminClient
import org.apache.kafka.clients.admin.NewTopic
import org.apache.kafka.clients.producer.ProducerConfig
import org.slf4j.LoggerFactory
import org.springframework.stereotype.Service
import java.util.*

@Service
@Slf4j
class KafkaTopicService {
    val log = LoggerFactory.getLogger(KafkaTopicService::class.java)

    fun createTopic(brokers: String, topic: String): Boolean {
        val adminClient = AdminClient.create(Properties().apply {
            put(ProducerConfig.BOOTSTRAP_SERVERS_CONFIG, brokers)
        })

        if (adminClient.listTopics().names().get().contains(topic)) {
            log.warn("Топик $topic уже существует")
            return false
        }

        val newTopic = NewTopic(topic, 1, 1)
        adminClient.createTopics(Collections.singletonList(newTopic)).all().get()
        log.info("Топик $topic успешно создан")
        return true
    }

    fun deleteAllTopics(brokers: String) {
        val adminClient = AdminClient.create(Properties().apply {
            put(ProducerConfig.BOOTSTRAP_SERVERS_CONFIG, brokers)
        })

        adminClient.deleteTopics(adminClient.listTopics().names().get()).all().get()

        log.info("Все топики успешно удалены")
    }

    fun deleteTopic(brokers: String, topic: String) {
        val adminClient = AdminClient.create(Properties().apply {
            put(ProducerConfig.BOOTSTRAP_SERVERS_CONFIG, brokers)
        })

        adminClient.deleteTopics(listOf(topic)).all().get()

        log.info("Топик $topic успешно удален")
    }

}