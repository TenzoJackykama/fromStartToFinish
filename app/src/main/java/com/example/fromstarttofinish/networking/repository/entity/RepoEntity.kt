package com.example.myapplication.github.repository.entity

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.example.fromstarttofinish.usecases.model.FakeDataApiModel

@Entity(tableName = "repo_table")
data class RepoEntity(
    @PrimaryKey val id: Int,
    @ColumnInfo(name = "name") val title: String
)

fun RepoEntity.toModel(): FakeDataApiModel {
    return FakeDataApiModel(id = this.id, title = this.title)
}

fun FakeDataApiModel.toEntity(): RepoEntity {
    return RepoEntity(id = this.id, title = this.title)
}