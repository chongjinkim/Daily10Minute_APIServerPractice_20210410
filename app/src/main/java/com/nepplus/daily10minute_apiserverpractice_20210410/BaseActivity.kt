package com.nepplus.daily10minute_apiserverpractice_20210410

import androidx.appcompat.app.ActionBar
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar

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
//      2. 기본 액션바 => 커스텀 액션바로 교체하는 작업이 필요

        val defaultActionBar = supportActionBar!!

//        기본액션바 모드를 -> 커스텀 지원 모드로 변경

        defaultActionBar.displayOptions = ActionBar.DISPLAY_SHOW_CUSTOM

//        실제로 보여줄 커스텀 화면 지정.(my_custom_action_bar 지정)
        defaultActionBar.setCustomView(R.layout.my_custom_action_bar)

//      좌우로 여백이 남아서 제거를 해야 한다.
//      액션바를 잡고 있는(부모의 역할인) => Toolbar의 여백 제거. -> (내부 공간을 0으로 설정)

//        Toolbar 선택 : androidx가 주는 걸로 선택
        val myToolBar = defaultActionBar.customView.parent as Toolbar
        myToolBar.setContentInsetsAbsolute(0, 0)

    }
}