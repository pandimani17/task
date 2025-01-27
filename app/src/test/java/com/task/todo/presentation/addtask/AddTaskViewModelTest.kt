package com.task.todo.presentation.addtask

import com.task.todo.common.NetworkUtils
import com.task.todo.common.Resource
import com.task.todo.data.remote.dto.Todo
import com.task.todo.domain.use_case.AddTodoLocalUseCase
import com.task.todo.domain.use_case.PostTodoUseCase
import com.task.todo.domain.use_case.ValidateDescriptionUSeCase
import com.task.todo.domain.use_case.ValidationResult
import com.task.todo.presentation.addtask.events.AddTaskEvent
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.test.TestCoroutineDispatcher
import kotlinx.coroutines.test.resetMain

import kotlinx.coroutines.test.runTest
import kotlinx.coroutines.test.setMain
import org.junit.After
import org.junit.Assert.assertFalse

import org.junit.Assert.assertTrue
import org.junit.Before
import org.junit.Test

import org.mockito.Mockito.`when`
import org.mockito.kotlin.any
import org.mockito.kotlin.mock

class AddTaskViewModelTest{

private lateinit var  addTaskViewModel : AddTaskViewModel
    private val postTodoUseCase: PostTodoUseCase = mock()
    private val addTodoLocalUseCase: AddTodoLocalUseCase= mock()
    private val validateDescription: ValidateDescriptionUSeCase= mock()
    private val mockNetworkUtils: NetworkUtils =mock()
    private val testDispatcher = TestCoroutineDispatcher() // Create a Test Coroutine Dispatcher




    @OptIn(ExperimentalCoroutinesApi::class)
    @Before
    fun setUp(){
        Dispatchers.setMain(testDispatcher) // Set the main dispatcher to the test dispatcher
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
    @OptIn(ExperimentalCoroutinesApi::class)
    @After
    fun tearDown() {
        Dispatchers.resetMain() // Reset the main dispatcher after the test
        testDispatcher.cleanupTestCoroutines() // Clean up the test dispatcher
    }



    @Test
    fun test_checkInternetAvailability()= runTest {
        `when` (mockNetworkUtils.isInternetAvailable(any())).thenReturn (true)
        val validationResult : ValidationResult = mock()
        `when`(validateDescription.invoke(any())).thenReturn(validationResult)
        `when`(validationResult.successful).thenReturn(true)
        `when`(postTodoUseCase.invoke(any())).thenReturn(flow { emit(Resource.Success(Todo(completed = true, id = 0, todo = "dsfsd", userId = 1))) })
        `when`(addTodoLocalUseCase.invoke(any())).thenReturn(flow { emit(Resource.Success(Unit)) })

        val event = AddTaskEvent.TodoSubmitted(mock())
            addTaskViewModel.onEvent(event)
            assertFalse(addTaskViewModel.internetNotAvailable.value)

    }

}