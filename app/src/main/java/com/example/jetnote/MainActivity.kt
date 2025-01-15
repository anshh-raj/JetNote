package com.example.jetnote

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import com.example.jetnote.navigation.NoteNavigation
import com.example.jetnote.ui.theme.JetNoteTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            MyApp {
//                val noteViewModel: NoteViewModel by viewModels()
                NoteNavigation()
            }
        }
    }
}

@Composable
fun MyApp(content: @Composable () -> Unit){
    JetNoteTheme {
        content()
    }
}

//@Composable
//fun NotesApp(noteViewModel: NoteViewModel = viewModel()){
//
////    val notes = remember {
////        mutableStateListOf<Note>()
////    }
//
//    val notesList = noteViewModel.getAllNotes()
//    NoteScreen(
//        notes = notesList,
//        onAddNote = {
//            noteViewModel.addNote(it)
//        },
//        onRemoveNote = {
//            noteViewModel.removeNote(it)
//        }
//    )
//}


@Preview(showBackground = true)
@Composable
fun GreetingPreview() {

}