package com.nepplus.daily10minute_apiserverpractice_20210410

import android.app.DatePickerDialog
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.DatePicker
import kotlinx.android.synthetic.main.activity_view_proof_by_date.*

class ViewProofByDateActivity : BaseActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_view_proof_by_date)
        setupEvents()
        setValues()
    }

    override fun setupEvents() {

        selectDateBtn.setOnClickListener {

//            날짜가 선택되면 어떡할건지? 변수에 저장.

            val dateSetListener = object : DatePickerDialog.OnDateSetListener{
                override fun onDateSet(view: DatePicker?, year: Int, month: Int, dayOfMonth: Int) {

//선택된 년 / 월 / 일이 변수에 담겨져 있다. -> TextView에 반영을 해준다.

                    Log.d("선택된날짜", "${year}년", "${month}월", "${dayOfMonth}일")

                }


            }

//            실제로 달력 띄우기(alertDiaglog)

            val datePickerDialog = DatePickerDialog(mContext, dateSetListener, 2021, 4, 24)

             datePickerDialog.show()

        }
    }

    override fun setValues() {

    }
}