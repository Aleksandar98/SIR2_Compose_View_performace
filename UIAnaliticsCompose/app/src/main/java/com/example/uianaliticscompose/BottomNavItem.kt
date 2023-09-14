package com.example.uianaliticscompose

sealed class BottomNavItem(var title:String, var icon:Int, var screen_route:String){

    object Home : BottomNavItem("Home", R.drawable.baseline_home_24,"home")
    object Traveled: BottomNavItem("Traveled",R.drawable.baseline_airplane_24,"traveled")
    object AddDestination: BottomNavItem("Add destination",R.drawable.baseline_add_24,"add_destination")
    object DetailDestination: BottomNavItem("Detail destination",R.drawable.baseline_add_24,"detail/{selectedDestinationId}")
}
