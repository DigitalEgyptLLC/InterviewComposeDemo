package com.intercept.myapplication.ui.home

import android.content.Intent
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.itemsIndexed
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.hilt.navigation.compose.hiltViewModel
import coil3.compose.AsyncImage
import com.intercept.myapplication.R
import com.intercept.myapplication.ui.details.DetailsActivity
import kotlinx.coroutines.launch

@Composable
fun HomeScreen(homeViewModel: HomeViewModel = hiltViewModel()) {
    val uiState by homeViewModel.uiState.collectAsState()
    val coroutineScope = rememberCoroutineScope()
    val actions by homeViewModel.actions.collectAsState()

    actions?.let { action ->
        if (action is HomeNavigationAction.NavigateToDetails) {
            val context = LocalContext.current
            context.startActivity(Intent(context, DetailsActivity::class.java))
        }
    }

    when (uiState) {
        HomeUIState.Loading -> {
            Box(
                modifier = Modifier.fillMaxSize(),
                contentAlignment = Alignment.Center
            ) {
                CircularProgressIndicator()
            }
        }

        is HomeUIState.Success -> {
            val list = (uiState as HomeUIState.Success).list

            LazyVerticalGrid(
                columns = GridCells.Fixed(count = 3)
            ) {
                itemsIndexed(list) { index, _ ->
                    Column {
                        AsyncImage(
                            modifier = Modifier
                                .clickable {
                                    coroutineScope.launch {
                                        homeViewModel.onDetailsClicked()
                                    }
                                },
                            model = list[index].imageUrl,
                            contentScale = ContentScale.Crop,
                            contentDescription = null,
                        )

                        Text(text = stringResource(id = R.string.home_item_title_prefix) + index.toString())
                    }
                }
            }
        }

        is HomeUIState.Failure -> {
            Box(
                modifier = Modifier.fillMaxSize(),
                contentAlignment = Alignment.Center
            ) {
                val errorMessage = remember {
                    (uiState as HomeUIState.Failure).errorMessage
                }
                Text(text = errorMessage)
            }
        }
    }
}

