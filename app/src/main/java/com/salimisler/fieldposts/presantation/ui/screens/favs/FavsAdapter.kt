package com.salimisler.fieldposts.presantation.ui.screens.favs

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.AsyncListDiffer
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.salimisler.fieldposts.R
import com.salimisler.fieldposts.databinding.ItemPostBinding
import com.salimisler.fieldposts.domain.model.PostUiModel

class FavsAdapter : RecyclerView.Adapter<FavsAdapter.ViewHolder>() {

    private val differCallback = object : DiffUtil.ItemCallback<PostUiModel>() {
        override fun areItemsTheSame(oldItem: PostUiModel, newItem: PostUiModel): Boolean {
            return oldItem.isFav == newItem.isFav
        }

        override fun areContentsTheSame(oldItem: PostUiModel, newItem: PostUiModel): Boolean {
            return oldItem == newItem
        }
    }

    private val differ = AsyncListDiffer(this, differCallback)

    lateinit var onItemClickListener: (PostUiModel) -> Unit

    fun submitList(data: List<PostUiModel>) {
        differ.submitList(data)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val item = ItemPostBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ViewHolder(item)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val item = differ.currentList[position]
        holder.apply {
            cvPost.setOnClickListener { onItemClickListener.invoke(item) }
            tvTitle.text = item.title
            tvBody.text = item.body
            ivFavToggle.setImageResource(R.drawable.ic_heard_filled)
        }
    }

    override fun getItemCount(): Int = differ.currentList.size

    inner class ViewHolder(item: ItemPostBinding) : RecyclerView.ViewHolder(item.root) {
        val cvPost = item.cvPost
        val tvTitle = item.tvTitle
        val tvBody = item.tvBody
        val ivFavToggle = item.ivFavToggle
    }

}