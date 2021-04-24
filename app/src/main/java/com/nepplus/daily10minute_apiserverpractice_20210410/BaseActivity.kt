package com.nepplus.daily10minute_apiserverpractice_20210410

import androidx.appcompat.app.AppCompatActivity

//객체화를 시키지 않을겁니다.
abstract class BaseActivity : AppCompatActivity() {

    val mContext = this

//모든 액티비티는 이 2개를 무조건 구현을 한다.
    abstract fun setupEvents()
    abstract fun setValues()

//    커스텀 액션바를 적용해주는 기능(함수)

    fun setCustomActionBar(){

//        1. 액션바 어떤 모양으로 보이게 하고 싶은지?
//        => 모양 : layout -> xml 작성하자
//
    }
}