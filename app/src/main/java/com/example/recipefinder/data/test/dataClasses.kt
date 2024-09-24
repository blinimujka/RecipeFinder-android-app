package com.example.recipefinder.data.test

import androidx.room.Embedded
import androidx.room.Entity
import androidx.room.PrimaryKey
import androidx.room.Relation

@Entity
data class team(
    @PrimaryKey(autoGenerate = false)
    val teamId:Long,
    var Name: String
)

@Entity
data class player(
    @PrimaryKey(autoGenerate = false)
    val playerId:Long,
    var Name: String,
    var Number:Long,
    var teamId:Long
)

@Entity
data class TeamsWithPlayers(
    @Embedded val team: team,
    @Relation(
        parentColumn = "teamId",
        entityColumn = "teamId"
    )
    val players: List<player>
)