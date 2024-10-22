package com.example.lab2

import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.RadioButton
import android.widget.TextView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat



class MainActivity : AppCompatActivity() {


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main)

        //定義元件變數
        val edName = findViewById<EditText>(R.id.edName)
        val tvText = findViewById<TextView>(R.id.tvText)
        val tvName = findViewById<TextView>(R.id.tvName)
        val tvWinner = findViewById<TextView>(R.id.tvWinner)
        val tvMyMora = findViewById<TextView>(R.id.tvMyMora)
        val tvTargetMora = findViewById<TextView>(R.id.tvTargetMora)
        val btnScissor = findViewById<RadioButton>(R.id.btnScissor)
        val btnStone = findViewById<RadioButton>(R.id.btnStone)
        val btnPaper = findViewById<RadioButton>(R.id.btnPaper)
        val btnMora = findViewById<Button>(R.id.btnMora)

        //設定 btnMora 的點擊事件
        btnMora.setOnClickListener {
            if(edName.text.isEmpty()){//玩家未輸入姓名
                tvText.setText("請輸入玩家姓名")
            }
            else{
                tvName.text = "名字\n${edName.text}"//設定玩家姓名

                var myMora : Int //我方出拳
                val targetMora = (0..2).random()//電腦出拳，產生亂數0~2

                //設定我方出拳
                when{
                    btnScissor.isChecked() -> {tvMyMora.text = "我方出拳\n剪刀"
                        myMora = 0
                    }
                    btnStone.isChecked() -> {tvMyMora.text = "我方出拳\n石頭"
                        myMora = 1
                    }
                    else -> {tvMyMora.text = "我方出拳\n布"
                        myMora = 2
                    }
                }
                //設定電腦出拳
                when(targetMora){
                    0 -> tvTargetMora.text = "電腦出拳\n剪刀"
                    1 -> tvTargetMora.text = "電腦出拳\n石頭"
                    else -> tvTargetMora.text = "電腦出拳\n布"
                }

                //判斷勝負
                when {
                    myMora == targetMora -> {
                        tvWinner.text = "勝利者\n平手"
                        tvText.text = "平局，請再試一次！"
                    }

                    (myMora == 0 && targetMora == 2) ||
                            (myMora == 1 && targetMora == 0) ||
                            (myMora == 2 && targetMora == 1) -> {
                        tvWinner.text = "勝利者\n${edName.text}"
                        tvText.text = "恭喜你獲勝了！！！"
                    }

                    else -> {
                        tvWinner.text = "勝利者\n電腦"
                        tvText.text = "可惜，電腦獲勝了！"
                    }
                }
            }
        }




        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
    }
}