package com.example.data.model

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.example.domain.model.Board
import kotlinx.serialization.Serializable
import kotlinx.serialization.json.Json

@Entity
@Serializable
data class BoardDTO(
    @PrimaryKey val id: Long,
    val title: String,
    val content: String,
    val createdAt: String,
    val updatedAt: String,
    val createUserId: Long,
    val createUserName: String,
    val createUserProfileFilePath: String? = null,
    val commentList: List<CommentDTO>
)

fun BoardDTO.toDomainModel(): Board {
    if (content.startsWith("{")) {
        val contentParam = Json.decodeFromString<ContentParam>(content)
        return Board(
            userId = this.createUserId,
            id = this.id,
            title = this.title,
            content = contentParam.text,
            images = contentParam.images,
            username = this.createUserName,
            profileImageUrl = this.createUserProfileFilePath ?: "",
            comments = this.commentList.map { it.toDomainModel() }
        )
    } else {
        return Board(
            userId = this.createUserId,
            id = this.id,
            title = this.title,
            content = this.content,
            images = emptyList(),
            username = this.createUserName,
            profileImageUrl = this.createUserProfileFilePath ?: "",
            comments = this.commentList.map { it.toDomainModel() }
        )
    }
}