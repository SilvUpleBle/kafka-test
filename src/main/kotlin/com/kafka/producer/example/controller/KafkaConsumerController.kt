package com.kafka.producer.example.controller

import com.kafka.producer.example.service.KafkaConsumerService
import io.swagger.v3.oas.annotations.Operation
import io.swagger.v3.oas.annotations.Parameter
import io.swagger.v3.oas.annotations.tags.Tag
import lombok.RequiredArgsConstructor
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("/api/v1/consumer")
@RequiredArgsConstructor
@Tag(name = "Контроллер для получения сообщений", description = "Позволяет получать сообщения из топика")
class KafkaConsumerController(private val kafkaConsumerService: KafkaConsumerService) {

    @GetMapping("/get/{broker}/{topic}")
    @Operation(
        summary = "Получение сообщения",
        description = "Позволяет получить сообщение из топика",
    )
    fun getMessages(@PathVariable @Parameter(description = "Хост брокера", example = "localhost:9092") broker: String,
                    @PathVariable @Parameter(description = "Название топика", example = "test-topic") topic: String,
                    @RequestParam @Parameter(description = "За какое время должны быть получены сообщения, в мс", example = "10000") time: Long): ResponseEntity<Any> {

        return ResponseEntity.ok(kafkaConsumerService.getMessages(broker, topic, time))
    }

}