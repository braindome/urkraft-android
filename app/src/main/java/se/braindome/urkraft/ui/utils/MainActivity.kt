package se.braindome.urkraft.ui.utils

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.ui.graphics.toArgb
import androidx.core.splashscreen.SplashScreen.Companion.installSplashScreen
import androidx.core.view.WindowInsetsControllerCompat
import androidx.lifecycle.lifecycleScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import se.braindome.urkraft.UrkraftApp
import se.braindome.urkraft.ui.theme.Gray80
import se.braindome.urkraft.ui.theme.UrkraftTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        val splashScreen = installSplashScreen()
        var keepSplashOnScreen = true
        splashScreen.setKeepOnScreenCondition { keepSplashOnScreen }
        lifecycleScope.launch {
            delay(300)

            keepSplashOnScreen = false
        }
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

