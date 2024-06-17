package geo.georgi.plugins

import geo.georgi.model.*
import io.ktor.http.*
import io.ktor.server.application.*
import io.ktor.server.http.content.*
import io.ktor.server.plugins.statuspages.*
import io.ktor.server.response.*
import io.ktor.server.routing.*
import io.ktor.http.ContentType
import io.ktor.server.http.content.staticResources
import io.ktor.server.request.*

fun Application.configureRouting() {
    install(StatusPages) {
        exception<Throwable> { call, cause ->
            call.respondText(text = "500: $cause", status = HttpStatusCode.InternalServerError)
        }
    }
    routing {
        staticResources("/task-ui","task-ui")
        /*
        route("/tasks"){
            get("/test"){
                call.respond(
                    listOf(
                        Task("tarea1","Descripcion 1", Priority.Low),
                        Task("tarea2","Descripcion 2", Priority.Medium),
                        Task("asdq","test", Priority.Vital),
                    )
                )
            }
            get{
                val tasks = TaskRepository.allTasks()
                call.respondText(
                    contentType = ContentType.parse("text/html"),
                    text = tasks.tasksAsTable()
                )
            }
            get("/byPriority/{priority}") {
                val priorityAsText = call.parameters["priority"]
                if(priorityAsText == null) {
                    call.respond(HttpStatusCode.BadRequest)
                    return@get
                }

                try{
                    val priority = Priority.valueOf(priorityAsText)
                    val tasks = TaskRepository.tasksByPriority(priority)
                    if(tasks.isEmpty()){
                        call.respond(HttpStatusCode.NotFound)
                        return@get
                    }

                    call.respondText(
                        contentType = ContentType.parse("text/html"),
                        text = tasks.tasksAsTable()
                    )
                }catch(ex: IllegalArgumentException){
                    call.respond(HttpStatusCode.BadRequest)
                }
            }
            get("/byName/{taskName}"){
                val name = call.parameters["taskName"]
                if(name == null){
                    call.respond(HttpStatusCode.BadRequest)
                    return@get
                }
                val task = TaskRepository.taskByName(name)
                if(task == null){
                    call.respond(HttpStatusCode.NotFound)
                    return@get
                }
                call.respondText(
                    contentType = ContentType.parse("text/html"),
                    text = listOf(task).tasksAsTable()
                )
            }
            post{
                val formContent = call.receiveParameters()
                val params = Triple(
                    formContent["name"] ?: "",
                    formContent["description"] ?: "",
                    formContent["priority"] ?: ""
                )
                if(params.toList().any { it.isEmpty()}) {
                    call.respond(HttpStatusCode.BadRequest)
                    return@post
                }
                try{
                    val priority = Priority.valueOf(params.third)
                    TaskRepository.addTask(
                        Task(
                            params.first,
                            params.second,
                            priority
                        )
                    )
                    call.respond(HttpStatusCode.NoContent)
                }catch(ex: IllegalArgumentException){
                    call.respond(HttpStatusCode.BadRequest)
                }catch(ex: IllegalStateException){
                    call.respond(HttpStatusCode.BadRequest)
                }
            }
        }
        */
    }

}
