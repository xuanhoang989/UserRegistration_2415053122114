package com.example.userregistrationapp

import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity

class HomeActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_home)

        val tvThongTin = findViewById<TextView>(R.id.tvThongTin)
        val btnDangXuat = findViewById<Button>(R.id.btnDangXuat)

        // Nhận đối tượng User từ Intent
        val user = intent.getSerializableExtra("USER_DATA") as? User

        user?.let {
            tvThongTin.text = """
                Xin chào: ${it.hoTen}
                Email: ${it.email}
                Số điện thoại: ${it.soDienThoai}
                Trạng thái: Đăng ký thành công!
            """.trimIndent()
        }

        // Quay lại màn hình đăng ký
        btnDangXuat.setOnClickListener {
            finish()
        }
    }
}