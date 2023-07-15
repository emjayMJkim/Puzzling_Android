package com.puzzling.puzzlingaos.presentation.home.mypage.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.puzzling.puzzlingaos.data.model.response.ResponseMyPageProjectDto
import com.puzzling.puzzlingaos.databinding.ItemMyProjectBinding
import com.puzzling.puzzlingaos.util.ItemDiffCallback
import java.text.SimpleDateFormat
import java.util.*

class MyProjectAdapter() :
    ListAdapter<ResponseMyPageProjectDto, MyProjectAdapter.MyProjectViewHolder>(
        diffCallback,
    ) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyProjectViewHolder {
        val binding: ItemMyProjectBinding =
            ItemMyProjectBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return MyProjectViewHolder(binding)
    }

    override fun onBindViewHolder(holder: MyProjectViewHolder, position: Int) {
        return holder.onBind(currentList[position])
    }

    private var listener: OnItemClickListener? = null

    inner class MyProjectViewHolder(private val binding: ItemMyProjectBinding) :
        RecyclerView.ViewHolder(binding.root) {

        fun onBind(item: ResponseMyPageProjectDto) {
            val now = Date()
            val startDate =
                SimpleDateFormat("yyyy-MM-dd", Locale.getDefault()).parse(item.startDate)
            val timeDiff = (now.time - startDate.time) / (24 * 60 * 60 * 1000)

            with(binding) {
                tvMyPageProjectName.text = item.projectName
                if (timeDiff < 0) {
                    tvMyPageStartDate.text = "D - ${kotlin.math.abs(timeDiff - 1)}"
                } else {
                    tvMyPageStartDate.text = "D + $timeDiff"
                }
            }

            if (bindingAdapterPosition != RecyclerView.NO_POSITION) {
                binding.btnMyPageAllRetrospect.setOnClickListener {
                    listener?.onItemClick(itemView, item, bindingAdapterPosition)
                }
            }
        }
    }

    interface OnItemClickListener {
        fun onItemClick(v: View, data: ResponseMyPageProjectDto, pos: Int)
    }

    fun setOnItemClickListener(listener: OnItemClickListener) {
        this.listener = listener
    }

    companion object {
        private val diffCallback =
            ItemDiffCallback<ResponseMyPageProjectDto>(
                onContentsTheSame = { old, new -> old == new },
                onItemsTheSame = { old, new -> old == new },
            )
    }
}