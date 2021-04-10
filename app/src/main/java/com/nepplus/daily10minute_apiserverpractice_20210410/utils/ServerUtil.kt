package com.nepplus.daily10minute_apiserverpractice_20210410.utils

import android.util.Log
import okhttp3.*
import org.json.JSONObject
import java.io.IOException
import kotlin.math.log

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

            client.newCall(request).enqueue(object : Callback {
                override fun onFailure(call: Call, e: IOException) {
//                    서버에 연결 자체를 실패 (서버를 접근 할 수 없는 상황.)
//                    데이터 요금 소진, 서버가 터짐 등드의 이유로 연결 자체에 실패
//                    반대 - 로그인 비번 틀림, 회원가입 이메일 중복 등등 로직 실패 - 연결은 성공, 결과는 실패
                }

                override fun onResponse(call: Call, response: Response) {

//                    서버에 응답을 받아내는 데 성공한 경우
//                    응답(response) > 내부의 본문(body)만 활용 > String 형태로 저장

                    val bodyString = response.body!!.string() //toString아니다 String으로 활용

//                    bodyString은 인코딩 되어이쓴 상태라 => 사람이 읽기가 어렵다(한글 깨짐)
//                    bodyString -> jsonObject로 변환시키면 => 읽을 수 있게 함

                    val jsonObj = JSONObject(bodyString)

                    Log.d("서버응답", jsonObj.toString())

                }


            })

        }

    }

}