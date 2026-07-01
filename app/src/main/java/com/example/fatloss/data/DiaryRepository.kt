package com.qwe153999.slimmingcheckinapp.data

import kotlinx.coroutines.flow.Flow

class DiaryRepository(private val dao: DiaryDao) {
    fun entries(): Flow<List<DiaryEntry>> = dao.entries()
    suspend fun get(id: Int) = dao.get(id)
    suspend fun insert(entry: DiaryEntry) = dao.insert(entry)
    suspend fun update(entry: DiaryEntry) = dao.update(entry)
    suspend fun delete(entry: DiaryEntry) = dao.delete(entry)

    companion object {
        // helper to create repository with database instance
        fun create(context: android.content.Context): DiaryRepository {
            val db = AppDatabase.getDatabase(context)
            return DiaryRepository(db.diaryDao())
        }
    }
}