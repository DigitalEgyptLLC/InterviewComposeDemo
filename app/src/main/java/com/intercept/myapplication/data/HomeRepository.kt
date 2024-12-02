package com.intercept.myapplication.data

import javax.inject.Inject

class HomeRepository @Inject constructor(private val homeService: HomeService) {
    suspend fun fetchHomeData(): Result<List<HomeDataModel>> {
        return try {
            val response = homeService.getHome()
            Result.success(response)
        } catch (e: Exception) {
            Result.failure(e)
        }
    }
}