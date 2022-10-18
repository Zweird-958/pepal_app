package com.example.pepalapp.pages

sealed class Routes(val route: String) {
    object Login : Routes("Login")
    object Marks : Routes("Marks")
    object Account : Routes("Account")
    object Calendar : Routes("Calendar")
    object FullCalendar : Routes("FullCalendar")
    object Works : Routes("Works")
    object Validate : Routes("Validate")
}