package geo.georgi.plugins

import geo.georgi.model.Priority
import geo.georgi.model.Task
import geo.georgi.model.TaskRepository
import io.ktor.http.*
import io.ktor.serialization.kotlinx.json.*
import io.ktor.server.application.*
import io.ktor.server.plugins.contentnegotiation.*
import io.ktor.server.request.*
import io.ktor.server.response.*
import io.ktor.server.routing.*

fun Application.configureSerialization(repository: TaskRepository) {
    install(ContentNegotiation) {
        json()
    }
    routing {
        route("/tasks"){
            get{
                val tasks = repository.allTasks()
                call.respond(tasks)
            }
            get("/byPriority/{priority}") {
                val priorityAsText = call.parameters["priority"]
                if(priorityAsText == null) {
                    call.respond(HttpStatusCode.BadRequest)
                    return@get
                }

                try{
                    val priority = Priority.valueOf(priorityAsText)
                    val tasks = repository.tasksByPriority(priority)
                    if(tasks.isEmpty()){
                        call.respond(HttpStatusCode.NotFound)
                        return@get
                    }
                    call.respond(tasks)
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
                val task = repository.taskByName(name)
                if(task == null){
                    call.respond(HttpStatusCode.NotFound)
                    return@get
                }
                call.respond(task)
            }
            post{
                try{
                    val task = call.receive<Task>()
                    repository.addTask(task)
                    call.respond(HttpStatusCode.NoContent)
                }catch(ex: IllegalArgumentException){
                    call.respond(HttpStatusCode.BadRequest)
                }catch(ex: IllegalStateException){
                    call.respond(HttpStatusCode.BadRequest)
                }
            }
            delete("/{taskName}"){
                val name = call.parameters["taskName"]
                if(name == null){
                    call.respond(HttpStatusCode.BadRequest)
                    return@delete
                }
                if(repository.removeTask(name)){
                    call.respond(HttpStatusCode.NoContent)
                }else{
                    call.respond(HttpStatusCode.NotFound)
                }
            }
        }
    }
}
