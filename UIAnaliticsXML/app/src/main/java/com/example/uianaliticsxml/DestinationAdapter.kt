package com.example.uianaliticsxml

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.core.view.isVisible
import androidx.recyclerview.widget.RecyclerView


class DestinationAdapter(destinationList: List<Destination>,private val onDestinationClicked: DestinationClickListener) : RecyclerView.Adapter<DestinationAdapter.ViewHolder>() {

    private var destinationList: List<Destination>


    init {
        this.destinationList = destinationList
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val context = parent.context
        val view = LayoutInflater.from(context).inflate(R.layout.destiation_item, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.titleTxt.text = destinationList[position].name
        holder.shortDescTxt.text = destinationList[position].shortDescription
        holder.destinationImg.setImageResource(destinationList[position].imageRes)

        holder.imageButton.isVisible = destinationList[position].isVisited

        holder.itemView.setOnClickListener {
            onDestinationClicked.onDestinationClicked(destinationList[position])
        }
    }

    override fun getItemCount(): Int {
        return destinationList.size
    }

    fun setNewDataList(list: List<Destination>) {
        destinationList = list
        notifyDataSetChanged()
    }

    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val titleTxt: TextView
        val shortDescTxt: TextView
        val destinationImg: ImageView
        val imageButton: ImageView
        init {
            titleTxt = itemView.findViewById(R.id.titleTxt)
            shortDescTxt = itemView.findViewById(R.id.shortDescTxt)
            destinationImg = itemView.findViewById(R.id.destinationImg)
            imageButton = itemView.findViewById(R.id.destinationVisitedImg)
        }
    }

    interface DestinationClickListener{
        fun onDestinationClicked(destination: Destination)
    }
}