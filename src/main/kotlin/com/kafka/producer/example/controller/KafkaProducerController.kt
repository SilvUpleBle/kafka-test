package com.kafka.producer.example.controller

import com.kafka.producer.example.service.KafkaProducerService
import io.swagger.v3.oas.annotations.Operation
import io.swagger.v3.oas.annotations.Parameter
import io.swagger.v3.oas.annotations.tags.Tag
import lombok.RequiredArgsConstructor
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("/api/v1/producer")
@RequiredArgsConstructor
@Tag(name = "Контроллер для отправки сообщений", description = "Позволяет отправлять сообщения в топик")
class KafkaProducerController(private val kafkaProducerService: KafkaProducerService) {

    @PostMapping("/send/{broker}/{topic}")
    @Operation(
        summary = "Отправка сообщения",
        description = "Позволяет отправить сообщение в топик",
    )
    fun sendMessage(@PathVariable @Parameter(description = "Хост брокера", example = "localhost:9092") broker: String,
                    @PathVariable @Parameter(description = "Название топика", example = "test-topic") topic: String,
                    @RequestParam @Parameter(description = "Сообщение", example = "Привет, мир!") message: String): ResponseEntity<Any> {
        kafkaProducerService.sendMessage(broker, topic, message)
        return ResponseEntity.ok("Сообщение успешно отправлено")
    }

}