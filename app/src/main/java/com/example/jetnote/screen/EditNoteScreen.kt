package com.example.jetnote.screen

import android.widget.Toast
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import com.example.jetnote.components.NoteButton
import com.example.jetnote.components.NoteInputText
import com.example.jetnote.model.Note
import java.util.UUID

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun EditNoteScreen(
    noteViewModel: NoteViewModel = viewModel(),
    navController: NavController,
    noteID: String?
){
    val noteList = noteViewModel.noteList.collectAsState().value.filter {
        it.id == UUID.fromString(noteID)
    }
    var title by remember {
        mutableStateOf(noteList[0].title)
    }
    var description by remember {
        mutableStateOf(noteList[0].description)
    }
    val context = LocalContext.current
    Scaffold(
        modifier = Modifier
            .fillMaxSize(),
        topBar = {
            CenterAlignedTopAppBar(
                title = {
                    Text(
                        text = "Edit Note"
                    )
                },
                navigationIcon = {
                    Icon(
                        imageVector = Icons.AutoMirrored.Filled.ArrowBack,
                        contentDescription = "back Icon",
                        modifier = Modifier.clickable {
                            navController.popBackStack()
                        }
                    )
                },
                colors = TopAppBarDefaults.topAppBarColors(
                    containerColor = MaterialTheme.colorScheme.primaryContainer
                )
            )
        }
    ) {innerPadding ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(innerPadding)
        ) {
            Column(
                modifier = Modifier.fillMaxWidth(),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                NoteInputText(
                    text = title,
                    label = "Title",
                    onTextChange = {
//                        if (it.all{ char->
//                                char.isLetter() || char.isWhitespace()
//                            })
                            title = it
                    },
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(top = 9.dp, bottom = 8.dp , start = 8.dp, end = 8.dp)
                )
                NoteInputText(
                    text = description,
                    label = "Add a note",
                    onTextChange = {
//                        if (it.all{ char->
//                                char.isLetter() || char.isWhitespace()
//                            })
                            description = it
                    },
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(top = 9.dp, bottom = 8.dp, start = 8.dp, end = 8.dp)
                )
                NoteButton(
                    text = "Save",
                    onClick = {
                        if(title.isNotEmpty() && description.isNotEmpty()){
                            noteViewModel.updateNote(
                                oldNote = noteList[0],
                                newNote = Note(
                                    title = title,
                                    description = description
                                )
                            )
                            Toast.makeText(context, "Note Edited", Toast.LENGTH_SHORT).show()
                            navController.popBackStack()
                        }
                    }
                )
            }
        }
    }
}