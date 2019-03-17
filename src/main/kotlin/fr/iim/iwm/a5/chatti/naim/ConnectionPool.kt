package fr.iim.iwm.a5.chatti.naim

import java.sql.Connection
import java.sql.DriverManager
import java.util.concurrent.ConcurrentLinkedQueue

class ConnectionPool(val url: String, val user: String?, val password: String?) {

    var list = ConcurrentLinkedQueue<Connection>()

    fun getConnection() =
        list.poll()
            ?: DriverManager.getConnection(url, user, password)


    fun makeAvailable(connection: Connection) {
        println("make available")
        list.add(connection)
    }

    inline fun use(block: (Connection) -> Unit) {
        val connection = getConnection()

        try {
            block(connection)
        } finally {
            makeAvailable(connection)
        }
    }
}