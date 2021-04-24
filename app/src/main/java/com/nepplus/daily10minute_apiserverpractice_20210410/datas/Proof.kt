package com.nepplus.daily10minute_apiserverpractice_20210410.datas

import org.json.JSONObject
import java.util.ArrayList

class Proof {

    //멤버 변수들만 추가, 생성자 커스터마이징 X

    var id = 0 // Int가 들어올 예정
    var content = ""// String이 들어올 예정

    //이미지 경로(URL) 들을 저장할 ArrayList

    val imageUrls = ArrayList<String>()

    companion object{

  //Json 한 덩어리 -> proof로 변환 가능

        fun getProofFromJson(jsonObj : JSONObject) : Proof {

            val proof = Proof()

       //json항목을 -> proof 변수들에 대입

            proof.id = jsonObj.getInt("id")
            proof.content = jsonObj.getString("content")

//            imageUrl에 사진 경로들을 추가.

            val imagesArr = jsonObj.getJSONArray("images")

            for(i in 0 until imagesArr.length()){

//            { } -> "img_url" String을 추출 -> proof의 imageUrls에 추가.

            proof.imageUrls.add(imagesArr.getJSONObject(i).getString("img_url"))

            }

            return proof

        }

    }


}