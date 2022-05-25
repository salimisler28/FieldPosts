package com.salimisler.fieldposts.presantation.ui.screens.posts.adapter

import android.view.View
import android.widget.ImageView
import android.widget.TextView
import com.airbnb.epoxy.EpoxyAttribute
import com.airbnb.epoxy.EpoxyHolder
import com.airbnb.epoxy.EpoxyModelClass
import com.airbnb.epoxy.EpoxyModelWithHolder
import com.salimisler.fieldposts.R
import com.salimisler.fieldposts.domain.model.PostUiModel

@EpoxyModelClass
abstract class PostItemModel : EpoxyModelWithHolder<PostItemModel.ViewHolder>() {
    override fun getDefaultLayout(): Int {
        return R.layout.item_post
    }

    @EpoxyAttribute
    var item: PostUiModel? = null

    @EpoxyAttribute
    var onItemClickListener: ((PostUiModel) -> Unit)? = null

    @EpoxyAttribute
    var onFavClickListener: ((PostUiModel) -> Unit)? = null

    override fun bind(holder: ViewHolder) {
        super.bind(holder)
        holder.apply {
            tvTitle.text = item?.title
            tvBody.text = item?.body
        }
    }

    class ViewHolder : EpoxyHolder() {
        lateinit var tvTitle: TextView
        lateinit var tvBody: TextView
        lateinit var ivFavToggle: ImageView

        override fun bindView(itemView: View) {
            tvTitle = itemView.findViewById(R.id.tv_title)
            tvBody = itemView.findViewById(R.id.tv_body)
            ivFavToggle = itemView.findViewById(R.id.iv_fav_toggle)
        }
    }

    private fun getFavToggleImageResource(isFav: Boolean?): Int {
        return if (isFav == true) {
            R.drawable.ic_heard_filled
        } else {
            R.drawable.ic_heard_unfilled
        }
    }

}