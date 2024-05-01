package com.devartdgo.test2selccapp.repository

import com.devartdgo.test2selccapp.model.Student
import com.devartdgo.test2selccapp.network.CacheProvider
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import javax.inject.Inject

class StudentRepository(private val cacheProvider: CacheProvider) {
    suspend fun getStudents(): List<Student> {
        return withContext(Dispatchers.IO) {
            cacheProvider.getStudents()
        }
    }
}