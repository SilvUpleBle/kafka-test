package com.kafka.producer.example.controller

import com.kafka.producer.example.service.KafkaTopicService
import io.swagger.v3.oas.annotations.Operation
import io.swagger.v3.oas.annotations.Parameter
import io.swagger.v3.oas.annotations.tags.Tag
import lombok.RequiredArgsConstructor
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("/api/v1/topic")
@RequiredArgsConstructor
@Tag(name = "Контроллер для работы с топиками", description = "Позволяет удалять и создавать топики")
class KafkaTopicController(private val kafkaTopicService: KafkaTopicService) {

    @PostMapping("/create/{broker}/{topic}")
    @Operation(
        summary = "Создание топика",
        description = "Позволяет создать топик",
    )
    fun createTopic(@PathVariable(required = true) @Parameter(description = "Хост брокера", example = "localhost:9092") broker: String,
                    @PathVariable(required = true) @Parameter(description = "Название топика", example = "test-topic") topic: String): ResponseEntity<Any> {
        return if (kafkaTopicService.createTopic(broker, topic))
            ResponseEntity.ok("Топик $topic успешно создан")
        else
            ResponseEntity.status(404).body("Топик $topic уже существует")
    }

    @DeleteMapping("/delete/{broker}/{topic}")
    @Operation(
        summary = "Удаление топика",
        description = "Позволяет удалить топик по его имени",
    )
    fun deleteTopic(@PathVariable(required = true) @Parameter(description = "Хост брокера", example = "localhost:9092") broker: String,
                    @PathVariable(required = true) @Parameter(description = "Название топика", example = "test-topic") topic: String): ResponseEntity<Any> {
        kafkaTopicService.deleteTopic(broker, topic)
        return ResponseEntity.ok("Топик $topic успешно удален")
    }

    @DeleteMapping("/deleteAll/{broker}")
    @Operation(
        summary = "Удаление всех топиков",
        description = "Позволяет удалить все топики",
    )
    fun deleteAllTopics(@PathVariable(required = true) @Parameter(description = "Хост брокера", example = "localhost:9092") broker: String): ResponseEntity<Any> {
        kafkaTopicService.deleteAllTopics(broker)
        return ResponseEntity.ok("Все топики успешно удалены")
    }

}