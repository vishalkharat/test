package com.example.jet2assignment.view.activity.ui.home

import android.util.Log
import android.view.View
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.jet2assignment.data.model.Item
import com.example.jet2assignment.utils.getItemDashString
import com.example.jet2assignment.utils.getItemStatusDrawable
import kotlinx.android.synthetic.main.item.view.*
import java.util.logging.Logger

class HomeItemViewHolder(private val view: View): RecyclerView.ViewHolder(view) {
	fun bind(item: Item){
		view.txt_user_name.text = item.content
//		view.txt_item_status.text = item.status
//		view.imv_item_status
//			.setImageDrawable(view.context
//				.getDrawable(getItemStatusDrawable(
//					item.status)))
		view.txt_user_designation.text = item.content
//		view.txt_item_dash.text = getItemDashString(
//			status = item.status,
//			species = item.species)

//		System.out.println(item.media);
		Glide.with(view)
			.load("https://s3.amazonaws.com/uifaces/faces/twitter/illyzoren/128.jpg")
			.into(view.imv_item)

		Glide.with(view)
			.load("https://s3.amazonaws.com/uifaces/faces/twitter/ryuchi311/128.jpg")
			.into(view.imv_article_image)
	}
}