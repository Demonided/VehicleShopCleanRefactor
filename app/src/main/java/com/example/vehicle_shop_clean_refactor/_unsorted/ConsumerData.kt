package com.example.vehicle_shop_clean_refactor._unsorted

sealed interface ConsumerData<T> {
    data class Data<T>(val value: T) : ConsumerData<T>
    data class Error<T>(val message: String) : ConsumerData<T>
}