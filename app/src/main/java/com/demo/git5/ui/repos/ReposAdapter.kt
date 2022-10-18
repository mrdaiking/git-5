package com.demo.git5.ui.repos

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.demo.git5.R
import com.demo.git5.databinding.ItemRowRepoBinding
import com.demo.git5.domain.model.UserRepo
import com.demo.git5.event.EventBus
import com.demo.git5.event.ShowURLEvent

/**
 * Repositories adapter
 */
class ReposAdapter(private val mContext: Context) :
    RecyclerView.Adapter<ReposAdapter.ViewHolder>() {

    private var items = mutableListOf<UserRepo>()

    inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        private val binding: ItemRowRepoBinding = ItemRowRepoBinding.bind(itemView)

        fun bind(data: UserRepo) {
            binding.apply {
                txtRepoName.text = data.name
                txtRepoDes.text = data.description?: mContext.resources.getText(R.string.no_des)
                txtRepoLang.text = data.language
                txtRepoStar.text = data.star.toString()
            }
            with(itemView) {
                setOnClickListener {
                    EventBus.notify(ShowURLEvent(data.url?:"")) // Notify event contained url
                }
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ReposAdapter.ViewHolder {
        return ViewHolder(
            LayoutInflater.from(mContext).inflate(R.layout.item_row_repo, parent, false)
        )
    }

    fun setItems(data: MutableList<UserRepo>) {
        this.items = data
        notifyDataSetChanged()
    }

    override fun getItemCount(): Int = items.size

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(items[position])
    }
}
