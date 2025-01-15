package com.example.jetnote.navigation

import androidx.compose.runtime.Composable
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.example.jetnote.screen.AddNoteScreen
import com.example.jetnote.screen.EditNoteScreen
import com.example.jetnote.screen.MainScreen

import com.example.jetnote.screen.NoteViewModel

@Composable
fun NoteNavigation(){
    val navController = rememberNavController()
    val noteViewModel = viewModel<NoteViewModel>()
    NavHost(navController = navController, startDestination = NotesScreens.MainScreen.name){
        composable(NotesScreens.MainScreen.name){
            MainScreen(navController = navController, noteViewModel = noteViewModel)
        }
        composable(NotesScreens.AddNoteScreen.name){
            AddNoteScreen(noteViewModel = noteViewModel, navController = navController)
        }
        composable(NotesScreens.EditNoteScreen.name + "/{note}",
            arguments = listOf(navArgument(name = "note"){type = NavType.StringType}))
        { backStackEntry ->
            EditNoteScreen(noteViewModel = noteViewModel, navController = navController,
                noteID = backStackEntry.arguments?.getString("note"))
        }
    }
}