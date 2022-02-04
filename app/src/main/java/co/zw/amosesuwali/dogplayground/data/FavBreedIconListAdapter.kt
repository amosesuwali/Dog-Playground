package co.zw.amosesuwali.dogplayground.data

import android.content.Context
import android.content.res.Resources
import android.graphics.Color
import android.graphics.drawable.Drawable
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import androidx.core.content.res.ResourcesCompat
import androidx.lifecycle.MutableLiveData
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import co.zw.amosesuwali.dogplayground.R
import co.zw.amosesuwali.dogplayground.databinding.BasicBreedDetailItemBinding
import co.zw.amosesuwali.dogplayground.databinding.BasicBreedIconItemBinding
import co.zw.amosesuwali.dogplayground.models.BreedDetailModel

/**
* This class implements a [RecyclerView] [ListAdapter] which uses Data Binding to present [List]
* data, including computing diffs between lists.
*/

class FavBreedIconListAdapter(): ListAdapter<BreedDetailModel, FavBreedIconListAdapter.BreedDetailViewHolder>(DiffCallback) {

     var selectedBreeds = MutableLiveData<MutableList<String>> ()
     var selectedBreedsCount = MutableLiveData<String> ("0")
    class BreedDetailViewHolder(
    private var binding: BasicBreedIconItemBinding
    ) : RecyclerView.ViewHolder(binding.root) {
        fun bind(breed: BreedDetailModel) {
            binding.breed = breed
            // This is important, because it forces the data binding to execute immediately,
            // which allows the RecyclerView to make the correct view size measurements
            binding.executePendingBindings()

        }
    }

    companion object DiffCallback : DiffUtil.ItemCallback<BreedDetailModel>() {
        override fun areItemsTheSame(oldItem:BreedDetailModel, newItem: BreedDetailModel): Boolean {
            return oldItem.breedName == newItem.breedName
        }

        override fun areContentsTheSame(oldItem: BreedDetailModel, newItem: BreedDetailModel): Boolean {
            return oldItem.breedName == newItem.breedName
        }
    }
    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): BreedDetailViewHolder {
        selectedBreeds.value= mutableListOf()
        return BreedDetailViewHolder(
            BasicBreedIconItemBinding.inflate(LayoutInflater.from(parent.context))
        )
    }



    override fun onBindViewHolder(holder: BreedDetailViewHolder, position: Int) {
        val breedItem = getItem(holder.adapterPosition)
        Log.d("holder",holder.toString())
        Log.d("breedItem",breedItem.toString())
        Log.d("holder.adapterPosition",holder.adapterPosition.toString())

        holder.itemView.setOnClickListener {
            val selectedBreedDecoration = context!!.resources.getDrawable(R.drawable.breed_selected_item_border,context!!.theme)
            val unselectedBreedDecoration = context!!.resources.getDrawable(R.drawable.breed_item_border,context!!.theme)

            if (selectedBreeds.value?.contains(breedItem.breedName) == false) {
                selectedBreeds.value?.add(breedItem.breedName)

                it.background=selectedBreedDecoration
                it.visibility = View.VISIBLE
            }else{
                selectedBreeds.value?.remove(breedItem.breedName)
                it.background=unselectedBreedDecoration
            }

            selectedBreedsCount.value =  selectedBreeds.value?.size.toString()
            Log.d("selectedBreedSize",selectedBreeds.value?.size.toString())
        }
        holder.bind(breedItem)

    }


    private var context: Context? = null

    override fun onAttachedToRecyclerView(recyclerView: RecyclerView) {
        super.onAttachedToRecyclerView(recyclerView)
        context = recyclerView.context
    }
}