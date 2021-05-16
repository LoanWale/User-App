package com.loanwalle.loanwallecollection.Model

class TodayCollection(var name : String, var numbr : String) {
    object Coll{
        val todaycollection = listOf<TodayCollection>(
                TodayCollection("Deepak","0123456778"),
                TodayCollection("Deepak","1234567890"),
                TodayCollection("Deepak","9876543212"),
                TodayCollection("Deepak","5678904321"),
                TodayCollection("Deepak","5678904321"),
                TodayCollection("Deepak","5678904321"),
                TodayCollection("Deepak","5678904321"),
                TodayCollection("Deepak","5678904321"),
                TodayCollection("Deepak","5678904321")
        )
    }
}