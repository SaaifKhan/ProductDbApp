package com.saifkhan.completeappdemo.Ui.activity.adapter

import android.text.TextUtils
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.saifkhan.productdbapp.R
import com.saifkhan.productdbapp.data.models.ProductModel
import com.saifkhan.productdbapp.databinding.ItemProductBinding
import com.squareup.picasso.Callback
import com.squareup.picasso.Picasso

class ProductIstAdapter(
   // val onItemClickListener: OnItemClickListener,
    private val itemClick: (ProductModel) -> Unit
) :
    ListAdapter<ProductModel, ProductIstAdapter.ViewHolder>(DiffCallBack()) {

    var lastSelectedPostion = -1

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): ProductIstAdapter.ViewHolder {
        return ViewHolder(ItemProductBinding.inflate(LayoutInflater.from(parent.context)))
    }

    override fun onBindViewHolder(holder: ProductIstAdapter.ViewHolder, position: Int) {
        holder.bind.productModel = getItem(position)

        //clickListener
        holder.itemView.setOnClickListener {
            lastSelectedPostion = position
          //  onItemClickListener.onItemClick(position, getItem(position))
            itemClick(getItem(position))

        }
    }

    class ViewHolder(private var binding: ItemProductBinding) :
        RecyclerView.ViewHolder(binding.root) {
        val bind = binding
    }

    class DiffCallBack : DiffUtil.ItemCallback<ProductModel>() {

        override fun areItemsTheSame(oldItem: ProductModel, newItem: ProductModel): Boolean {
            return oldItem.id == newItem.id
        }

        override fun areContentsTheSame(oldItem: ProductModel, newItem: ProductModel): Boolean {
            return oldItem.id == newItem.id &&
                    oldItem.category == newItem.category &&
                    oldItem.description.equals(newItem.description) &&
                    oldItem.image.equals(newItem.image) &&
                    oldItem.price.equals(newItem.price) &&
                    oldItem.title.equals(newItem.title)
        }



    }

    interface OnItemClickListener {
        fun onItemClick(itemPosition: Int, musicModel: ProductModel)
    }

}