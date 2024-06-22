package com.example.portfolio

import org.springframework.web.bind.annotation.CrossOrigin
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RestController

@RestController
class PortfolioApiController {
    @CrossOrigin(origins = ["http://localhost:8888"])
    @GetMapping("/asdf")
    fun helloWorld(): String {
        return "Hello World"
    }
}