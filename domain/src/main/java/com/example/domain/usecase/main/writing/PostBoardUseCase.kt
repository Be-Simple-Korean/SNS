package com.example.domain.usecase.main.writing

import com.example.domain.model.Image

interface PostBoardUseCase {

    operator fun invoke(
        title: String,
        content: String,
        images: List<Image>
    )
}