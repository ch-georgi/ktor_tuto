package geo.georgi.plugins

import io.ktor.http.*
import io.ktor.server.application.*
import io.ktor.server.request.*
import io.ktor.server.response.*
import io.ktor.server.routing.*
import org.jetbrains.exposed.sql.*

fun Application.configureDatabases() {
    Database.connect(
        url = "jdbc:mysql://localhost:3306/ktor_tutorial_db",
        driver = "com.mysql.cj.jdbc.Driver",
        user = "root",
        password = ""
    )
}
