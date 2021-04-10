package com.nepplus.daily10minute_apiserverpractice_20210410

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : BaseActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        setupEvents()
        setValues()
    }//onCreate에 적혀있는 함수들은 무조건 추가가된다. - 안에있는 함수들도 무조건 실행을 해주어야 한다.

    override fun setupEvents() {

        loginBtn.setOnClickListener {

     //입력한 이메일/비번 추출
        val inputEmail = emailEdt.text.toString()
        val inputPassword = passwordEdt.text.toString()

        }
    }

    override fun setValues() {
        TODO("Not yet implemented")
    }

}