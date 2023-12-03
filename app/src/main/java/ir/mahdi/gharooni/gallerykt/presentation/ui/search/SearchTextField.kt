@file:OptIn(ExperimentalMaterial3Api::class)

package ir.mahdi.gharooni.gallerykt.presentation.ui.search

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import ir.mahdi.gharooni.gallerykt.presentation.view_model.SearchViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.collectLatest

@Composable
fun SearchTextField() {

    val viewModel = viewModel<SearchViewModel>()


    var searchText by remember { mutableStateOf("") }

    // Create a Flow for the search text
    val searchFlow: StateFlow<String> = remember { MutableStateFlow(searchText) }

    // Launch a coroutine to collect the Flow and notify when the text changes
    LaunchedEffect(searchFlow) {
        searchFlow.collectLatest { newSearchText ->
            viewModel.startSearch(newSearchText)
        }
    }

    OutlinedTextField(
        value = searchText,
        onValueChange = {
            searchText = it
            // Update the StateFlow when the text changes

            (searchFlow as MutableStateFlow).value = it
        },
        modifier = Modifier
            .fillMaxWidth()
            .padding(16.dp),
        label = { Text("Search") },
        leadingIcon = {
            Icon(
                imageVector = Icons.Default.Search,
                contentDescription = null
            )
        },
        keyboardOptions = KeyboardOptions.Default.copy(
            imeAction = ImeAction.Search
        ),
        shape = RoundedCornerShape(12.dp)
    )
}