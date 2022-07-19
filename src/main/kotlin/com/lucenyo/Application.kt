package com.lucenyo

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication

@SpringBootApplication(scanBasePackages = ["com.lucenyo"])
class Application

fun main(args: Array<String>) {
  runApplication<Application>(*args)
}
