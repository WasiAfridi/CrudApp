package com.swistechnologies.crudapp

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import com.swistechnologies.crudapp.databinding.ItemLayoutBinding
import com.swistechnologies.crudapp.dbClasses.Subscriber

class RecyclerViewAdaptor(private val subscribersList: List<Subscriber>,
                          private val clickListener: (Subscriber) -> Unit): RecyclerView.Adapter<MyViewHolder>()
{
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        val binding : ItemLayoutBinding = DataBindingUtil.inflate(layoutInflater, R.layout.item_layout, parent, false)
        return MyViewHolder(binding, clickListener)
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        holder.bind(subscribersList[position], clickListener)
    }

    override fun getItemCount(): Int {
        return subscribersList.size
    }
}

class MyViewHolder (val binding: ItemLayoutBinding, clickListener: (Subscriber) -> Unit): RecyclerView.ViewHolder(binding.root){

    fun bind(subscriber: Subscriber, clickListener: (Subscriber) -> Unit){
        binding.txtSubName.text = subscriber.name
        binding.txtSubEmail.text = subscriber.email
        binding.listItemLayout.setOnClickListener {
            clickListener(subscriber)
        }
    }

}