package com.example.userregistrationapp

import android.content.Intent
import android.os.Bundle
import android.util.Patterns
import android.widget.Button
import android.widget.EditText
import android.widget.ScrollView
import androidx.appcompat.app.AppCompatActivity
import com.google.android.material.snackbar.Snackbar

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val rootLayout = findViewById<ScrollView>(R.id.rootLayout)
        val edtHoTen = findViewById<EditText>(R.id.edtHoTen)
        val edtEmail = findViewById<EditText>(R.id.edtEmail)
        val edtSdt = findViewById<EditText>(R.id.edtSdt)
        val edtMatKhau = findViewById<EditText>(R.id.edtMatKhau)
        val btnHuy = findViewById<Button>(R.id.btnHuy)
        val btnDangKy = findViewById<Button>(R.id.btnDangKy)

        // 1. Nút HỦY: Xóa trắng toàn bộ trường nhập liệu và đưa con trỏ về ô Họ tên
        btnHuy.setOnClickListener {
            edtHoTen.text.clear()
            edtEmail.text.clear()
            edtSdt.text.clear()
            edtMatKhau.text.clear()
            edtHoTen.requestFocus()
            Snackbar.make(rootLayout, "Đã làm mới form nhập liệu", Snackbar.LENGTH_SHORT).show()
        }

        // 2. Nút ĐĂNG KÝ: Validate dữ liệu và xử lý Snackbar / Intent
        btnDangKy.setOnClickListener {
            val hoTen = edtHoTen.text.toString().trim()
            val email = edtEmail.text.toString().trim()
            val sdt = edtSdt.text.toString().trim()
            val matKhau = edtMatKhau.text.toString().trim()

            // Kiểm tra rỗng
            if (hoTen.isEmpty() || email.isEmpty() || sdt.isEmpty() || matKhau.isEmpty()) {
                Snackbar.make(rootLayout, "Lỗi: Không được để trống bất kỳ trường nào!", Snackbar.LENGTH_LONG)
                    .setBackgroundTint(getColor(android.R.color.holo_red_dark))
                    .show()
                return@setOnClickListener
            }

            // Kiểm tra định dạng Email
            if (!Patterns.EMAIL_ADDRESS.matcher(email).matches()) {
                Snackbar.make(rootLayout, "Lỗi: Định dạng Email không hợp lệ!", Snackbar.LENGTH_LONG)
                    .setBackgroundTint(getColor(android.R.color.holo_red_dark))
                    .show()
                return@setOnClickListener
            }

            // Kiểm tra Số điện thoại (từ 10 chữ số)
            if (sdt.length < 10) {
                Snackbar.make(rootLayout, "Lỗi: Số điện thoại phải có ít nhất 10 số!", Snackbar.LENGTH_LONG)
                    .setBackgroundTint(getColor(android.R.color.holo_red_dark))
                    .show()
                return@setOnClickListener
            }

            // Kiểm tra độ dài mật khẩu (tối thiểu 6 ký tự)
            if (matKhau.length < 6) {
                Snackbar.make(rootLayout, "Lỗi: Mật khẩu phải từ 6 ký tự trở lên!", Snackbar.LENGTH_LONG)
                    .setBackgroundTint(getColor(android.R.color.holo_red_dark))
                    .show()
                return@setOnClickListener
            }

            // SUCCESS: Khởi tạo đối tượng từ class User
            val user = User(
                hoTen = hoTen,
                email = email,
                soDienThoai = sdt,
                matKhau = matKhau
            )

            // Chuyển sang màn hình Trang chủ (HomeActivity)
            val intent = Intent(this, HomeActivity::class.java).apply {
                putExtra("USER_DATA", user)
            }
            startActivity(intent)
        }
    }
}