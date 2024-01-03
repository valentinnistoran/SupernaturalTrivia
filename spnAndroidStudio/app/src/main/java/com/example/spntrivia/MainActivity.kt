package com.example.spntrivia

import android.os.Bundle
import com.google.android.material.bottomnavigation.BottomNavigationView
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.findNavController
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.setupActionBarWithNavController
import androidx.navigation.ui.setupWithNavController
import com.example.spntrivia.databinding.ActivityMainBinding
import com.example.spntrivia.gameHistoryDB.GameDatabase
import com.example.spntrivia.gameHistoryDB.QuizResultRepository
import com.example.spntrivia.ui.trivia.TriviaViewModel

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    //private lateinit var triviaDatabase: TriviaDatabase
    private lateinit var gameDatabase: GameDatabase
    private lateinit var quizResultRepository: QuizResultRepository
    private lateinit var triviaViewModel: TriviaViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        if (supportActionBar != null) {
            supportActionBar!!.hide()
        }

        //triviaDatabase = TriviaDatabase.getDatabase(this)
//        gameDatabase = GameDatabase.getDatabase(this)
//        quizResultRepository = QuizResultRepository(gameDatabase.quizResultDao())
//        triviaViewModel = TriviaViewModel(quizResultRepository)


        val navView: BottomNavigationView = binding.navView

        val navController = findNavController(R.id.nav_host_fragment_activity_main)
        // Passing each menu ID as a set of Ids because each
        // menu should be considered as top level destinations.
        val appBarConfiguration = AppBarConfiguration(
            setOf(
                R.id.navigation_home, R.id.navigation_info, R.id.navigation_profile, R.id.navigation_settings
            )
        )
        setupActionBarWithNavController(navController, appBarConfiguration)
        navView.setupWithNavController(navController)
    }


}