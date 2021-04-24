package com.nepplus.daily10minute_apiserverpractice_20210410.datas

import org.json.JSONObject

class Proof {

    //멤버 변수들만 추가, 생성자 커스터마이징 X

    var id = 0 // Int가 들어올 예정
    var content = ""// String이 들어올 예정

    companion object{

  //Json 한 덩어리 -> proof로 변환 가능

        fun getProofFromJson(jsonObj : JSONObject) : Proof {

            val proof = Proof()

       //json항목을 -> proof 변수들에 대입

            proof.id = jsonObj.getInt("id")
            proof.content = jsonObj.getString("content")

            return proof

        }

    }


}