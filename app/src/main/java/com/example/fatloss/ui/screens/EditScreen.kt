package com.example.fatloss.ui.screens

import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.fatloss.data.DiaryEntry
import com.example.fatloss.ui.DiaryViewModel
import kotlinx.coroutines.launch

@Composable
fun EditScreen(vm: DiaryViewModel, entryId: Int? = null, onDone: () -> Unit) {
    val scope = rememberCoroutineScope()
    val scaffoldState = rememberScaffoldState()

    var date by remember { mutableStateOf("今日") }
    var sourceTimes by remember { mutableStateOf("昨天 12:38\n来自电脑端\n\n14:26\n来自手机端") }
    var dayTaken by remember { mutableStateOf("13") }
    var originalWeight by remember { mutableStateOf("179.5") }
    var cumulativeWeight by remember { mutableStateOf("159.5") }
    var yesterdayWeight by remember { mutableStateOf("176.5") }
    var todayWeight by remember { mutableStateOf("177.3") }
    var breakfast by remember { mutableStateOf("武大郎烧饼") }
    var lunch by remember { mutableStateOf("卤丸子+西葫芦炒鸡蛋+半份米饭") }
    var dinner by remember { mutableStateOf("炒白菜+凉面") }
    var snacks by remember { mutableStateOf("无") }
    var waterLiters by remember { mutableStateOf("1.420") }
    var defecation by remember { mutableStateOf("1 次") }
    var exercise by remember { mutableStateOf(false) }
    var stayUpLate by remember { mutableStateOf(false) }
    var sleepTime by remember { mutableStateOf("23:30") }
    var avoidSpicy by remember { mutableStateOf(true) }
    var avoidAlcohol by remember { mutableStateOf(true) }
    var avoidSeafood by remember { mutableStateOf(true) }
    var drinksMilk by remember { mutableStateOf(false) }
    var extra by remember { mutableStateOf("") }

    Scaffold(scaffoldState = scaffoldState, topBar = { TopAppBar(title = { Text("编辑 / 新增打卡") }) }) { padding ->
        Column(modifier = Modifier.padding(12.dp).fillMaxSize()) {
            OutlinedTextField(value = date, onValueChange = { date = it }, label = { Text("日期") })
            Spacer(Modifier.height(8.dp))
            OutlinedTextField(value = dayTaken, onValueChange = { dayTaken = it }, label = { Text("服用第几天") })
            Spacer(Modifier.height(8.dp))
            OutlinedTextField(value = originalWeight, onValueChange = { originalWeight = it }, label = { Text("原始体重") })
            Spacer(Modifier.height(8.dp))
            OutlinedTextField(value = yesterdayWeight, onValueChange = { yesterdayWeight = it }, label = { Text("昨日体重") })
            Spacer(Modifier.height(8.dp))
            OutlinedTextField(value = todayWeight, onValueChange = { todayWeight = it }, label = { Text("今日体重") })
            Spacer(Modifier.height(8.dp))
            OutlinedTextField(value = breakfast, onValueChange = { breakfast = it }, label = { Text("早餐") })
            Spacer(Modifier.height(8.dp))
            OutlinedTextField(value = lunch, onValueChange = { lunch = it }, label = { Text("午餐") })
            Spacer(Modifier.height(8.dp))
            OutlinedTextField(value = dinner, onValueChange = { dinner = it }, label = { Text("晚餐") })
            Spacer(Modifier.height(8.dp))
            OutlinedTextField(value = waterLiters, onValueChange = { waterLiters = it }, label = { Text("饮水量(L)") })
            Spacer(Modifier.height(8.dp))

            Row(verticalAlignment = androidx.compose.ui.Alignment.CenterVertically) {
                Checkbox(checked = exercise, onCheckedChange = { exercise = it })
                Text(text = "是否运动")
            }
            Spacer(Modifier.height(8.dp))

            Button(onClick = {
                // Simple validation
                if (todayWeight.isBlank()) {
                    scope.launch { scaffoldState.snackbarHostState.showSnackbar("请填写今日体重") }
                    return@Button
                }
                val today = todayWeight.toDoubleOrNull()
                if (today == null) {
                    scope.launch { scaffoldState.snackbarHostState.showSnackbar("今日体重必须为数字，例如 77.3") }
                    return@Button
                }

                val entry = DiaryEntry(
                    id = entryId ?: 0,
                    date = date,
                    sourceTimes = sourceTimes,
                    dayTaken = dayTaken.toIntOrNull() ?: 0,
                    originalWeight = originalWeight,
                    cumulativeWeight = cumulativeWeight,
                    yesterdayWeight = yesterdayWeight,
                    todayWeight = todayWeight,
                    breakfast = breakfast,
                    lunch = lunch,
                    dinner = dinner,
                    snacks = snacks,
                    waterLiters = waterLiters.toDoubleOrNull() ?: 0.0,
                    defecation = defecation,
                    exercise = exercise,
                    stayUpLate = stayUpLate,
                    sleepTime = sleepTime,
                    avoidSpicy = avoidSpicy,
                    avoidAlcohol = avoidAlcohol,
                    avoidSeafood = avoidSeafood,
                    drinksMilk = drinksMilk,
                    extra = if (extra.isBlank()) null else extra
                )
                scope.launch {
                    if (entryId == null) vm.add(entry) else vm.update(entry)
                    onDone()
                }
            }) { Text("保存") }
        }
    }
}
