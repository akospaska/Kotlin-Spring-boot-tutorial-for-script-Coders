package com.example.demo.Controllers.asynctest

import com.example.demo.Controllers.finalTestResult
import com.example.demo.Controllers.getEndpoint.inventoryListItem
import com.example.demo.globalGson
import com.example.demo.globalSqlConnection
import org.springframework.web.bind.annotation.*
import java.sql.*
import java.util.*

@RestController
@CrossOrigin
@RequestMapping("api/asyncTest")

class asynccall {
    @PostMapping("/testpost")
    fun asynccallFn(@RequestBody requestBody: String): String? {

        var stmt: Statement? = null
        var resultset: ResultSet? = null
        val trialList: MutableList<inventoryListItem> = ArrayList()
        try {
            stmt = globalSqlConnection!!.createStatement()
            resultset = stmt!!.executeQuery("select * from inventoryTutorial;")

            if (stmt.execute("select * from inventoryTutorial;")) {
                resultset = stmt.resultSet
            }

            while (resultset!!.next()) {

                val some: inventoryListItem = inventoryListItem(resultset.getString("name"), resultset.getInt("id"), resultset.getInt("count"))
                trialList.add(some)
            }
        } catch (ex: SQLException) {
            // handle any errors
            ex.printStackTrace()
        }
        val json = globalGson.toJson(trialList)
        return json
    }
}


