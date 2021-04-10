package com.nepplus.daily10minute_apiserverpractice_20210410

import android.os.Bundle
import android.util.Log
import android.widget.Toast
import com.nepplus.daily10minute_apiserverpractice_20210410.utils.ServerUtil
import kotlinx.android.synthetic.main.activity_login.*
import org.json.JSONObject

class LoginActivity : BaseActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)
        setupEvents()
        setValues()
    }//onCreate에 적혀있는 함수들은 무조건 추가가된다. - 안에있는 함수들도 무조건 실행을 해주어야 한다.

    override fun setupEvents() {

        loginBtn.setOnClickListener {

     //입력한 이메일/비번 추출
        val inputEmail = emailEdt.text.toString()
        val inputPassword = passwordEdt.text.toString()

     //서버에 로그인 요청

            ServerUtil.postRequestLogin(inputEmail, inputPassword, object : ServerUtil.JsonResponseHandler{
                override fun onResponse(jsonObj: JSONObject) {

                    val codeNum = jsonObj.getInt("code")

                    if(codeNum == 200){
                        //로그인 성공한 경우
                    }
                    else{
//로그인 실패 => 토스트 띄워보자
//서버가 알려주는 실패 사유를 받아서 => 그 내용을 토스트로 한다.
                        val message = jsonObj.getString("message")

                        runOnUiThread {

                            Toast.makeText(mContext, message, Toast.LENGTH_SHORT).show()
                        }


                    }
                }


            })

        }
    }

    override fun setValues() {

    }

}