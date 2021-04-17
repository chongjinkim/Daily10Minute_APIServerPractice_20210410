package com.nepplus.daily10minute_apiserverpractice_20210410

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.bumptech.glide.Glide
import com.nepplus.daily10minute_apiserverpractice_20210410.datas.Project
import kotlinx.android.synthetic.main.activity_view_project_detail.*

class ViewProjectDetailActivity : BaseActivity() {

    lateinit var mProject : Project

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_view_project_detail)
        setupEvents()
        setValues()
    }

    override fun setupEvents() {



    }

    override fun setValues() {

        mProject = intent.getSerializableExtra("projectinfo") as Project//형변환

        Glide.with(mContext).load(mProject.imageUrl).into(projectImg)
        titleTxt.text = mProject.title
        descriptionTxt.text = mProject.description

        userCountText.text = "${mProject.onGoingUserCount}명"
        proofMethodTxt.text = mProject.proofMethod

    }
}