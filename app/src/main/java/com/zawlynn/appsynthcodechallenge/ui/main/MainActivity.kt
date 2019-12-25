package com.zawlynn.appsynthcodechallenge.ui.main

import android.annotation.SuppressLint
import android.graphics.Bitmap
import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.core.graphics.drawable.RoundedBitmapDrawableFactory
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.LinearLayoutManager
import com.bumptech.glide.Glide
import com.bumptech.glide.request.target.BitmapImageViewTarget
import com.google.android.material.snackbar.Snackbar
import com.zawlynn.appsynthcodechallenge.R
import com.zawlynn.appsynthcodechallenge.data.database.dao.model.UserInfo
import com.zawlynn.appsynthcodechallenge.data.network.Resource
import com.zawlynn.appsynthcodechallenge.data.network.isNetworkStatusAvailable
import com.zawlynn.appsynthcodechallenge.ui.main.viewmodel.MainActivityViewmodel
import com.zawlynn.codigo.assignment.ui.main.RecAdapter
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.content_no_network.*


class MainActivity : AppCompatActivity() ,View.OnClickListener{
    lateinit var viewmodel: MainActivityViewmodel
    val TAG ="MainActivity_"
    lateinit var adapter: RecAdapter
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        viewmodel = ViewModelProviders.of(this).get(MainActivityViewmodel::class.java)
        viewmodel.userInfo.observe(this, Observer {
            it?.let {
                Log.d(TAG,it.toString())
                initUI(it)
                viewmodel.getDataFromApi(it.userId)
            }
        })
        viewmodel.isNetwork.observe(this, Observer {
            it?.let {
                if(!it){
                    layout_no_internet.visibility= View.VISIBLE
                }else{
                    layout_no_internet.visibility= View.GONE
                }
            }
        })
        viewmodel.data.observe(this,  Observer {
            it?.let {
                Log.d(TAG,"TOTAL VALUES ARE "+it.size)
                adapter.submitList(it)
            }

        })
        viewmodel.status.observe(this , Observer {
            it?.let {
                Log.d(TAG,"CURRENT STATUS "+it.name)
                if(it==Resource.Status.LOADING){
                    progress_loading.visibility = View.VISIBLE
                }else{
                    if(it==Resource.Status.ERROR){
                        Snackbar.make(container,resources.getString(R.string.error_occur)
                            ,Snackbar.LENGTH_SHORT).show()
                    }
                    progress_loading.visibility = View.GONE
                }
            }
        })
        btnTryagain.setOnClickListener(this)
    }
    @SuppressLint("SetTextI18n")
    fun initUI(userInfo: UserInfo){
        Glide.with(applicationContext).asBitmap().load(userInfo.avatar)
            .into(object : BitmapImageViewTarget(imgProfile) {
                override fun setResource(res: Bitmap?) {
                    val resource = res
                    val circularBitmapDrawable =
                        RoundedBitmapDrawableFactory.create(resources, resource)
                    circularBitmapDrawable.isCircular = true
                    imgProfile.setImageDrawable(circularBitmapDrawable)
                }
            })
        tv_likes.text = userInfo.likes.toString()
        tv_followers.text =userInfo.followers.toString()
        tv_following.text=userInfo.following.toString()
        tv_userName.text = userInfo.firstName+" "+userInfo.lastName
        adapter= RecAdapter()
        recNoti.adapter=adapter
        recNoti.layoutManager= LinearLayoutManager(applicationContext)
    }

    override fun onClick(p0: View?) {
        if(isNetworkStatusAvailable()){
            layout_no_internet.visibility= View.GONE
            viewmodel.getUserInfo()
        }
    }
}
