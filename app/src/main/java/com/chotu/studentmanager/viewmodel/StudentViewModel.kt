package com.chotu.studentmanager.viewmodel

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.chotu.studentmanager.data.entity.StudentEntity
import com.chotu.studentmanager.repository.StudentRepository
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.WhileSubscribed
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch

class StudentViewModel(
    private val repository: StudentRepository
) : ViewModel() {

    val students: StateFlow<List<StudentEntity>> =
        repository.getAllStudents()
            .stateIn(
                scope = viewModelScope,
                started = SharingStarted.WhileSubscribed(5000),
                initialValue = emptyList()
            )

    var selectedStudent by mutableStateOf<StudentEntity?>(null)
        private set


    fun insertStudent(
        name: String,
        course: String,
        semester: Int
    ) {
        viewModelScope.launch {
            repository.insertStudent(
                StudentEntity(
                    name = name,
                    course = course,
                    semester = semester
                )
            )

        }
    }

    fun deleteStudent(
        student: StudentEntity
    ) {
        viewModelScope.launch {
            repository.deleteStudent(student)
        }
    }

    fun selectedStudent(student: StudentEntity?) {
        selectedStudent = student
    }

    fun updateStudent(
        student: StudentEntity
    ) {
        viewModelScope.launch {
            repository.updateStudent(student)
            selectedStudent = null
        }
    }
}