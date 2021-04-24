package com.nepplus.daily10minute_apiserverpractice_20210410.adapreters

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import com.bumptech.glide.Glide
import com.nepplus.daily10minute_apiserverpractice_20210410.R
import com.nepplus.daily10minute_apiserverpractice_20210410.datas.Project
import com.nepplus.daily10minute_apiserverpractice_20210410.datas.Proof
import org.w3c.dom.Text
import java.util.ArrayList

class ProofAdapter(
        val mContext : Context,
        resId : Int,
        val mList : List<Proof>) : ArrayAdapter<Proof>(mContext, resId, mList) {

        val inflater = LayoutInflater.from(mContext)

    override fun getView(position: Int, convertView: View?, parent: ViewGroup): View {

        var tempRow = convertView

        //재활용이 꼭 필요할 때만 활용을 한다!!
        if(tempRow == null){
            tempRow = inflater.inflate(R.layout.proof_list_item, null)
        }

        //데이터 반영이 안되고 틀만 만들었다.
        val row = tempRow!!

        val proofData= mList[position]

        val writerProfileImg = row.findViewById<ImageView>(R.id.writerProfileImg)
        val writerNicknameTxt = row.findViewById<TextView>(R.id.writerNicknameTxt)
        val proofTimeTxt = row.findViewById<TextView>(R.id.proofTimeTxt)
        val proofContentTxt = row.findViewById<TextView>(R.id.proofContentTxt)
        val proofImg = row.findViewById<ImageView>(R.id.proofImg)
        val likeBtn = row.findViewById<Button>(R.id.likeBtn)
        val replyBtn = row.findViewById<Button>(R.id.replyBtn)

        proofContentTxt.text = proofData.content


        return row
    }
}