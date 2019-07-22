package com.example.androidkotlinktorapi.kotlin.repo

// data model class same as pjo model class in java -> we want to save person object in this class
data class Person(val name: String, val age: Int) {

    var id: Int? = null // id is for get data from database -> automatically increment -> by default is equal to null
}