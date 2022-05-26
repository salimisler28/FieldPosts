package com.salimisler.fieldposts.domain.usecases

import app.cash.turbine.test
import appdb.FavsEntity
import com.google.common.truth.Truth.assertThat
import com.salimisler.fieldposts.core.Resource
import com.salimisler.fieldposts.data.network.dto.CommentDto
import com.salimisler.fieldposts.data.network.dto.PostDto
import com.salimisler.fieldposts.domain.mappers.PostDetailMapper
import com.salimisler.fieldposts.domain.model.PostDetailUiModel
import com.salimisler.fieldposts.domain.model.PostUiModel
import com.salimisler.fieldposts.domain.repositories.JsonPlaceholderRepository
import io.mockk.MockKAnnotations
import io.mockk.coEvery
import io.mockk.every
import io.mockk.impl.annotations.MockK
import kotlinx.coroutines.flow.flowOf
import kotlinx.coroutines.runBlocking
import org.junit.Before
import org.junit.Test

class InitPostDetailUseCaseTest {
    @MockK(relaxed = true)
    lateinit var jsonPlaceholderRepository: JsonPlaceholderRepository

    @MockK(relaxed = true)
    lateinit var postDetailMapper: PostDetailMapper

    lateinit var initPostDetailUseCase: InitPostDetailUseCase

    @Before
    fun before() {
        MockKAnnotations.init(this)
        initPostDetailUseCase = InitPostDetailUseCase(jsonPlaceholderRepository, postDetailMapper)
    }

    @Test
    fun `ded`() = runBlocking {
        // given
        val postId = 1
        val title = ""
        val body = ""
        val comments = listOf<CommentDto>()
        val postDto = PostDto(userId = 1, id = postId, title = title, body = body)
        val favDto = FavsEntity(id = postId.toLong(), title = "", body = "")
        val favList = listOf(favDto)
        val postUiModel = PostUiModel(id = postId, title = title, body = body, isFav = true)

        coEvery { jsonPlaceholderRepository.getPostById(postId) } returns (flowOf(
            Resource.success(postDto)
        ))

        coEvery { jsonPlaceholderRepository.getCommentsByPostId(1) } returns (flowOf(
            Resource.success(comments)
        ))

        coEvery { jsonPlaceholderRepository.listenAllFavs() } returns (flowOf(
            Resource.success(favList)
        ))

        val postDetailUiModel = PostDetailUiModel(postUiModel = postUiModel, comments = listOf())
        every { postDetailMapper.map(PostDetailMapper.ParamsIn(postDto, comments, favList)) } returns (postDetailUiModel)

        // expected
        val out = InitPostDetailUseCase.ParamsOut(postDetailUiModel)

        // result
        val paramsIn = InitPostDetailUseCase.ParamsIn(postId)
        initPostDetailUseCase.invoke(paramsIn).test {
            assertThat(awaitItem()).isEqualTo(Resource.success(out))
            awaitComplete()
        }

    }
}