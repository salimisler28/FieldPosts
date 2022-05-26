package com.salimisler.fieldposts.presantation.ui.screens.postdetail

import android.annotation.SuppressLint
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Card
import androidx.compose.material.CircularProgressIndicator
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.ComposeView
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import com.salimisler.fieldposts.R
import com.salimisler.fieldposts.domain.model.CommentUiModel
import com.salimisler.fieldposts.domain.model.PostDetailUiModel
import com.salimisler.fieldposts.domain.model.PostUiModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class PostDetailFragment : Fragment() {
    val viewModel: PostDetailViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        return ComposeView(requireContext()).apply {
            setContent {
                Screen()
            }
        }
    }

    @Composable
    private fun Screen() {
        val loading = viewModel.baseLoadingSF.collectAsState().value
        val post = viewModel.postDetailSF.collectAsState().value

        LazyColumn {
            if (loading) {
                item { LoadingScreen() }
            } else {
                item { PostItem(post!!.postUiModel) }
                item { Spacer(modifier = Modifier.size(20.dp)) }
                post?.comments?.forEach {
                    item { CommentItem(it) }
                }
            }
        }
    }

    @Composable
    private fun CommentItem(commentUiModel: CommentUiModel) {
        Card(
            modifier = Modifier
                .fillMaxWidth()
                .padding(10.dp),
            elevation = 4.dp,
            shape = RoundedCornerShape(10.dp)
        ) {

            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .background(color = Color.Cyan)
                    .padding(10.dp)
            ) {
                Text(text = commentUiModel.name)
                Spacer(modifier = Modifier.size(20.dp))
                Text(text = commentUiModel.body)
            }
        }
    }

    @SuppressLint("RememberReturnType")
    @Composable
    private fun PostItem(post: PostUiModel) {
        val favImage = if (post.isFav) R.drawable.ic_heard_filled else R.drawable.ic_heard_unfilled

        Card(
            modifier = Modifier
                .fillMaxWidth()
                .padding(10.dp),
            elevation = 4.dp,
            shape = RoundedCornerShape(5.dp)
        ) {
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(10.dp)
            ) {
                Row(
                    horizontalArrangement = Arrangement.SpaceBetween,
                    modifier = Modifier.fillMaxWidth()
                ) {
                    Text(text = post.title)
                    Image(
                        painter = painterResource(id = favImage),
                        contentDescription = null,
                        modifier = Modifier.clickable {
                            viewModel.toggleFav(post)
                        }
                    )
                }
                Text(text = post.body)
            }
        }
    }

    @Composable
    private fun LoadingScreen() {
        Column(
            modifier = Modifier.fillMaxSize(),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            CircularProgressIndicator()
        }
    }
}