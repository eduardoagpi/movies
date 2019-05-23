package com.cesaraguirre.movies.data.room.mapper

//Maper to mapp domain objects to room objects and room objects to domain objects
abstract class BaseDataMapper<RE, DE> {

    abstract fun mapToRoom(input: DE): RE
    abstract fun mapToDomain(input: RE): DE

    fun mapToDomain(input: List<RE>): List<DE> {
        val output = mutableListOf<DE>()
        input.forEach {
            output.add(
                    mapToDomain(it)
            )
        }
        return output
    }

    fun mapToRoom(input: List<DE>): List<RE> {
        val output = mutableListOf<RE>()
        input.forEach {
            output.add(
                    mapToRoom(it)
            )
        }
        return output
    }

}