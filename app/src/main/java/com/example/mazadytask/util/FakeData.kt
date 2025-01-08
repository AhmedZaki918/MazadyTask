package com.example.mazadytask.util

import com.example.mazadytask.data.model.OptionType


fun carsDummy(): List<OptionType> {
    val data = ArrayList<OptionType>()
    data.apply {
        add(OptionType("BMW"))
        add(OptionType("Ford"))
        add(OptionType("Opel"))
        add(OptionType("Renault"))
    }
    return data
}

fun mobileDummy(): List<OptionType> {
    val data = ArrayList<OptionType>()
    data.apply {
        add(OptionType("Samsung"))
        add(OptionType("Oppo"))
        add(OptionType("Apple"))
        add(OptionType("Nokia"))
    }
    return data
}

fun computerDummy(): List<OptionType> {
    val data = ArrayList<OptionType>()
    data.apply {
        add(OptionType("Dell"))
        add(OptionType("Lenovo"))
        add(OptionType("HP"))
        add(OptionType("Samsung"))
        add(OptionType("MSI"))
    }
    return data
}

fun mainTypeDummy(): List<OptionType> {
    val data = ArrayList<OptionType>()
    data.apply {
        add(OptionType("cars"))
        add(OptionType("computer"))
        add(OptionType("mobile"))
    }
    return data
}