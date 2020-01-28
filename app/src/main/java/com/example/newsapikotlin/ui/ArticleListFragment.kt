package com.example.newsapikotlin.ui


import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

import com.example.newsapikotlin.R
import com.example.newsapikotlin.model.ArticleResult
import com.example.newsapikotlin.ui.adapter.ArticleListAdapter
import com.example.newsapikotlin.viewmodel.ArticleViewModel
import kotlinx.android.synthetic.main.fragment_article_list.*

/**
 * A simple [Fragment] subclass.
 */
class ArticleListFragment : Fragment() {

    private lateinit var articleListAdapter: ArticleListAdapter
    private lateinit var viewManager: RecyclerView.LayoutManager
    lateinit var articleViewModel: ArticleViewModel


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_article_list, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewManager = LinearLayoutManager(activity)   //content
        articleListAdapter = ArticleListAdapter()
        recyclerView.adapter = articleListAdapter
        recyclerView.layoutManager = viewManager
        observeViewModel()

    }

    fun observeViewModel(){
         articleViewModel = ViewModelProviders
            .of(this)
            .get(ArticleViewModel:: class.java)
        articleViewModel.getResult().observe(
            this, Observer <ArticleResult> { result->

                recyclerView.visibility = View.VISIBLE
                articleListAdapter.updateList(result.articles)

            }
        )
        articleViewModel.getError().observe(
            this, Observer<Boolean>{isError->
                if (isError){
                    txtError.visibility = View.VISIBLE
                    recyclerView.visibility = View.GONE
                } else {
                    txtError.visibility = View.GONE
                }

            }

        )
        articleViewModel.getLoading().observe(
            this, Observer<Boolean> {isLoading ->
                if (isLoading){
                    txtError.visibility = View.GONE
                    recyclerView.visibility =View.GONE
                }

            }
        )
    }

    override fun onResume() {
        super.onResume()
        articleViewModel.loadResults()
    }

}
