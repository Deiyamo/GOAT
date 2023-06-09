package com.example.goat.presentation

sealed class Screen(val route: String) {
    object MainScreen: Screen("main")
    object HomeScreen: Screen("home")
    object DailyQuizScreen: Screen("daly_quiz")
    object QuizScreen: Screen("quiz")
    object ChallengeScreen: Screen("challenge")
}
