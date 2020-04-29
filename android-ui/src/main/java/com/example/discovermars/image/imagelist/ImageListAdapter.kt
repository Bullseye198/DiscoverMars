package com.example.discovermars.image.imagelist

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.ListAdapter
import android.widget.TextView
import androidx.lifecycle.MutableLiveData
import androidx.recyclerview.widget.RecyclerView
import com.example.discovermars.R
import com.example.discovermars.image.ImageListViewModel
import kotlinx.android.synthetic.main.item_image.view.*
import org.w3c.dom.Text

//Note list evet - Sealed class which represent different events which
//can occur in the front end of the app.
//Mutable live data - something that can be observed.
class ImageListAdapter: RecyclerView.Adapter<CustomViewHolder>() {

    class ImageViewHolder(root: View): RecyclerView.ViewHolder(root) {
        var content: TextView = root.lbl_message
        var date: TextView = root.lbl_date_and_time
    }

    override fun getItemCount(): Int {
        return 3
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CustomViewHolder {
        //create a view
        val layoutInflater = LayoutInflater.from(parent.context)
        val cellForRow = layoutInflater.inflate(R.layout.item_image, parent, false)
        return CustomViewHolder(cellForRow)
    }

    override fun onBindViewHolder(holder: CustomViewHolder, position: Int) {

    }
}

class CustomViewHolder(v: View): RecyclerView.ViewHolder(v) {

}