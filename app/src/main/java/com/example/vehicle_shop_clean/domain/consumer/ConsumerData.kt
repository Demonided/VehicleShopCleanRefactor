package com.example.vehicle_shop_clean.domain.consumer

sealed interface ConsumerData<T> {
    data class Data<T>(val value: T) : ConsumerData<T>
    data class Error<T>(val message: String) : ConsumerData<T>
}