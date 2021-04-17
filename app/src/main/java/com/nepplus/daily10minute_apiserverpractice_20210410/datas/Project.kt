package com.nepplus.daily10minute_apiserverpractice_20210410.datas

import org.json.JSONObject
import java.io.Serializable
import java.util.ArrayList

class Project (
        var id : Int,
        var title : String,
        var imageUrl : String,
        var description : String,
        var onGoingUserCount : Int,
        var proofMethod : String,
        var myLastStatus : String?) : Serializable {

//태그 목록을 저장하기 위한 ArrayList => 멤버변수 추가

    val tags = ArrayList<String>()

// 보조 생성자 추가 => Project()만으로도 만들 수 있게

    constructor() : this(0,"","","",0, "", null)

//    기능을 추가 => json을 넣으면 (input) : Project로 변환(return) => 단순 기능. companion Ojbect이용

   companion object{

       fun getProjectFromJson(jsonObj : JSONObject) : Project{

           val project = Project()

 // jsonObj에서 정보 추출 => project의 하위 항목 반영

          project.id = jsonObj.getInt("id")
          project.title = jsonObj.getString("title")
          project.imageUrl = jsonObj.getString("img_url")
          project.description = jsonObj.getString("description")

          project.onGoingUserCount = jsonObj.getInt("ongoing_users_count")
          project.proofMethod = jsonObj.getString("proof_method")

// 내 최종 도전 상태 => null일 가능성도 있다.
// null인 데이터를 파싱하라고 하면 => 에러처리, 파싱 중단
// null 인지 아닌지 확인 ? => null이 아닐때만 파싱하자
         if(!jsonObj.isNull("my_last_status")){

             project.myLastStatus = jsonObj.getString("my_last_status")

         }

//태그 목록  (JSONArray[ ]) => 반복 파싱 => tag에 String으로 추가.

           val tagsArr = jsonObj.getJSONArray("tags")

// for문 이용 => 내용물을 하나씩 반복해서 파싱 + 목록에 담기

           for(index in 0 until tagsArr.length()){

               val tagObj = tagsArr.getJSONObject(index)

               val title = tagObj.getString("title")

               project.tags.add(title)

           }

//           완성된 project가 결과로 나이도록

           return project

       }

   }






}