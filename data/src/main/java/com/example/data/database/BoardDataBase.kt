package com.example.data.database

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.example.data.model.BoardDTO

@Database(version = 1,
    entities = [BoardDTO::class, RemoteKey::class]
)
@TypeConverters(CommentConverter::class)
abstract class BoardDataBase : RoomDatabase(){
    abstract fun boardDao():BoardDao
    abstract fun remoteKeyDao():RemoteKeyDao
}