package com.example.fatloss

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.fatloss.ui.screens.EditScreen
import com.example.fatloss.ui.screens.ListScreen
import com.example.fatloss.ui.theme.FatLossTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            FatLossTheme {
                Surface(color = MaterialTheme.colors.background) {
                    AppNav()
                }
            }
        }
    }
}

@Composable
fun AppNav() {
    val nav = rememberNavController()
    NavHost(navController = nav, startDestination = "list") {
        composable("list") {
            val vm: com.example.fatloss.ui.DiaryViewModel = viewModel()
            ListScreen(vm, onAdd = { nav.navigate("edit") }, onEdit = { id -> nav.navigate("edit/$id") })
        }
        composable("edit") {
            val vm: com.example.fatloss.ui.DiaryViewModel = viewModel()
            EditScreen(vm, onDone = { nav.popBackStack() })
        }
        composable("edit/{id}") { backStack ->
            val vm: com.example.fatloss.ui.DiaryViewModel = viewModel()
            val id = backStack.arguments?.getString("id")?.toIntOrNull()
            EditScreen(vm, entryId = id, onDone = { nav.popBackStack() })
        }
    }
}

@Preview(showBackground = true)
@Composable
fun DefaultPreview() {
    FatLossTheme {
        // preview left empty
    }
}