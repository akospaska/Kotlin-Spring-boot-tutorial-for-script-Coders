package com.example.demo

import com.google.gson.Gson
import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication
import java.sql.*

@SpringBootApplication
class DemoApplication
var globalSqlConnection: Connection? = DriverManager.getConnection(
    "jdbc:mysql://localhost:3306/apitestapp?autoReconnect=true&useSSL=false", "root", "password"
)

var globalGson = Gson()

fun main(args: Array<String>) {
    runApplication<DemoApplication>(*args)
}
