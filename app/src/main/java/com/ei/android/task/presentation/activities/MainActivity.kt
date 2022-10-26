package com.ei.android.task.presentation.activities

import android.os.Bundle
import android.widget.ProgressBar
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.isVisible
import androidx.lifecycle.lifecycleScope
import androidx.paging.CombinedLoadStates
import androidx.paging.LoadState
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.RecyclerView
import com.ei.android.task.PhotosApplication
import com.ei.android.task.R
import com.ei.android.task.presentation.PhotosViewModel
import com.ei.android.task.presentation.adapter.LoaderStateAdapter
import com.ei.android.task.presentation.adapter.PhotosAdapter

class MainActivity : AppCompatActivity() {

    private lateinit var recyclerView: RecyclerView

    private lateinit var adapter: PhotosAdapter
    private lateinit var viewModel: PhotosViewModel
    private lateinit var progressBar: ProgressBar

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        viewModel = (application as PhotosApplication).viewModel
        initRecyclerView()
        observeViewModel()


    }

    private fun initRecyclerView() {
        recyclerView = findViewById(R.id.recyclerVeiw)
        progressBar = findViewById(R.id.progress)
        val decoration =
            DividerItemDecoration(applicationContext, DividerItemDecoration.VERTICAL)
        recyclerView.addItemDecoration(decoration)
        adapter = PhotosAdapter(object : PhotosAdapter.OnImageClickListener {
            override fun onClickListener(url: String) {
                val intent = ImageActivity.newIntent(this@MainActivity, url)
                startActivity(intent)
            }
        })
        adapter.withLoadStateHeaderAndFooter(
            header = LoaderStateAdapter(),
            footer = LoaderStateAdapter()
        )
        recyclerView.adapter = adapter

        adapter.addLoadStateListener { stata: CombinedLoadStates ->
            val refreshState = stata.refresh
            progressBar.isVisible = refreshState == LoadState.Loading
        }

    }

    private fun observeViewModel() {
        lifecycleScope.launchWhenCreated {
            viewModel.fetchPhotos().observe(this@MainActivity, {
                adapter.submitData(lifecycle, it)
            })
        }
    }
}