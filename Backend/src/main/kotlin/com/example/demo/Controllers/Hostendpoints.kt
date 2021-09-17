package com.example.demo.Controllers

import com.google.gson.Gson
import org.springframework.web.bind.annotation.*
import java.sql.*
import java.util.*

data class sampleJSON(val name: String, val age: Int, val Content: String)
data class testJsonClass(val title: String, val value: Int)

data class finalTestResult(val id: String, val name: String)

@RestController
@RequestMapping("api/hello")
class Hostendpoints {
    var gson = Gson()

    @GetMapping("/springboot")

    fun helloWorld(): String {

        var sampleObj = sampleJSON("Ákos", 30, "Lófaszomat már")

        val json = gson.toJson(sampleObj)

        println("i am the json")
        println(json)

        println("Data fetched")
        return json
    }

    @PostMapping("/addsomething")

    fun writeSomething(@RequestBody obj: String): String {

        var conn: Connection? = DriverManager.getConnection(
            "jdbc:mysql://localhost:3306/apitestapp?autoReconnect=true&useSSL=false", "root", "password"
        )

// ////////////////
        var stmt: Statement? = null
        var resultset: ResultSet? = null

        val trialList: MutableList<finalTestResult> = ArrayList()

        try {
            stmt = conn!!.createStatement()
            resultset = stmt!!.executeQuery("select * from customer;")

            if (stmt.execute("select * from customer;")) {
                resultset = stmt.resultSet
            }

            while (resultset!!.next()) {

                val some: finalTestResult = finalTestResult(resultset.getString("id"), resultset.getString("name"))
                trialList.add(some)
            }
        } catch (ex: SQLException) {
            // handle any errors
            ex.printStackTrace()
        }
        // //////////////////////
        println(trialList)
        println(trialList[2])
        val sentData = gson.fromJson(obj, testJsonClass::class.java)
        println("Hello post endpoint")

        println(obj)
        println("This is the data what have been posted: ${sentData.title}")
        var sampleObj = sampleJSON("Ákos", 30, "Lófaszomat már")
        val json = gson.toJson(sampleObj)
        val json2 = gson.toJson(trialList)
        println(json)
        println(json2)
        return json
    }
}