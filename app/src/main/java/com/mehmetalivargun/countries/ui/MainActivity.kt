package com.mehmetalivargun.countries.ui

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.mehmetalivargun.countries.R
import com.mehmetalivargun.countries.ui.container.HomeContainerFragment
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        if (savedInstanceState == null) {
            supportFragmentManager.beginTransaction()
                .add(R.id.root_id, HomeContainerFragment())
                .commit()
        }
    }
}