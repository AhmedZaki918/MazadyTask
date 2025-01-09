package com.example.mazadytask.util


enum class DropDownId(val value: Byte) {
    FIRST(1),
    SECOND(2)
}

enum class MainCategory(val value: String) {
    CARS("cars"),
    COMPUTER("computer"),
    MOBILE("mobile")
}

enum class RequestState(val value : String) {
    IDLE("idle"),
    SUCCESS("success"),
    ERROR("error"),
    LOADING("loading")
}