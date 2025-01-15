package com.example.jetnote.screen

import android.widget.Toast
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import com.example.jetnote.R
import com.example.jetnote.model.Note
import com.example.jetnote.navigation.NotesScreens
import java.time.format.DateTimeFormatter

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MainScreen(
    navController: NavController,
    noteViewModel: NoteViewModel = viewModel(),
){
    val notes = noteViewModel.getAllNotes()
    val context = LocalContext.current

    Scaffold(
        modifier = Modifier
            .fillMaxSize(),
        topBar = {
            TopAppBar(
                title = {
                    Text(
                        text = stringResource(id = R.string.app_name)
                    )
                },
                colors = TopAppBarDefaults.topAppBarColors(
                    containerColor = MaterialTheme.colorScheme.primaryContainer
                )
            )
        },
        floatingActionButton = {
            FloatingActionButton(
                onClick = {
                    navController.navigate(NotesScreens.AddNoteScreen.name)
                }
            ) {
                Icon(Icons.Filled.Add, "Add Note")
            }
        }
    ) { innerPadding ->
        Column(
            modifier = Modifier
                .padding(innerPadding)
                .fillMaxSize()
        ) {
            LazyColumn{
                items(notes){note ->
                    NoteRow(
                        note = note,
                        navController = navController
                    ) {
                        noteViewModel.removeNote(it)
                        Toast.makeText(context, "Note Deleted", Toast.LENGTH_SHORT).show()
                    }
                }
            }
        }
    }
}

@Composable
fun NoteRow(
    modifier: Modifier = Modifier,
    note: Note,
    navController: NavController,
    onNoteClicked: (Note)-> Unit
){
    Surface(
        modifier = modifier
            .fillMaxWidth()
            .padding(4.dp)
            .clip(RoundedCornerShape(topEnd = 33.dp, bottomStart = 33.dp)),
        color = MaterialTheme.colorScheme.surfaceContainer,
        shadowElevation = 6.dp
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(start = 8.dp, end = 8.dp),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Column(
                modifier = Modifier
                    .clickable {
                        navController.navigate(NotesScreens.EditNoteScreen.name + "/${note.id}")
                    }
                    .padding(horizontal = 14.dp, vertical = 6.dp)
                    .weight(0.9f),
                horizontalAlignment = Alignment.Start
            ) {
                Text(
                    note.title,
                    style = MaterialTheme.typography.headlineMedium,
                    modifier = Modifier.padding(vertical = 4.dp)
                )
                Text(
                    note.description,
                    style = MaterialTheme.typography.bodyLarge,
                    modifier = Modifier.padding(vertical = 4.dp)
                )
                Text(
                    note.entryDate.format(DateTimeFormatter.ofPattern("EEE, d MMM")),
                    style = MaterialTheme.typography.labelSmall,
                    modifier = Modifier.padding(vertical = 4.dp)
                )
            }
            Icon(
                imageVector = Icons.Filled.Delete,
                contentDescription = "Delete Icon",
                modifier = Modifier
                    .clickable {
                        onNoteClicked(note)
                    }
                    .weight(0.1f)
            )
        }
    }

}