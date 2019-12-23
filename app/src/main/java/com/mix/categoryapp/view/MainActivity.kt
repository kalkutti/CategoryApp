package com.mix.categoryapp.view

import android.content.res.Configuration
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.DefaultItemAnimator
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import com.mix.categoryapp.R
import com.mix.categoryapp.model.CategoryDetailEntity
import com.mix.categoryapp.viewmodel.BaseViewModel
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    var baseViewModel: BaseViewModel? = null
    var mCategAdapter: CategAdapter? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        baseViewModel = ViewModelProviders.of(this).get(BaseViewModel::class.java)
        getPopularBlog()
        //swiperefresh.setOnRefreshListener { getPopularBlog() }
    }
    fun getPopularBlog() {
        //swiperefresh.setRefreshing(false)
        baseViewModel!!.allCateg.observe(this, Observer {  categList ->
            prepareRecyclerView(categList)
        })

    }

    private fun prepareRecyclerView(categList: MutableList<CategoryDetailEntity>?) {

        mCategAdapter = CategAdapter(categList)
        if (this.resources.configuration.orientation == Configuration.ORIENTATION_PORTRAIT) {
            blogRecyclerView.setLayoutManager(LinearLayoutManager(this))
        } else {
            blogRecyclerView.setLayoutManager(GridLayoutManager(this, 4))
        }
        blogRecyclerView.setItemAnimator(DefaultItemAnimator())
        blogRecyclerView.setAdapter(mCategAdapter)

    }
}
