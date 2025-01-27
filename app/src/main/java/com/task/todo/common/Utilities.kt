package com.task.todo.common

import android.content.Context
import android.net.ConnectivityManager
import android.net.NetworkCapabilities
import com.task.todo.data.remote.dto.Todo
import com.task.todo.domain.model.TodoParcel

object Utilities : NetworkUtils {

    fun toParcelable(todo: Todo): TodoParcel {
        val myParcelableObject = TodoParcel(
            completed = todo.completed,
            id = todo.id,
            todo = todo.todo,
            userId = todo.userId
        )
        return myParcelableObject

    }
    fun fromParcelable(todoParcel: TodoParcel?): Todo {
        if (todoParcel != null) {
            return Todo(
                completed = todoParcel.completed,
                id = todoParcel.id,
                todo = todoParcel.todo.toString(),
                userId = todoParcel.userId
            )
        }
        return Todo(completed = false,id=0, todo = "", userId = 0)
    }
    override fun isInternetAvailable(context: Context?): Boolean {
        val connectivityManager =
            context?.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
        val nw = connectivityManager.activeNetwork ?: return false
        val actNw = connectivityManager.getNetworkCapabilities(nw) ?: return false
        return when {
            actNw.hasTransport(NetworkCapabilities.TRANSPORT_WIFI) -> true
            actNw.hasTransport(NetworkCapabilities.TRANSPORT_CELLULAR) -> true
            actNw.hasTransport(NetworkCapabilities.TRANSPORT_ETHERNET) -> true
            actNw.hasCapability(NetworkCapabilities.NET_CAPABILITY_INTERNET) -> true
            actNw.hasCapability(NetworkCapabilities.NET_CAPABILITY_VALIDATED) -> true
            actNw.hasTransport(NetworkCapabilities.TRANSPORT_BLUETOOTH) -> true
            else -> false
        }
    }

}
interface NetworkUtils {
    fun isInternetAvailable(context: Context?): Boolean
}