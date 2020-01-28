package com.example.newsapikotlin.api

import com.example.newsapikotlin.model.ArticleResult
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Header
import retrofit2.http.Headers

interface ArticleInterface{

    @Headers("X-Api-Key: 724fd2355db2474bbdaa401a0dcd39e6")
    @GET("v2/top-headlines?country=us")
    fun getArticles(): Call<ArticleResult>
}