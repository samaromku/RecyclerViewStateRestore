package ru.appngo.recyclerviewscrollrestoreimport

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import kotlinx.android.extensions.LayoutContainer
import kotlinx.android.synthetic.main.item_user.view.*
import ru.appngo.recyclerviewscrollrestore.R
import ru.appngo.recyclerviewscrollrestore.User

class UsersAdapter : RecyclerView.Adapter<UsersAdapter.UserViewholder>() {

    var data: List<User> = emptyList()
        set(newList) {
            val calculateDiff = DiffUtil.calculateDiff(UserDiffCallback(field, newList))
            calculateDiff.dispatchUpdatesTo(this)
            field = newList
        }

    override fun getItemCount(): Int = data.size

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): UserViewholder {
        return UserViewholder(
                LayoutInflater.from(parent.context)
                        .inflate(R.layout.item_user, parent, false)
        )
    }

    override fun onBindViewHolder(holder: UsersAdapter.UserViewholder, position: Int) {
        holder.bind(data[position])
    }

    inner class UserViewholder(override val containerView: View) : RecyclerView.ViewHolder(containerView),
            LayoutContainer {
        fun bind(item: User) = with(itemView) {
            user_image.setBackgroundColor(item.color)
            user_name.text = item.userName
        }
    }
}

class UserDiffCallback(private val oldList: List<User>, private val newList: List<User>) : DiffUtil.Callback() {
    override fun areItemsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean {
        return oldList[oldItemPosition] == newList[newItemPosition]
    }

    override fun areContentsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean {
        val old = oldList[oldItemPosition]
        val new = newList[newItemPosition]
        return old.id == new.id
    }

    override fun getOldListSize(): Int {
        return oldList.size
    }

    override fun getNewListSize(): Int {
        return newList.size
    }
}
