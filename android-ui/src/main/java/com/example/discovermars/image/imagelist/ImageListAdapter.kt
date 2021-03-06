package com.example.discovermars.image.imagelist

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.lifecycle.MutableLiveData
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import coil.api.load
import com.example.discovermars.R
import com.example.domain.image.model.Image
import kotlinx.android.synthetic.main.item_image.view.*


class ImageListAdapter(val event: MutableLiveData<ImageListEvent> = MutableLiveData()) :
    ListAdapter<Image, ImageListAdapter.ImageViewHolder>
        (ImageDiffUtilCallback()) {

    class ImageViewHolder(root: View) : RecyclerView.ViewHolder(root) {
        var content: TextView = root.lbl_message
        var date: TextView = root.lbl_date_and_time
        var image: ImageView = root.imv_list_item_icon
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ImageViewHolder {
        val inflater = LayoutInflater.from(parent.context)

        return ImageViewHolder(
            inflater.inflate(R.layout.item_image, parent, false)
        )
    }

    override fun onBindViewHolder(holder: ImageViewHolder, position: Int) {
        getItem(position).let { image ->
            holder.content.text = image.contents
            holder.date.text = image.creationDate
            holder.image.load(image.imageUrl.replace("http:", "https:"))
            //  holder.image.load("https://mars.nasa.gov/msl-raw-images/proj/msl/redops/ods/surface/sol/01004/opgs/edr/fcam/FLB_486615455EDR_F0481570FHAZ00323M_.jpg")
            holder.itemView.setOnClickListener {
                event.value = ImageListEvent.OnImageItemClick(position, image.id)
            }
        }
    }

}