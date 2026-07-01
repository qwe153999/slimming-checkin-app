package com.example.fatloss.ui.screens

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.fatloss.data.DiaryEntry
import com.example.fatloss.ui.DiaryViewModel

@Composable
fun ListScreen(vm: DiaryViewModel, onAdd: () -> Unit, onEdit: (Int) -> Unit) {
    val items = vm.entries.collectAsState()
    Scaffold(
        topBar = { TopAppBar(title = { Text("瘦身每日打卡") }) },
        floatingActionButton = {
            FloatingActionButton(onClick = onAdd) { Text("+") }
        }
    ) { padding ->
        LazyColumn(modifier = Modifier.padding(padding)) {
            items(items.value) { entry ->
                DiaryCard(entry = entry, onClick = { onEdit(entry.id) }, onDelete = { vm.delete(entry) })
            }
        }
    }
}

@Composable
fun DiaryCard(entry: DiaryEntry, onClick: () -> Unit, onDelete: () -> Unit) {
    Card(modifier = Modifier
        .padding(12.dp)
        .fillMaxWidth(), elevation = 6.dp) {
        Column(modifier = Modifier.padding(12.dp)) {
            Text(text = "日期: ${entry.date}", style = MaterialTheme.typography.h6)
            Spacer(Modifier.height(6.dp))
            Text(text = "🫧服用第几天：${entry.dayTaken}")
            Text(text = "昨日体重：${entry.yesterdayWeight}  今日体重：${entry.todayWeight}")
            Spacer(Modifier.height(8.dp))
            Row {
                Button(onClick = onClick, modifier = Modifier.weight(1f)) { Text("编辑") }
                Spacer(Modifier.width(8.dp))
                Button(onClick = onDelete, modifier = Modifier.weight(1f)) { Text("删除") }
            }
        }
    }
}