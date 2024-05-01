package com.devartdgo.test2selccapp.network

import com.devartdgo.test2selccapp.model.Student

class FileSystemCacheProvider: CacheProvider {
    override suspend fun getStudents(): List<Student> {
        return listOf(
            Student(
                name = "student1",
                id = "1",
                currentYear = 1
            ),
            Student(
                name = "student2",
                id = "2",
                currentYear = 2
            ),
            Student(
                name = "student3",
                id = "3",
                currentYear = 3
            ),
        )
    }
}