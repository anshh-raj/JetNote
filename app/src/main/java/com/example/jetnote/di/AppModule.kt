package com.example.jetnote.di

import android.content.Context
import androidx.room.Room
import com.example.jetnote.data.NotesDatabase
import com.example.jetnote.data.NotesDatabaseDao
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@InstallIn(SingletonComponent::class)
@Module
object AppModule {

    @Singleton
    @Provides
    fun provideNotesDao(notesDatabase: NotesDatabase): NotesDatabaseDao
    = notesDatabase.noteDao()

    @Singleton
    @Provides
    fun provideAppDatabase(@ApplicationContext context: Context): NotesDatabase
    = Room.databaseBuilder(
        context,
        NotesDatabase::class.java,
        name = "notes_db")
        .fallbackToDestructiveMigrationFrom()
        .build()
}