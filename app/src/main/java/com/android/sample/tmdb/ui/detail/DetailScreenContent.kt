package com.android.sample.tmdb.ui.detail

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.hilt.navigation.compose.hiltViewModel
import com.android.sample.tmdb.ui.Content

@Composable
fun MovieDetailScreenContent(
    viewModel: MovieDetailViewModel = hiltViewModel()
) {
    Content(viewModel = viewModel) {
        DetailScreen(detailWrapper = it)
    }
}

@Composable
fun TVShowDetailScreenContent(
    viewModel: TVShowDetailViewModel = hiltViewModel()
) {
    Content(viewModel = viewModel) {
        DetailScreen(detailWrapper = it)
    }
}