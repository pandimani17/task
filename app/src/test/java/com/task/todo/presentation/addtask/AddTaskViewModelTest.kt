package com.task.todo.presentation.addtask

import android.content.Context
import android.net.ConnectivityManager
import android.net.Network
import android.net.NetworkCapabilities
import com.task.todo.common.NetworkUtils
import com.task.todo.common.Utilities
import com.task.todo.domain.use_case.AddTodoLocalUseCase
import com.task.todo.domain.use_case.PostTodoUseCase
import com.task.todo.domain.use_case.ValidateDescriptionUSeCase
import com.task.todo.presentation.addtask.events.AddTaskEvent
import io.mockk.every
import io.mockk.mockk
import io.mockk.mockkStatic
import kotlinx.coroutines.test.runTest
import org.junit.Assert.assertFalse
import org.junit.Assert.assertTrue
import org.junit.Before
import org.junit.Test
import org.mockito.Mockito
import org.mockito.Mockito.mockStatic
import org.mockito.Mockito.`when`
import org.mockito.kotlin.any
import org.mockito.kotlin.mock

class AddTaskViewModelTest{

private lateinit var  addTaskViewModel : AddTaskViewModel
    private val postTodoUseCase: PostTodoUseCase = mock()
    private val addTodoLocalUseCase: AddTodoLocalUseCase= mock()
    private val validateDescription: ValidateDescriptionUSeCase= mock()
    private val mockNetworkUtils: NetworkUtils =mock()



    @Before
    fun setUp(){
        addTaskViewModel = AddTaskViewModel(postTodoUseCase,addTodoLocalUseCase,validateDescription,mockNetworkUtils)
//        val context = mockk<Context>()
//
//        val connectivityManager = mockk<ConnectivityManager>()
//        every { context.getSystemService(Context.CONNECTIVITY_SERVICE) } returns connectivityManager
//
//        val network = mockk<Network>()
//        val networkCapabilities = mockk<NetworkCapabilities>()
//
//        every { connectivityManager.activeNetwork } returns network
//        every { connectivityManager.getNetworkCapabilities(network) } returns networkCapabilities
//
//        every { networkCapabilities.hasTransport(NetworkCapabilities.TRANSPORT_WIFI) } returns true
    }



    @Test
    fun test_submitTodo()= runTest {
            val mockNetworkUtils : NetworkUtils = mock()
           `when` (mockNetworkUtils.isInternetAvailable(any())).thenReturn (true)
            val event = AddTaskEvent.TodoSubmitted(mock())
            addTaskViewModel.onEvent(event)
            assertTrue(addTaskViewModel.internetNotAvailable.value)

    }

}