package com.example.taskmanager.ui.components

import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.CheckCircle
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.filled.PlayArrow
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.PrimaryTabRow
import androidx.compose.material3.Tab
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import com.example.taskmanager.ui.homescreen.HomeScreenUiState

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun NavigationTabExample(
    modifier: Modifier = Modifier,
    uiState: HomeScreenUiState,
    onSelectTab: (Int) -> Unit
) {

}

@Preview(
    showBackground = true,
    showSystemUi = true
)
@Composable
fun NavigationTabExamplePreview() {
    NavigationTabExample(
        uiState = HomeScreenUiState(tabIndexSelected = 1),
        onSelectTab = {}
    )
}