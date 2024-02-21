package com.task.todo.data.repositoryImpl

import com.task.todo.data.local.dao.TaskHistoryDao
import com.task.todo.data.local.entity.TaskEntity
import com.task.todo.data.remote.dto.Todo
import com.task.todo.domain.repository.TodoRepositoryLocal
import javax.inject.Inject

class TodoRepoLocalImpl @Inject constructor(
    private val dao : TaskHistoryDao
): TodoRepositoryLocal {
    override suspend fun addTodoListLocal(todo: List<Todo>) {
        dao.addTasks(todo.map { it.toTaskEntity() })
    }

    override suspend fun getTodoListFromLocal(): List<Todo> {
        return dao.getAllTasks().map { it.toTodo() }
    }

    override suspend fun addTodo(todo: Todo) {
        dao.addTask(todo.toTaskEntity())
    }

    override suspend fun updateTodo(todo: Todo) {
        dao.updateTask(todo.toTaskEntity())
    }

    override suspend fun deleteTodo(todo: Todo) {
       dao.deleteTask(todo.toTaskEntity())
    }

    private fun Todo.toTaskEntity(): TaskEntity =
        TaskEntity(
            id = id,
            completed = completed,
            userID = userId,
            todo = todo
        )

    private fun TaskEntity.toTodo(): Todo =
        Todo(
            id = id,
            completed = completed,
            todo = todo,
            userId = userID
        )
}