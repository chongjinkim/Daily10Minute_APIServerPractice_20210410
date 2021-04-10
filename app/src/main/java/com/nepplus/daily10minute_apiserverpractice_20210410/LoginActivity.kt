package com.nepplus.daily10minute_apiserverpractice_20210410

import android.content.Intent
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

     // 체크박수의 체크여부가 변경되면
     // sharedPreferences에 어떻게 변화되었는지(체크 / 해제) 저장 예제
        autoLoginCheckBox.setOnCheckedChangeListener { buttonView, isChecked ->

            ContextUtil.setAutoLogin(mContext, isChecked)

        }

        signUpBtn.setOnClickListener {

            val myIntent = Intent(this, SignUpActivity::class.java)

            startActivity(myIntent)

        }


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
                       //로그인 한 사람의 닉네임 + 환영합니다 + 토스트
                        //메인화면으로 이동
                        val dataObj = jsonObj.getJSONObject("data")
                        val userObj = dataObj.getJSONObject("user")

                        val nickname = userObj.getString("nick_name")

                        //서버가 내려주는 토큰 값 추출
                        val token = dataObj.getString("token")

                        //sharedPreferences에 지정 : 기기에 보관(전원이 나가도 유지)



                        runOnUiThread {

                            Toast.makeText(mContext, "${nickname}님 환영합니다.", Toast.LENGTH_SHORT).show()

                            val myIntent = Intent(mContext, MainActivity::class.java)
                            startActivity(myIntent)

                            finish()
                        }
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

//        Contextutl로 저장해둔 자동 로그인여부를 꺼내서 => 체크박스에 반영.
        autoLoginCheckBox.isChecked = ContextUtil.getAutoLogin(mContext)
    }

}