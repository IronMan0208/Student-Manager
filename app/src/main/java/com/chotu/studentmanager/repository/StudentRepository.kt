package com.chotu.studentmanager.repository

import com.chotu.studentmanager.data.dao.StudentDao
import com.chotu.studentmanager.data.entity.StudentEntity
import kotlinx.coroutines.flow.Flow

class StudentRepository(
    private val studentDao: StudentDao
) {
    suspend fun insertStudent(
        student: StudentEntity
    ) {
        studentDao.insertStudent(student)
    }

    suspend fun deleteStudent(student: StudentEntity) {
        studentDao.deleteStudent(student)
    }

    suspend fun updateStudent(student: StudentEntity) {
        studentDao.updateStudent(student)
    }

    fun getAllStudents(): Flow<List<StudentEntity>> {
        return studentDao.getAllStudents()
    }
}