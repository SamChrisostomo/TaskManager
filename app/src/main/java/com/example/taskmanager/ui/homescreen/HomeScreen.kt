package com.example.taskmanager.ui.homescreen

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.CheckCircle
import androidx.compose.material.icons.filled.Done
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.filled.FavoriteBorder
import androidx.compose.material.icons.filled.PlayArrow
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.ModalBottomSheet
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.PrimaryTabRow
import androidx.compose.material3.Tab
import androidx.compose.material3.Text
import androidx.compose.material3.rememberModalBottomSheetState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.input.KeyboardCapitalization
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.example.taskmanager.R
import com.example.taskmanager.data.entity.TaskEntity
import com.example.taskmanager.ui.components.AppMainScaffold
import com.example.taskmanager.ui.components.TaskList
import kotlinx.coroutines.launch

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
    newTask: NewTask,
    onCompletedChange: (TaskEntity, Boolean) -> Unit,
    onFavoriteToggle: (TaskEntity) -> Unit,
    onSelectTab: (Int) -> Unit,
    onShowBottomSheet: (Boolean) -> Unit,
    onTaskTitleChanged: (String) -> Unit,
    onTaskDescriptionChanged: (String) -> Unit,
    onTaskFavoriteChanged: (Boolean) -> Unit,
    addNewTask: () -> Unit
) {

    AppMainScaffold(
        title = "Tarefas",
        navController = navController,
        floatingActionButton = {
            FloatingActionButton(onClick = {
                onShowBottomSheet(true)
            }) {
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
            val selectedTab = uiState.tabIndexSelected
            val pagerState =
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
            val sheetState = rememberModalBottomSheetState(
                skipPartiallyExpanded = false
            )
            val scope = rememberCoroutineScope()
            val showBottomSheet = uiState.showBottomSheet

            if (showBottomSheet) {
                ModalBottomSheet(
                    onDismissRequest = {
                        scope.launch { sheetState.hide() }.invokeOnCompletion {
                            if (!sheetState.isVisible) {
                                onShowBottomSheet(false)
                            }
                        }
                    },
                    sheetState = sheetState
                ) {
                    Column(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(16.dp)
                    ) {
                        Row(
                            modifier = Modifier.fillMaxWidth()
                        ) {
                            OutlinedTextField(
                                value = newTask.title,
                                onValueChange = { newTitle ->
                                    onTaskTitleChanged(newTitle)
                                },
                                label = {
                                    Text(text = "Nova tarefa")
                                },
                                keyboardOptions = KeyboardOptions(
                                    keyboardType = KeyboardType.Text,
                                    showKeyboardOnFocus = true,
                                    capitalization = KeyboardCapitalization.Words
                                ),
                                modifier = Modifier
                                    .fillMaxWidth()
                            )
                        }
                        Row(
                            modifier = Modifier.fillMaxWidth()
                        ) {
                            OutlinedTextField(
                                value = newTask.description,
                                onValueChange = { description ->
                                    onTaskDescriptionChanged(description)
                                },
                                label = {
                                    Text(text = "Detalhes")
                                },
                                keyboardOptions = KeyboardOptions(
                                    keyboardType = KeyboardType.Text,
                                    showKeyboardOnFocus = true,
                                    capitalization = KeyboardCapitalization.Words
                                ),
                                modifier = Modifier
                                    .fillMaxWidth()
                            )
                        }
                        Row(
                            modifier = Modifier
                                .fillMaxWidth()
                                .padding(top = 16.dp),
                            horizontalArrangement = Arrangement.SpaceBetween
                        ) {
                            IconButton(onClick = {
                                onTaskFavoriteChanged(!newTask.isFavorite)
                            }) {
                                Icon(
                                    imageVector = when (newTask.isFavorite) {
                                        true -> Icons.Filled.Favorite
                                        false -> Icons.Filled.FavoriteBorder
                                    },
                                    contentDescription = null
                                )
                            }
                            Button(onClick = {
                                addNewTask()
                            }) {
                                Icon(
                                    imageVector = Icons.Filled.Done,
                                    contentDescription = null,
                                    modifier = Modifier.padding(ButtonDefaults.TextButtonWithIconContentPadding)
                                )
                                Spacer(modifier = Modifier.padding(ButtonDefaults.IconSpacing))
                                Text(text = stringResource(R.string.modal_save_btn))
                            }
                        }
                    }
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
        newTask = NewTask(),
        onCompletedChange = { _, _ -> },
        onFavoriteToggle = {},
        onSelectTab = {},
        onShowBottomSheet = {},
        onTaskTitleChanged = {},
        onTaskDescriptionChanged = {},
        onTaskFavoriteChanged = {},
        addNewTask = {}
    )
}