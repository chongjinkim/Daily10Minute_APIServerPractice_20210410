package com.nepplus.daily10minute_apiserverpractice_20210410

import androidx.appcompat.app.AppCompatActivity

//객체화를 시키지 않을겁니다.
abstract class BaseActivity : AppCompatActivity() {

    val mContext = this

//모든 액티비티는 이 2개를 무조건 구현을 한다.
    abstract fun setupEvents()
    abstract fun setValues()
}