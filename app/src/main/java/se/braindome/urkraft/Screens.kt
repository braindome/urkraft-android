package se.braindome.urkraft

sealed class Screens (val screen: String ){
    data object Profile: Screens("Profile")
    data object Settings: Screens("Settings")
    data object Home: Screens("Home")
}