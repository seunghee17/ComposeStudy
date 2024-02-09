package com.example.bottomnavigation

import androidx.compose.ui.graphics.vector.ImageVector
data class NavItem(
    val label:String,
    val icon_unfill: Int,
    val route: String
)

val listOfNavItem : List<NavItem> = listOf(
    NavItem(
        label = "홈",
        icon_unfill = R.drawable.ic_home,
        route = Screens.HomeScreen.name
    ),
    NavItem(
        label = "커뮤니티",
        icon_unfill = R.drawable.ic_community,
        route = Screens.CommunityScreen.name
    ),
    NavItem(
        label = "가족",
        icon_unfill = R.drawable.ic_family,
        route = Screens.FamilyScreen.name
    ),
    NavItem(
        label = "후원",
        icon_unfill = R.drawable.ic_funding,
        route = Screens.FundingScreen.name
    ),
    NavItem(
        label = "마이페이지",
        icon_unfill = R.drawable.ic_my,
        route = Screens.MypageScreen.name
    ),
)