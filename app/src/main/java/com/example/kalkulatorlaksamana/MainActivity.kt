package com.example.kalkulatorlaksamana

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.runtime.SideEffect
import com.example.kalkulatorlaksamana.ui.theme.KalkulatorLaksamanaTheme
import com.example.kalkulatorlaksamana.ui.theme.DarkGray
import com.google.accompanist.systemuicontroller.rememberSystemUiController

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            KalkulatorLaksamanaTheme { //TODO: MEMANGGIL FUNGSI KalkulatorLaksamanaTheme dari file Theme.kt
                val viewModel = ViewModelKalkulator() //TODO: MENETAPKAN FILE VIEWMODELKALKULATOR SEBAGAI VIEW MODEL
                val systemUiController = rememberSystemUiController() //TODO: MEMANGGIL SYSTEMUICONTROLLER DARI DEPENDECIES GOOGLE ACCOMPANIST
                SideEffect {
                    systemUiController.setSystemBarsColor( //TODO: MENETAPKAN BACKGROUND COLOR
                        color = DarkGray,
                        darkIcons = false
                    )

                }

                UIKalkulator( //TODO: MEMANGGIL FILE UI UNTUK KALKULATOR, DAN MENJALANKAN VIEWMODELNYA
                    viewModel = viewModel,
                )
            }
        }
    }
}

