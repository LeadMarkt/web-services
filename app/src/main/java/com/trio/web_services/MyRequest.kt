package com.trio.web_services

import android.content.Context
import android.graphics.Bitmap
import android.util.LruCache
import android.view.textclassifier.TextLinks
import com.android.volley.Request
import com.android.volley.RequestQueue
import com.android.volley.Response
import com.android.volley.toolbox.ImageLoader
import com.android.volley.toolbox.JsonObjectRequest
import com.android.volley.toolbox.StringRequest
import com.android.volley.toolbox.Volley

class MyRequest {
    private var mRequestQueue:RequestQueue?=null
    private var context:Context?=null
    private var iVolley:IVolley?=null
    var imageLoader: ImageLoader?=null
        get


    val requestQueue: RequestQueue
        get() {
            if(mRequestQueue == null)
                mRequestQueue = Volley.newRequestQueue(context!!.applicationContext)
            return mRequestQueue!!

        }

    private constructor(context: Context,iVolley: IVolley){

        this.context = context
        this.iVolley=iVolley
        mRequestQueue = requestQueue
        this.imageLoader = ImageLoader(mRequestQueue,object : ImageLoader.ImageCache{
            private val mCache = LruCache<String,Bitmap>(10)
            override fun getBitmap(url: String?): Bitmap {
                return mCache.get(url)
            }

            override fun putBitmap(url: String?, bitmap: Bitmap?) {
                mCache.put(url,bitmap)
            }


    })
    }

    private constructor(context: Context){

        this.context = context
        mRequestQueue = requestQueue
        this.imageLoader = ImageLoader(mRequestQueue,object : ImageLoader.ImageCache{
            private val mCache = LruCache<String,Bitmap>(10)
            override fun getBitmap(url: String?): Bitmap {
                return mCache.get(url)
            }

            override fun putBitmap(url: String?, bitmap: Bitmap?) {
                mCache.put(url,bitmap)
            }


        })
    }

    fun<T> addToRequestQueue(req: Request<T>){
        requestQueue.add(req)
    }

    fun getRequest(url:String)
    {
        val getRequest = JsonObjectRequest(com.android.volley.Request.Method.GET,url,null,Response.Listener {
            response -> iVolley!!.onResponse(response.toString())
        },Response.ErrorListener { error -> iVolley!!.onResponse(error.message!!) })
        addToRequestQueue(getRequest)
    }

    //POST method with parameters

    fun postRequest(url:String)
    {
        val postRequest = object: StringRequest(Request.Method.POST,url,
            Response.Listener { response -> iVolley!!.onResponse(response.toString())},
            Response.ErrorListener { error -> iVolley!!.onResponse(error.message!!) })
        {
            override fun getParams(): MutableMap<String, String> {
                val params = HashMap<String,String>()
                params["name"] = "Heath Ledger"
                params["value"] = "This is value posted from android app"
                return params

            }
        }
    }

    fun putRequest(url:String)
    {
        val postRequest = object: StringRequest(Request.Method.POST,url,
            Response.Listener { response -> iVolley!!.onResponse(response.toString())},
            Response.ErrorListener { error -> iVolley!!.onResponse(error.message!!) })
        {
            override fun getParams(): MutableMap<String, String> {
                val params = HashMap<String,String>()
                params["name"] = "Heath Ledger"
                params["value"] = "This is value posted from android app"
                return params

            }
        }
    }

    fun patchRequest(url:String)
    {
        val postRequest = object: StringRequest(Request.Method.POST,url,
            Response.Listener { response -> iVolley!!.onResponse(response.toString())},
            Response.ErrorListener { error -> iVolley!!.onResponse(error.message!!) })
        {
            override fun getParams(): MutableMap<String, String> {
                val params = HashMap<String,String>()
                params["name"] = "Heath Ledger"
                params["value"] = "This is value posted from android app"
                return params

            }
        }
    }

    fun deleteRequest(url:String)
    {
        val deleteRequest = StringRequest(Request.Method.DELETE,url,Response.Listener { response ->
            iVolley!!.onResponse(response)
        },Response.ErrorListener { error -> iVolley!!.onResponse(error.message!!) })

        addToRequestQueue(deleteRequest)
    }

    companion object
    {
     private var mInstance : MyRequest?= null
        @Synchronized
        fun getInstance(context: Context): MyRequest{
            if(mInstance == null)
            {
                mInstance = MyRequest(context)
            }
            return mInstance!!
        }

        @Synchronized
        fun getInstance(context: Context, iVolley: IVolley): MyRequest{
            if(mInstance == null)
            {
                mInstance = MyRequest(context,iVolley)
            }
            return mInstance!!
        }
    }

}