package com.salimisler.fieldposts.presantation.ui.screens.posts.adapter

import android.view.View
import android.widget.ImageView
import android.widget.TextView
import androidx.cardview.widget.CardView
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

            cvPost.setOnClickListener {
                item?.let { onItemClickListener?.invoke(it) }
            }

            ivFavToggle.setOnClickListener {
                item?.let { onFavClickListener?.invoke(it) }
            }

            ivFavToggle.setFavImageResource(item?.isFav)
        }
    }

    class ViewHolder : EpoxyHolder() {
        lateinit var cvPost: CardView
        lateinit var tvTitle: TextView
        lateinit var tvBody: TextView
        lateinit var ivFavToggle: ImageView

        override fun bindView(itemView: View) {
            cvPost = itemView.findViewById(R.id.cv_post)
            tvTitle = itemView.findViewById(R.id.tv_title)
            tvBody = itemView.findViewById(R.id.tv_body)
            ivFavToggle = itemView.findViewById(R.id.iv_fav_toggle)
        }
    }

    private fun ImageView.setFavImageResource(isFav: Boolean?) {
        if (isFav == true) {
            this.setImageResource(R.drawable.ic_heard_filled)
        } else {
            this.setImageResource(R.drawable.ic_heard_unfilled)
        }
    }

}