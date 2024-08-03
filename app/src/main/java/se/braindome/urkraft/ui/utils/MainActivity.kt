package se.braindome.urkraft.ui.utils

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import se.braindome.urkraft.UrkraftApp
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
    }
}

