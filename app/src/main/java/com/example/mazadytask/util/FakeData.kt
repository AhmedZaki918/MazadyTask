package com.example.mazadytask.util

import com.example.mazadytask.data.model.categories.OptionType


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

fun carsTypesDummy(): List<OptionType> {
    val data = ArrayList<OptionType>()
    data.apply {
        add(OptionType("Sedan"))
        add(OptionType("Hatchback"))
        add(OptionType("SUV"))
        add(OptionType("Coupe"))
        add(OptionType("Pickup Truck"))
        add(OptionType("Van"))
    }
    return data
}

fun computerTypesDummy(): List<OptionType> {
    val data = ArrayList<OptionType>()
    data.apply {
        add(OptionType("Desktop"))
        add(OptionType("Laptop"))
    }
    return data
}

fun mobileTypesDummy(): List<OptionType> {
    val data = ArrayList<OptionType>()
    data.apply {
        add(OptionType("Tablet"))
        add(OptionType("Phone"))
    }
    return data
}

