/*
 * EyeTipActivity.kt
 * @handongkim
 * intent를 통해 어떤 DB와 연동되는지 확인 후 해당 데이터를 반영하는 액티비티
 * history
 * 20191123     handongkim      init
 * 20191124     handongkim      recyclerview item 클릭 기능 구현
 * 20191127     handongkim      누른 버튼에 따라 알맞는 recyclerViewAdapter 적용
 */

package com.example.black

import android.content.Intent
import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ValueEventListener
import kotlinx.android.synthetic.main.activity_tips_view.*
import kotlinx.android.synthetic.main.isg_test_activity.*
import kotlin.random.Random

class EyeTipActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_tips_view)

        val database = FirebaseDatabase.getInstance()
        val eDB = database.getReference("eyesaving")
        var value: Map<String, Map<String, Map<String, Any>>>? = null

        eDB.addValueEventListener(object : ValueEventListener {

            override fun onCancelled(p0: DatabaseError) {}

            override fun onDataChange(p0: DataSnapshot) {
                value = p0.getValue() as Map<String, Map<String, Map<String, Any>>>
                Log.d("DBTEST", value.toString())
            }
        })

        val eyeTipData = EyeDataClass(value)

        val dbKey = intent.getStringExtra("DBKEY")
        val title = intent.getStringExtra("TITLE")

        tipTitle.text = title

        when (dbKey) {
            "FOOD" -> {
                val recyclerAdapter = TestRecyclerAdapter(this, eyeTipData.testResultInfo) {
                    //                val recyclerAdapter = FoodRecyclerAdapter(this, eyeTipData.eyeFoodInfo) {
                    val intent = Intent(this, eachTipActivity::class.java)
//                    intent.putExtra("id", it.id.toString())
//                    intent.putExtra("title", it.title)
//                    intent.putExtra("content", it.content)
                    intent.putExtra("title", it.name)
                    startActivity(intent)

                }
                tipRecyclerView.adapter = recyclerAdapter
            }
            "TEA" -> {
                val recyclerAdapter = TeaRecyclerAdapter(this, eyeTipData.eyeTeaInfo) {
                    val intent = Intent(this, eachTipActivity::class.java)
                    intent.putExtra("id", it.id.toString())
                    intent.putExtra("title", it.title)
                    intent.putExtra("content", it.content)
                    startActivity(intent)

                }
                tipRecyclerView.adapter = recyclerAdapter
            }
            "DRUG" -> {
                val recyclerAdapter = DrugRecyclerAdapter(this, eyeTipData.eyeDrugInfo) {
                    val intent = Intent(this, eachTipActivity::class.java)
                    intent.putExtra("id", it.id.toString())
                    intent.putExtra("title", it.title)
                    intent.putExtra("content", it.content)
                    startActivity(intent)

                }
                tipRecyclerView.adapter = recyclerAdapter
            }
            "EXCERCISE" -> {
                val recyclerAdapter = ExcerciseRecyclerAdapter(this, eyeTipData.eyeExcerciseInfo) {
                    val intent = Intent(this, eachTipActivity::class.java)
                    intent.putExtra("id", it.id.toString())
                    intent.putExtra("title", it.title)
                    intent.putExtra("content", it.content)
                    startActivity(intent)

                }
                tipRecyclerView.adapter = recyclerAdapter
            }
            "INFO" -> {
                val recyclerAdapter = InfoRecyclerAdapter(this, eyeTipData.eyeInfo) {
                    val intent = Intent(this, eachTipActivity::class.java)
                    intent.putExtra("id", it.id.toString())
                    intent.putExtra("title", it.title)
                    intent.putExtra("content", it.content)
                    startActivity(intent)

                }
                tipRecyclerView.adapter = recyclerAdapter
            }
        }

        val lm = LinearLayoutManager(tipRecyclerView.context)
        tipRecyclerView.layoutManager = lm
        tipRecyclerView.setHasFixedSize(true)

    } // end of onCreate

} // end of class