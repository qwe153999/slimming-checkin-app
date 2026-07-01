package com.qwe153999.slimmingcheckinapp.data

import androidx.room.*
import kotlinx.coroutines.flow.Flow

@Dao
interface DiaryDao {
    @Query("SELECT * FROM diary ORDER BY id DESC")
    fun entries(): Flow<List<DiaryEntry>>

    @Query("SELECT * FROM diary WHERE id = :id LIMIT 1")
    suspend fun get(id: Int): DiaryEntry?

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(entry: DiaryEntry): Long

    @Update
    suspend fun update(entry: DiaryEntry)

    @Delete
    suspend fun delete(entry: DiaryEntry)
}