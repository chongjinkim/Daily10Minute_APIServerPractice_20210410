package com.nepplus.daily10minute_apiserverpractice_20210410.utils

import android.util.Log
import okhttp3.*
import okhttp3.HttpUrl.Companion.toHttpUrlOrNull
import org.json.JSONObject
import java.io.IOException
import kotlin.math.log

class ServerUtil {

//    화면(액티비티)의 입장에서, 서버에 다녀오면 할 행동을 적는 가이드북
//    행동 지침을 기록하는 개념 : InterFace

    interface JsonResponseHandler  {
       fun onResponse(jsonObj : JSONObject)

    }

    companion object{

//        이 중괄호 안에 변수 / 함수는
//        ServerUtil자체의 변수 or 함수()등으로 클래스 자체의 기능으로 활용 가능
//        Static개념에 대응됨

//        서버의 호스트 주소 저장.

        val HOST_URL = "http://15.164.153.174"

//        서버에 로그인을 요청하는 기능 => 함수로 만들고 사용하자
//        필요 재료 : 이메일, 비번 + 로그인 결과에 대한 처리 방안(가이드북)

        fun postRequestLogin(email : String, pw : String, handler : JsonResponseHandler?) {

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
                .url(urlString)//어디로 가는지
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

//                    받아낸 서버 응답 내용은 => 여기(ServerUtil)서 활용하는 게 아니라
//                     화면에서 UI에 반영하기 위한 재료로 사용
//                    Code : 400 => 로그인 실패 토스트(메인)

//                    우리가 완성해낸 jsonObj변수를 -> 액티비티에 넘겨주자 => 파싱 등의 처리는 액티비티에서 작성
                    handler?.onResponse(jsonObj)//가이드북 적혀있다면 실행

                }


            })

        }

//        서버에 회원가입 기능

        fun putRequestSignUp(email : String, pw : String, nickname : String, handler : JsonResponseHandler?){

           val urlString  = "${HOST_URL}/user"

           val formData = FormBody.Builder()
               .add("email", email)
               .add("password", pw)
               .add("nick_name", nickname)
               .build()

            val request = Request.Builder()
                .url(urlString)
                .put(formData)
                .build()


            val client = OkHttpClient()
            client.newCall(request).enqueue(object : Callback{
                override fun onFailure(call: Call, e: IOException) {

                }

                override fun onResponse(call: Call, response: Response) {
                    val bodyString = response.body!!.string()
                    val jsonObj = JSONObject(bodyString)
                    Log.d("서버응답", jsonObj.toString())
                    handler?.onResponse(jsonObj)
                }


            })
        }

        fun getRequestEmailCheck(email : String, handler: JsonResponseHandler?){
//    어디로 + 어떤데이터 => URL을 만들 때 한꺼번에 전부 적어야한다.
//            주소로 적는게 복잡할 예정 => 호스트주소 / email_chek
//             urlBuilder

            val urlBuilder = "${HOST_URL}/email_check".toHttpUrlOrNull()!!.newBuilder()

//            만들어진 기초 url에 필요한 파라미터들을 붙여주자
            urlBuilder.addEncodedQueryParameter("email", email)

//       붙일 정보는 다 붙였으면 최종 String형태로 변환

            val urlString = urlBuilder.build().toString()

            Log.d("가공된URL", urlString)

// 어디로 + 어떤 데이터 ? => 모두 UrlString에 적혀있는 상태
//            어떤 메쏘드?get => Request에 담아주자
        val request = Request.Builder()
                .url(urlString)
                .get()
                .build()

            val client = OkHttpClient()

            client.newCall(request).enqueue(object : Callback{

                override fun onFailure(call: Call, e: IOException) {

                }

                override fun onResponse(call: Call, response: Response) {

   //response전체 > 본문 추출 > JSONobject 변환 > 이 기능을 불러낸 화면에 전달.
                    val bodyString = response.body!!.string()

     //한글이 깨져있으니 jsonObj 변환
                    val jsonObj = JSONObject(bodyString)

                    Log.d("서버응답", jsonObj.toString())

                    handler?.onResponse(jsonObj)

                }


            })


        }
    }

}