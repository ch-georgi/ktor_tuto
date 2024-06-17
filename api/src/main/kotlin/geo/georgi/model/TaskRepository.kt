package geo.georgi.model

import geo.georgi.model.Priority
import geo.georgi.model.Task

interface TaskRepository  {
    suspend fun allTasks(): List<Task>
    suspend fun tasksByPriority(priority: Priority): List<Task>
    suspend fun taskByName(name: String): Task?
    suspend fun addTask(task: Task)
    suspend fun removeTask(name: String): Boolean
}

/*val tasks = mutableListOf(
    Task("cleaning","Clean the house", Priority.Low),
    Task("Prueba","Desc de prueba", Priority.High)
)*/
/*object TaskRepository {
    private val tasks = mutableListOf(
        Task("cleaning", "Clean the house", Priority.Low),
        Task("gardening", "Mow the lawn", Priority.Medium),
        Task("shopping", "Buy the groceries", Priority.High),
        Task("painting", "Paint the fence", Priority.Medium)
    )

    fun allTasks(): List<Task> = tasks

    fun tasksByPriority(priority: Priority) = tasks.filter {
        it.priority == priority
    }

    fun taskByName(name: String) = tasks.find {
        it.name.equals(name, ignoreCase = true)
    }

    fun addTask(task: Task) {
        if(taskByName(task.name) != null) {
            throw IllegalArgumentException(
                "Task with name ${task.name} already exists"
            )
        }
        tasks.add(task)
    }

}*/