package com.example.demo.Controllers.getEndpoint
import com.google.gson.Gson
import org.springframework.web.bind.annotation.*
import java.sql.*
import java.util.*

data class inventoryListItem(val name: String, val id: Int, val count: Int)
@RestController
@CrossOrigin
@RequestMapping("api")
class GetInventoryList {
    @GetMapping("/getinventorylist")
    fun getList(): String {

        var conn2: Connection? = DriverManager.getConnection(
            "jdbc:mysql://localhost:3306/apitestapp?autoReconnect=true&useSSL=false", "root", "password"
        )
        var stmt2: Statement? = null
        var resultset2: ResultSet? = null

        val trialList2: MutableList<inventoryListItem> = ArrayList()

        try {
            stmt2 = conn2!!.createStatement()
            resultset2 = stmt2!!.executeQuery("select * from inventoryTutorial;")

            if (stmt2.execute("select * from inventoryTutorial;")) {
                resultset2 = stmt2.resultSet
            }

            while (resultset2!!.next()) {

                val some2: inventoryListItem = inventoryListItem(resultset2.getString("name"), resultset2.getInt("id"), resultset2.getInt("count"))
                trialList2.add(some2)
            }
        } catch (ex: SQLException) {
            // handle any errors
            ex.printStackTrace()
        }
        var gson = Gson()
        val json = gson.toJson(trialList2)
        println(json)
        return json
    }
}
