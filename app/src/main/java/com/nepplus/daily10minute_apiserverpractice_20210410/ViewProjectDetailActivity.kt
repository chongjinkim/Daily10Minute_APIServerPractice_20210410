package com.nepplus.daily10minute_apiserverpractice_20210410

import android.graphics.Color
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.TextView
import android.widget.Toast
import com.bumptech.glide.Glide
import com.nepplus.daily10minute_apiserverpractice_20210410.datas.Project
import com.nepplus.daily10minute_apiserverpractice_20210410.utils.ServerUtil
import kotlinx.android.synthetic.main.activity_view_project_detail.*
import org.json.JSONObject

class ViewProjectDetailActivity : BaseActivity() {

    lateinit var mProject : Project

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_view_project_detail)
        setupEvents()
        setValues()
    }

    override fun setupEvents() {

        giveUpBtn.setOnClickListener {

     //서버에 포기의사 전달
        ServerUtil.deleteRequestGiveUpProject(mContext, mProject.id, object : ServerUtil.JsonResponseHandler{

            override fun onResponse(jsonObj: JSONObject) {

                val code = jsonObj.getInt("code")

                if(code == 200){

                }

                else{

                    runOnUiThread {
                        Toast.makeText(mContext, "포기신청에 실패했습니다.", Toast.LENGTH_SHORT).show()
                    }
                }

            }


        })

        }

        applyBtn.setOnClickListener {

 //서버에서 참여의사 전달
            ServerUtil.postRequestApplyProject(mContext, mProject.id, object : ServerUtil.JsonResponseHandler{

                override fun onResponse(jsonObj: JSONObject) {

//성공시 응답으로 => 새로 값이 반영된 프로젝트 JSONObject를 다시 내려준다
//                    새로 파싱해서 -> mProject를 갱신
                    val code = jsonObj.getInt("code")

                    if(code == 200){

                        val dataObj = jsonObj.getJSONObject("data")
                        val projectObj = dataObj.getJSONObject("project")

                        mProject = Project.getProjectFromJson(projectObj)

                        runOnUiThread {
                            refreshDataToUi()
                        }

                    }

                    else{

                        runOnUiThread {
                            Toast.makeText(mContext, "참여 신청에 실패했습니다", Toast.LENGTH_SHORT).show()
                        }
                    }
                }

            })

        }

    }

    override fun setValues() {

        mProject = intent.getSerializableExtra("projectinfo") as Project//형변환

        refreshDataToUi()

    }

    fun refreshDataToUi(){

        Glide.with(mContext).load(mProject.imageUrl).into(projectImg)
        titleTxt.text = mProject.title
        descriptionTxt.text = mProject.description

        userCountText.text = "${mProject.onGoingUserCount}명"
        proofMethodTxt.text = mProject.proofMethod
        //빈 레이아웃을 불러내서 -> 기존의 텍스트뷰 삭제 -> 태그 개수만큼 텍스트뷰 코틀린에서 추가

//      태그 목록은 -> 몇 개 일지가 매번 다르다.
//       긴 layout을 불러내서 => 태그 갯수만큼 텍스트뷰(코틀린에서)를 추가.

        tagListLayout.removeAllViews()

        for(tag in mProject.tags){

            Log.d("프로젝트태그", tag)

            val tagTextView = TextView(mContext)
            tagTextView.text = "#${tag}"
            tagTextView.setTextColor(Color.BLUE)

            tagListLayout.addView(tagTextView)

        }
    }
}