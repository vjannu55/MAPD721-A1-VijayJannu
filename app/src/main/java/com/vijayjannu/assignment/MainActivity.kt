package com.malikosft.assignment
import android.annotation.SuppressLint
import android.content.res.Resources
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalSoftwareKeyboardController
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.datastore.core.DataStore
import androidx.datastore.createDataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.preferencesKey
import androidx.datastore.preferences.createDataStore
import com.malikosft.assignment.ui.theme.AssignmentTheme


class MainActivity : ComponentActivity() {
    lateinit var dataStore: DataStore<androidx.datastore.preferences.core.Preferences>
    val key = preferencesKey<String>("username")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        dataStore = createDataStore("setting")
        setContent {
            AssignmentTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    form_filling()

                }
            }
        }
    }
}
@Preview
@SuppressLint("SuspiciousIndentation")
@OptIn(ExperimentalComposeUiApi::class)
@Composable
fun form_filling() {

    val name = remember { mutableStateOf("") }
    val email = remember { mutableStateOf("") }
    val id = remember { mutableStateOf("452") }

    val name_showing = remember { mutableStateOf("") }
    val id_showing = remember { mutableStateOf("") }

    val keyboradcontroller = LocalSoftwareKeyboardController.current

    val context = LocalContext.current

    val valid = remember(name.value, email.value) {

        name.value.toString().isNotEmpty() && email.value.toString().isNotEmpty()
    }
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(5.dp)
    ) {

        OutlinedTextField(
            value = name.value, onValueChange = {
                name.value = it
            },
            label = { Text(text = "Name") },
            modifier = Modifier
                .fillMaxWidth()
                .padding(5.dp)

        )
        OutlinedTextField(
            value = email.value, onValueChange = {
                email.value = it
            },
            label = { Text(text = "Email") },
            modifier = Modifier
                .fillMaxWidth()
                .padding(5.dp)

        )
        OutlinedTextField(
            value = id.value, onValueChange = {
                id.value = it
            },
            label = { Text(text = "ID") },
            modifier = Modifier
                .fillMaxWidth()
                .padding(5.dp)

        )

    }
}