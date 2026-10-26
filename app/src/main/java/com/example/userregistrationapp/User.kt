package com.example.userregistrationapp

import java.io.Serializable

data class User(
    val hoTen: String,
    val email: String,
    val soDienThoai: String,
    val matKhau: String
) : Serializable