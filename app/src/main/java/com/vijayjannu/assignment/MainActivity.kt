package com.malikosft.assignment
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.preferencesKey


class MainActivity : ComponentActivity() {

    lateinit var dataStore: DataStore<Preferences>
    val key  = preferencesKey<String>("username")

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
        }
    }


}

