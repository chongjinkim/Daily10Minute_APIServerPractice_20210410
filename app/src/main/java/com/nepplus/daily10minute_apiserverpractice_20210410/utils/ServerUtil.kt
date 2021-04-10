package com.nepplus.daily10minute_apiserverpractice_20210410.utils

import okhttp3.FormBody
import okhttp3.OkHttpClient
import okhttp3.Request

class ServerUtil {

    companion object{

//        이 중괄호 안에 변수 / 함수는
//        ServerUtil자체의 변수 or 함수()등으로 클래스 자체의 기능으로 활용 가능
//        Static개념에 대응됨

//        서버의 호스트 주소 저장.

        val HOST_URL = "http://15.164.153.174"

//        서버에 로그인을 요청하는 기능 => 함수로 만들고 사용하자

        fun postRequestLogin(email : String, pw : String) {

//            어느 주소로 가야하는가?/기능주소
//

            val urlString = "${HOST_URL}/user"


//            갈때 어떤 파라미터를 가져가야 하는가? POST VS GET에 따라 다르다.
//            post - formData

            val formData = FormBody.Builder()
                .add("email", email)
                .add("password", pw)
                .build()

//            모든 정보 종합 + 어떤 메쏘드?

            val request = Request.Builder()
                .url("urlString")//어디로 가는지
                .post(formData)//post방식 - 필요 데이터(formData)들고 가도록
                .build()

//            정리된 정보를 들고 => 실제 api 요청 진행

//            클라이언트로써 동작하는 코드를 쉽게 작성하도록 도와주는 라이브러리 : OkHttp
            val client = OkHttpClient()

            client.newCall(request)

        }

    }

}