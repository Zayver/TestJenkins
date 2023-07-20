package com.testjenkins.jkk.controller

import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RequestParam
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/test")
class MainController {
    @GetMapping("/info")
    fun getInfo(): String {
        return "Version 1"
    }

    @GetMapping("/sum")
    fun sumSomething(@RequestParam num1: Int, @RequestParam num2: Int): String{
        return "Hola: ${(num1 + num2)}"
    }
}