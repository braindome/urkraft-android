package se.braindome.urkraft.ui.utils

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.ui.graphics.toArgb
import androidx.core.view.WindowInsetsControllerCompat
import se.braindome.urkraft.UrkraftApp
import se.braindome.urkraft.ui.theme.Gray80
import se.braindome.urkraft.ui.theme.UrkraftTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            UrkraftTheme {
                UrkraftApp()
            }
        }
        setSystemBarColors()
    }

    private fun setSystemBarColors() {
        val windowInsetsController = WindowInsetsControllerCompat(window, window.decorView)
        windowInsetsController.isAppearanceLightStatusBars = false
        windowInsetsController.isAppearanceLightNavigationBars = false
        window.statusBarColor = Gray80.toArgb()
        window.navigationBarColor = Gray80.toArgb()
    }
}

