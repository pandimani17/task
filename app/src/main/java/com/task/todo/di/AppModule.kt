package com.task.todo.di

import android.content.Context
import androidx.room.Room
import com.task.todo.common.NetworkUtils
import com.task.todo.common.Utilities
import com.task.todo.data.local.TODOAppDatabase
import com.task.todo.data.local.dao.TaskHistoryDao
import com.task.todo.data.remote.ApiService
import com.task.todo.data.repositoryImpl.TodoRepoImpl
import com.task.todo.data.repositoryImpl.TodoRepoLocalImpl
import com.task.todo.domain.repository.TodoRepository
import com.task.todo.domain.repository.TodoRepositoryLocal
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttpClient
import okhttp3.Request
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton


@InstallIn(SingletonComponent::class)
@Module
object AppModule {


    @Provides
    @Singleton
    fun provideApiService(): ApiService {
        val httpClient =
            OkHttpClient.Builder().addInterceptor(interceptor)
        httpClient.addInterceptor { chain ->
            val request: Request =
                chain.request().newBuilder()
                    .build()
            chain.proceed(request)
        }
        return Retrofit.Builder()
            .baseUrl("https://dummyjson.com")
            .addConverterFactory(GsonConverterFactory.create())
            .client(httpClient.build())
            .build()
            .create(ApiService::class.java)

    }

    @Provides
    @Singleton
    fun provideRoomDataBase(@ApplicationContext appContext: Context): TODOAppDatabase {
        return Room.databaseBuilder(
            appContext,
            TODOAppDatabase::class.java,
            "com.task.todo"
        ).build()
    }

    @Provides
    @Singleton
    fun provideTaskHistoryDao(database: TODOAppDatabase): TaskHistoryDao {
        return database.taskHistoryDao()
    }

    @Provides
    @Singleton
    fun providesTodoRepository(
        api: ApiService
    ): TodoRepository {
        return TodoRepoImpl(api)
    }

    @Provides
    @Singleton
    fun providesTodoLocalRepository(dao: TaskHistoryDao): TodoRepositoryLocal {
        return TodoRepoLocalImpl(dao)
    }
    @Provides
    fun provideNetworkUtils(): NetworkUtils {
        return Utilities
    }

}


private val interceptor = run {
    val httpLoggingInterceptor = HttpLoggingInterceptor()
    httpLoggingInterceptor.apply {
        httpLoggingInterceptor.level = HttpLoggingInterceptor.Level.BODY
        httpLoggingInterceptor.level = HttpLoggingInterceptor.Level.HEADERS

    }
}