package com.example.fatloss.data

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "diary")
data class DiaryEntry(
    @PrimaryKey(autoGenerate = true) val id: Int = 0,
    val date: String,
    val sourceTimes: String,
    val dayTaken: Int,
    val originalWeight: String,
    val cumulativeWeight: String,
    val yesterdayWeight: String,
    val todayWeight: String,
    val breakfast: String,
    val lunch: String,
    val dinner: String,
    val snacks: String,
    val waterLiters: Double,
    val defecation: String,
    val exercise: Boolean,
    val stayUpLate: Boolean,
    val sleepTime: String,
    val avoidSpicy: Boolean,
    val avoidAlcohol: Boolean,
    val avoidSeafood: Boolean,
    val drinksMilk: Boolean,
    val extra: String?
)