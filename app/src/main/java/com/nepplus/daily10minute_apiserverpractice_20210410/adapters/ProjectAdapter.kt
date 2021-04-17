package com.nepplus.daily10minute_apiserverpractice_20210410.adapters

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.ImageView
import android.widget.TextView
import com.nepplus.daily10minute_apiserverpractice_20210410.R
import com.nepplus.daily10minute_apiserverpractice_20210410.datas.Project
import java.util.ArrayList

class ProjectAdapter(
        val mContext : Context,
        resId : Int,
        val mList : List<Project>) : ArrayAdapter<Project>(mContext, resId, mList) {

        val inflater = LayoutInflater.from(mContext)

    override fun getView(position: Int, convertView: View?, parent: ViewGroup): View {

        var tempRow = convertView

        //재활용이 꼭 필요할 때만 활용을 한다!!
        if(tempRow == null){
            tempRow = inflater.inflate(R.layout.project_list_item, null)
        }

        //데이터 반영이 안되고 틀만 만들었다.
        val row = tempRow!!

        val data = mList[position]

       val projectBackgroundImg = row.findViewById<ImageView>(R.id.projectBackgroundImg)
       val projectProfileText = row.findViewById<TextView>(R.id.profileTitleText)

       projectProfileText.text = data.title

        return row
    }
}