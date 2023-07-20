package com.testjenkins.jkk

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication

@SpringBootApplication
class JkkApplication

fun main(args: Array<String>) {
	runApplication<JkkApplication>(*args)
}
