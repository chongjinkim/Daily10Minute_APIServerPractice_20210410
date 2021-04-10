package com.nepplus.daily10minute_apiserverpractice_20210410.utils

import android.content.Context

class ContextUtil {

    companion object{

        private val prefName = "Daily10MinutesPref"

        private val AUTO_LOGIN = "AUTO_LOGIN"

        private val LOGIN_TOKEN = "LOGIN_TOKEN"

//        자동 로그인 설정여부 저장(Setter) 함수

        fun setAutoLogin(context : Context, autoLogin : Boolean){

//        메모장을 열어서 => 변수에 담아두자
          val pref = context.getSharedPreferences(prefName, Context.MODE_PRIVATE)

//         변수를 이용해서 => 실제 저장 여부 기로
           pref.edit().putBoolean(AUTO_LOGIN, autoLogin).apply()

        }

//        설정해둔 자동로그인 여부 확인(Getter) 함수


        fun getAutoLogin(context: Context) : Boolean{

            val pref = context.getSharedPreferences(prefName, Context.MODE_PRIVATE)

            return pref.getBoolean(AUTO_LOGIN, false)
        }

        fun setLoginToken(context: Context, token: String){

            val pref = context.getSharedPreferences(prefName, Context.MODE_PRIVATE)
            pref.edit().putString(LOGIN_TOKEN, token).apply()

        }

        fun getLoginToken(context: Context) : String{

            val pref = context.getSharedPreferences(prefName, Context.MODE_PRIVATE)

            return pref.getString(LOGIN_TOKEN, "")!!
        }
    }
}