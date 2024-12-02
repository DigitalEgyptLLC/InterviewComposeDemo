package com.intercept.myapplication.ui.home

sealed class HomeNavigationAction {
    data object NavigateToDetails : HomeNavigationAction()
}