package com.example.mazadytask.util


enum class RequestState(val value: String) {
    IDLE("idle"),
    SUCCESS("success"),
    ERROR("error"),
    LOADING("loading")
}