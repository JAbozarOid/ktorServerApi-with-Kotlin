package com.example.androidkotlinktorapi.kotlin.repo

import java.lang.IllegalArgumentException
import java.util.concurrent.CopyOnWriteArraySet
import java.util.concurrent.atomic.AtomicInteger

// store all of our state
object PersonRepo {
    private val idCounter = AtomicInteger() // we use this method for handling multiple time adding
    private val persons =
        CopyOnWriteArraySet<Person>() // because the application is a asyncronous -> copy multiple instances

    // insert person to database
    fun add(p: Person): Person {
        if (persons.contains(p)) {
            return persons.find {
                it == p
            }!! // !! this means if there is p in the list return true else return false
        }
        p.id = idCounter.incrementAndGet()
        persons.add(p)
        return p
    }

    // id is returned from database is string
    fun get(id: String): Person =
        persons.find { it.id.toString() == id } ?: throw IllegalArgumentException("No entity found for $id")

    fun get(id: Int): Person = get(id.toString())

    // this function return all the persons in list
    fun getAll(): List<Person> = persons.toList()

    fun remove(p: Person) {
        if (!persons.contains(p)) {
            throw IllegalArgumentException("Person not stored in repo.")
        }
        persons.remove(p)
    }

    fun remove(id: String): Boolean = persons.remove(get(id))
    fun remove(id: Int): Boolean = persons.remove(get(id))

    fun clear(): Unit = persons.clear()
}