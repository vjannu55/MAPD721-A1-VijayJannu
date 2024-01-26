package com.malikosft.assignment
import android.annotation.SuppressLint
import android.content.res.Resources
import android.os.Bundle
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Button
import androidx.compose.material3.Card
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
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch


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

    val keyboradcontroller =LocalSoftwareKeyboardController.current

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
        Row(
            horizontalArrangement = Arrangement.SpaceEvenly,
            modifier = Modifier
                .fillMaxWidth()
                .padding(5.dp)
        )
        {

            Button(
                onClick = {
                    CoroutineScope(Dispatchers.Main).launch {


                        val preferences = dataStore.data.collect() {
                            val data = it.get(key)

                            val list = data?.split(",")

                            val name_user = list?.get(0)
                            if (name_user != null && name_user.isNotEmpty())
                            {
                                name.value = name_user
                            }

                            val email_user = list?.get(1)
                            if (email_user != null && email_user.isNotEmpty())
                            {
                                email.value = email_user
                            }
                            val id_user = list?.get(2)
                            if (id_user != null) {
                                if (id_user.isNotEmpty())
                                    id.value = id_user
                            }

                        }
                    }
                },
                modifier = Modifier
                    .width(100.dp)
                    .padding(start = 10.dp, end = 10.dp)
            ) {
                Text(text = "Load")
            }

            Button(
                onClick = {
                    if (valid) {
                        CoroutineScope(Dispatchers.Main).launch {
                            savedata(name.value,email.value,id.value)
                            Toast.makeText(context,"Data save successfully", Toast.LENGTH_LONG).show()
                            name.value =""
                            email.value=""
                            id.value =""
                            keyboradcontroller?.hide()
                        }
                    }
                    else{
                        Toast.makeText(context,"Fields can not be empty",Toast.LENGTH_LONG).show()
                    }
                },
                modifier = Modifier
                    .width(100.dp)
                    .padding(start = 10.dp, end = 10.dp)
            ) {
                Text(text = "Save")
            }

            Button(
                onClick = {
                    CoroutineScope(Dispatchers.Main).launch {
                        dataStore.edit {
                            it.clear()
                        }
                    }
                    name.value =""
                    email.value =""
                    id.value =""
                    name_showing.value =""
                    id_showing.value =""
                },
                modifier = Modifier
                    .width(110.dp)
                    .padding(start = 10.dp, end = 10.dp)
            ) {
                Text(text = "Clear")
            }
        }
        Spacer(modifier = Modifier.height(50.dp))

        Card(
            modifier = Modifier
                .fillMaxWidth()
                .height(100.dp)
                .padding(5.dp),
        ) {
            Text(text = "Name "+"Vijay jannu", modifier = Modifier.padding(8.dp))
            Text(text = "ID  "+"301-413-452", modifier = Modifier.padding(8.dp))
        }
    }

}

    