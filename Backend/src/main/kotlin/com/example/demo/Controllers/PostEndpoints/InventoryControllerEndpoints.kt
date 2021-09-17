package com.example.demo.Controllers.PostEndpoints

import com.example.demo.Controllers.getEndpoint.inventoryListItem
import com.google.gson.Gson
import org.springframework.web.bind.annotation.*
import java.sql.*
import java.util.*

data class modifyCount(val id: Int, val movement: String)
data class deleteAccount(val id: Int)
data class createNewItem(val name: String)

@RestController
@CrossOrigin
@RequestMapping("inventoryactions")
class InventoryControllerEndpoints {
    var gson = Gson()
    var conn: Connection? = DriverManager.getConnection(
        "jdbc:mysql://localhost:3306/apitestapp?autoReconnect=true&useSSL=false", "root", "password"
    )
    @PostMapping("/modifycount")

    fun modifyCountValue(@RequestBody requestBody: String): String {
        println(requestBody)
        val sentData = gson.fromJson(requestBody, modifyCount::class.java)
        println("Hello post endpoint")
        println(requestBody)
        println(sentData)

        var stmt: Statement? = null
        var resultset: Int? = null

        val trialList: MutableList<inventoryListItem> = ArrayList()
        try {
            stmt = conn!!.createStatement()
            resultset = stmt!!.executeUpdate("update inventoryTutorial set count =count${sentData.movement}1  where id =${sentData.id};")
        } catch (ex: SQLException) {
            // handle any errors
            ex.printStackTrace()
        }

        println(trialList)
        return "asd"
    }
    @PostMapping("/deleteitem")

    fun deleteItem(@RequestBody requestBody: String): String {

        val sentData = gson.fromJson(requestBody, deleteAccount::class.java)

        var stmt: Statement? = null
        var resultset: Int? = null

        val trialList: MutableList<inventoryListItem> = ArrayList()
        try {
            stmt = conn!!.createStatement()
            resultset = stmt!!.executeUpdate("delete from inventoryTutorial where id =${sentData.id};")
        } catch (ex: SQLException) {
            // handle any errors
            ex.printStackTrace()
        }

        println(trialList)
        return "asd"
    }

    @PostMapping("/createnewItem")

    fun createNewItem(@RequestBody requestBody: String): String {

        val sentData = gson.fromJson(requestBody, createNewItem::class.java)

        var stmt: Statement? = null
        var resultset: Int? = null

        val trialList: MutableList<inventoryListItem> = ArrayList()
        try {
            stmt = conn!!.createStatement()

            resultset = stmt!!.executeUpdate(" insert into inventoryTutorial (name) values(\"${sentData.name}\");")
        } catch (ex: SQLException) {
            // handle any errors
            ex.printStackTrace()
        }

        println(trialList)
        return "asd"
    }
}
