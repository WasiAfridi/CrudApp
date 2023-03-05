package com.swistechnologies.crudapp

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.swistechnologies.crudapp.databinding.ActivityMainBinding
import com.swistechnologies.crudapp.dbClasses.Subscriber
import com.swistechnologies.crudapp.dbClasses.SubscriberDAO
import com.swistechnologies.crudapp.dbClasses.SubscribersDatabase
import com.swistechnologies.crudapp.dbClasses.SubscribersRepository

class MainActivity : AppCompatActivity() {
    private lateinit var binding : ActivityMainBinding
    private lateinit var crudViewModel: CrudViewModel
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_main)
        val dao: SubscriberDAO = SubscribersDatabase.getInstance(application).subscriberDAO
        val repository = SubscribersRepository(dao)
        val factory = ViewModelFactory(repository)
        crudViewModel = ViewModelProvider(this, factory).get(CrudViewModel::class.java)
        binding.myViewModel = crudViewModel
        binding.lifecycleOwner = this

        initRecyclerView()

    }

    fun initRecyclerView(){
        binding.rvSubscribers.layoutManager = LinearLayoutManager(this)
        displayAllSubscribers()
    }

    fun displayAllSubscribers(){
        crudViewModel.allSubscribers.observe(this, Observer {
            Log.i("MyTag", it.toString())
            binding.rvSubscribers.adapter = RecyclerViewAdaptor(it) { selectedItem: Subscriber -> listItemClicked(selectedItem)}
        })
    }

    fun listItemClicked(subscriber: Subscriber){
        Toast.makeText(this,"Clicked Subscriber is ${subscriber.name}", Toast.LENGTH_LONG).show()
        crudViewModel.initUpdateAndDelete(subscriber)
    }
}