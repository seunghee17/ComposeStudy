package com.example.pager

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.material.Scaffold
import androidx.compose.material.Tab
import androidx.compose.material.TabRow
import androidx.compose.material.Text
import androidx.compose.material.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.derivedStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.style.LineHeightStyle
import kotlinx.coroutines.launch

@OptIn(ExperimentalStdlibApi::class, ExperimentalFoundationApi::class)
@Composable
fun MainScreen(){
    val scope = rememberCoroutineScope()
    val pagerState = rememberPagerState(pageCount = {MyTab.values().size})
    val selectedTabIndex = remember{ derivedStateOf { pagerState.currentPage }  }

    Scaffold(
        topBar = { TopAppBar (title = {Text(text = "Home")})}
    ){
        //toprow와 horizontalpager을 묶을 아이
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(top = it.calculateTopPadding())
        ){
            TabRow(
                selectedTabIndex = selectedTabIndex.value,
                modifier = Modifier.fillMaxWidth(),
                backgroundColor = Color.Yellow,
                contentColor = Color.Green
                ) {
                MyTab.values().forEachIndexed{index, currentTab->
                    Tab(
                        selected = selectedTabIndex.value == index,
                        selectedContentColor = Color.Black,
                        unselectedContentColor = Color.Green,
                        interactionSource = null,
                        onClick = {
                            scope.launch {
                                //ordinal??
                                pagerState.animateScrollToPage(currentTab.ordinal)
                            }
                        },
                        text = {Text(text = currentTab.title)}
                    )
                }
            }
            HorizontalPager(
                state = pagerState,
                modifier = Modifier
                    .fillMaxWidth()
                    .weight(1f)
            ) {
                Box(
                    modifier = Modifier.fillMaxSize(),
                    contentAlignment = Alignment.Center
                ){
                    Text(text = MyTab.values().get(selectedTabIndex.value).title)
                }
            }
        }
    }
}