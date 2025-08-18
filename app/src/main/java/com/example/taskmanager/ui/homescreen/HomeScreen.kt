package com.example.taskmanager.ui.homescreen

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.CheckCircle
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.filled.PlayArrow
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.PrimaryTabRow
import androidx.compose.material3.Tab
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.NavController
import com.example.taskmanager.data.entity.TaskEntity
import com.example.taskmanager.ui.components.AppMainScaffold
import com.example.taskmanager.ui.components.TaskList

enum class NavigationTab(
    val title: String,
    val icon: ImageVector
) {
    FAVORITE("Favorite", Icons.Filled.Favorite),
    UNCOMPLETED("Uncompleted", Icons.Filled.PlayArrow),
    COMPLETED("Completed", Icons.Filled.CheckCircle)
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun HomeScreen(
    navController: NavController,
    uiState: HomeScreenUiState,
    onCompletedChange: (TaskEntity, Boolean) -> Unit,
    onFavoriteToggle: (TaskEntity) -> Unit,
    onSelectTab: (Int) -> Unit
) {

    AppMainScaffold(
        title = "Tarefas",
        navController = navController,
        floatingActionButton = {
            FloatingActionButton(onClick = {}) {
                Icon(
                    imageVector = Icons.Filled.Add,
                    contentDescription = null
                )
            }
        }) { innerPadding ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(innerPadding)
        ) {
            var selectedTab = uiState.tabIndexSelected
            var pagerState =
                rememberPagerState(
                    initialPage = selectedTab,
                    pageCount = { NavigationTab.entries.size })

            LaunchedEffect(selectedTab) {
                pagerState.animateScrollToPage(selectedTab)
            }

            LaunchedEffect(pagerState.currentPage, pagerState.isScrollInProgress) {
                if (!pagerState.isScrollInProgress) {
                    onSelectTab(pagerState.currentPage)
                }
            }

            PrimaryTabRow(selectedTabIndex = selectedTab, modifier = Modifier) {
                NavigationTab.entries.forEachIndexed { index, destination ->
                    Tab(
                        selected = selectedTab == index,
                        onClick = {
                            onSelectTab(index)
                        },
                        text = {
                            Text(
                                text = destination.title,
                                maxLines = 2,
                                overflow = TextOverflow.Ellipsis
                            )
                        },
                        icon = {
                            Icon(
                                imageVector = destination.icon,
                                contentDescription = null
                            )
                        }
                    )
                }
            }

            HorizontalPager(
                state = pagerState
            ) { pageIndex ->
                when (pageIndex) {
                    0 -> TaskList(uiState.favoriteTasks, onCompletedChange, onFavoriteToggle)
                    1 -> TaskList(uiState.uncompletedTasks, onCompletedChange, onFavoriteToggle)
                    2 -> TaskList(uiState.completedTasks, onCompletedChange, onFavoriteToggle)
                }
            }
        }
    }
}

@Preview(showSystemUi = true, showBackground = true)
@Composable
fun HomeScreenPreview() {
    HomeScreen(
        navController = NavController(LocalContext.current),
        uiState = HomeScreenUiState(),
        onCompletedChange = { _, _ -> },
        onFavoriteToggle = {},
        onSelectTab = {}
    )
}