package com.pemmob.animefind

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.navigation.compose.rememberNavController
import com.pemmob.animefind.data.remote.RetrofitClient
import com.pemmob.animefind.data.repository.AnimeRepository
import com.pemmob.animefind.ui.navigation.AppNavGraph
import com.pemmob.animefind.ui.theme.AnimeTheme

class MainActivity : ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val repository = AnimeRepository(RetrofitClient.instance)

        setContent {
            AnimeTheme {
                val navController = rememberNavController()
                AppNavGraph(
                    navController = navController,
                    repository = repository
                )
            }
        }
    }
}