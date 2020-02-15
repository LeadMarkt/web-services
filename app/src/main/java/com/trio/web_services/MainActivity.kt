package com.trio.web_services

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity(), IVolley {
    override fun onResponse(response: String) {
        Toast.makeText(this@MainActivity,""+response,Toast.LENGTH_SHORT).show()
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        btn_get.setOnClickListener{
            MyRequest.getInstance(this@MainActivity, this@MainActivity)
                .getRequest("https://jsonplaceholder.typicode.com/todos/1")
        }

        btn_post.setOnClickListener{
            MyRequest.getInstance(this@MainActivity, this@MainActivity)
                .postRequest("https://jsonplaceholder.typicode.com/posts")
        }

        btn_put.setOnClickListener{
            MyRequest.getInstance(this@MainActivity, this@MainActivity)
                .putRequest("https://jsonplaceholder.typicode.com/posts/1")
        }

        btn_patch.setOnClickListener{
            MyRequest.getInstance(this@MainActivity, this@MainActivity)
                .patchRequest("https://jsonplaceholder.typicode.com/posts/1")
        }

        btn_delete.setOnClickListener{
            MyRequest.getInstance(this@MainActivity, this@MainActivity)
                .deleteRequest("https://jsonplaceholder.typicode.com/posts/1")
        }

        btn_image_loader.setOnClickListener{
            image_view.setImageUrl("", MyRequest.getInstance(this@MainActivity).imageLoader)
        }
    }
}
