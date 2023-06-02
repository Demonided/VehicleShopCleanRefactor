package com.example.vehicle_shop_clean.domain.api

sealed interface ApiResponse<T> {
    data class Success<T>(val data: T) : ApiResponse<T>
    data class Error<T>(val message: String) : ApiResponse<T>
}