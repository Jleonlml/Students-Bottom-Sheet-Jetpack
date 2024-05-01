package com.devartdgo.test2selccapp

import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.ModalBottomSheet
import androidx.compose.material3.Scaffold
import androidx.compose.material3.SheetState
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.rememberModalBottomSheetState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.devartdgo.test2selccapp.model.Student
import com.devartdgo.test2selccapp.ui.theme.Test2SElCCAppTheme
import com.devartdgo.test2selccapp.viewmodel.StudentViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            Test2SElCCAppTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    MainScreen()
                }
            }
        }
    }
}

@Composable
fun Greeting(name: String, modifier: Modifier = Modifier) {
    Text(
        text = "Hello $name!",
        modifier = modifier
    )
}

@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    Test2SElCCAppTheme {
        Greeting("Android")
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MainScreen() {
    val sheetState = rememberModalBottomSheetState()
    val viewModel = hiltViewModel<StudentViewModel>()
    val students = viewModel.students.collectAsState()

    Scaffold(
        bottomBar = {
            BottomActionSheet(
                viewModel = viewModel,
                sheetState = sheetState,
            )
        }
    ) { padding ->
        LazyColumn(
            modifier = Modifier.padding(padding)
        ) {
            items(students.value) { student ->
                StudentItem(
                    student = student,
                    onClick = {
                        viewModel.setSelectedStudent(student)
                        Log.d("Juan", student.toString())
                    }
                )
            }
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun BottomActionSheet(
    sheetState: SheetState,
    viewModel: StudentViewModel
) {
    val student = viewModel.selectedStudent.collectAsState()
    if (student.value != null) {
        ModalBottomSheet(
            onDismissRequest = {
                viewModel.setSelectedStudent(null)
            },
            sheetState = sheetState
        ) {
            Text(student.value!!.name)
            Spacer(modifier = Modifier.height(8.dp))
            Text("ID: ${student.value!!.id}")
            Spacer(modifier = Modifier.height(8.dp))
            Text("Current Year: ${student.value!!.currentYear}")
        }
    }
}

// StudentItem.kt
@Composable
fun StudentItem(
    student: Student,
    onClick: () -> Unit
) {
    Row(
        modifier = Modifier
            .clickable { onClick() }
            .padding(16.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Text(student.name)
    }
}