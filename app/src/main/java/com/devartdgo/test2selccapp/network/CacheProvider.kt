package com.devartdgo.test2selccapp.network

import com.devartdgo.test2selccapp.model.Student
import javax.inject.Singleton

@Singleton
interface CacheProvider {
    suspend fun getStudents(): List<Student>
}