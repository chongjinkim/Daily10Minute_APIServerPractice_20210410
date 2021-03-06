package com.nepplus.daily10minute_apiserverpractice_20210410

import android.content.DialogInterface
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.appcompat.app.AlertDialog
import com.google.firebase.iid.FirebaseInstanceId
import com.nepplus.daily10minute_apiserverpractice_20210410.adapters.ProjectAdapter
import com.nepplus.daily10minute_apiserverpractice_20210410.datas.Project
import com.nepplus.daily10minute_apiserverpractice_20210410.utils.ContextUtil
import com.nepplus.daily10minute_apiserverpractice_20210410.utils.ServerUtil
import kotlinx.android.synthetic.main.activity_main.*
import org.json.JSONObject

class MainActivity : BaseActivity() {

   val mProjects = ArrayList<Project>()

   lateinit var mProjectAdapter : ProjectAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        setCustomActionBar()
        setupEvents()
        setValues()
    }

    override fun setupEvents() {

        logoutBtn.setOnClickListener {

//            정말 로그아웃 => AlertDialog

            val alert = AlertDialog.Builder(mContext)
            alert.setTitle("로그아웃")
            alert.setMessage("정말 로그아웃 하시겠습니까?")
            alert.setPositiveButton("확인", DialogInterface.OnClickListener { dialog, which ->

//          로그인 : 아이디 / 비번 서버전달 => 회원이 맞는 지 검사. => 성공 시 토큰 값을 전달 => 앱에서 토큰을 저장.
//          로그아웃 : 기기에 저장된 토큰값을 삭제
//
             ContextUtil.setLoginToken(mContext, "")

             val myIntent = Intent(mContext, LoginActivity::class.java)
             startActivity(myIntent)

             finish()

            })
            alert.setNegativeButton("취소", null)
            alert.show()

        }

    }

    override fun setValues() {


        projectListView.setOnItemClickListener { parent, view, position, id ->

            val clickedProject = mProjects[position]

            val myIntent = Intent(mContext, ViewProjectDetailActivity::class.java)

            myIntent.putExtra("projectinfo", clickedProject)

            startActivity(myIntent)

        }


   //서버에서 => 보여줄 프로젝트 목록이 어떤것들이 있는지 받아서 => project()형태로 변환해서 => mProjects에 하나하나 추가

        getProjectListFromServer()

        mProjectAdapter = ProjectAdapter(mContext, R.layout.project_list_item, mProjects)
        projectListView.adapter = mProjectAdapter

      //BaseActivity가 물려주는 backImg를 메인화면에서만 숨김처리
        backImg.visibility = View.GONE

        Log.d("기기토큰", FirebaseInstanceId.getInstance().token!!)


    }

    fun getProjectListFromServer(){
//        실제로 서버에서 받아오는 기능을 수행한다.
        ServerUtil.getRequestProjectList(mContext, object : ServerUtil.JsonResponseHandler{
            override fun onResponse(jsonObj: JSONObject) {

                val dataObj = jsonObj.getJSONObject("data")

                val projectsArr = dataObj.getJSONArray("projects")

//                반복문 for문으로 => projectArr 내부를 하내씩 꺼내서 파싱을 한다.

                for (i in 0 until projectsArr.length()){

//                    [ ]안에 있는 -> {  }들을, 하나 꺼내서 => 파싱 : Project형태로 변환 => mProjects에 추가

                    val projectObj = projectsArr.getJSONObject(i)

//                    Project클래스에 보조 생성자 추가 => 재료 없이도 만들 수 있게
//                    Project클래스에 함수(기능) 추가 => JSONObject 를 넣으면 => project형태로 변환해주는 기능

                    val project = Project.getProjectFromJson(projectObj)

                    mProjects.add(project)

                }

//                서버 통신이 어댑터 연결보다 먼저 실행되지만 => 실제로는 더 늦게 끝날 수도 있다.
//                데이터 추가가 => 리스트뷰의 내용 변경일 수도 있다.

                runOnUiThread {

                    mProjectAdapter.notifyDataSetChanged()
                }





            }


        })

    }
}