package com.nepplus.daily10minute_apiserverpractice_20210410.datas

import org.json.JSONObject
import java.util.ArrayList

class User {

    var id = 0
    var email = ""
    var nickName = ""

    val profileImgUrls = ArrayList<String>()

    companion object{

        fun getUserFromJson(jsonObj : JSONObject) : User{

            val user = User()

            user.id = jsonObj.getInt("id")
            user.email = jsonObj.getString("email")
            user.nickName = jsonObj.getString("nick_name")

            val profileArr = jsonObj.getJSONArray("profile_images")

            for(i in 0 until profileArr.length()){

                user.profileImgUrls.add(profileArr.getJSONObject(i).getString("img_url"))
            }
            return user
        }

    }
}