package id.ac.binus.pethub2.activity

import android.annotation.SuppressLint
import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.FirebaseDatabase
import id.ac.binus.pethub2.R

class RegisterActivity : AppCompatActivity() {
    @SuppressLint("MissingInflatedId")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_register)

        val tvGoToLogin = findViewById<TextView>(R.id.tvGoToLogin)
        val btnRegister = findViewById<Button>(R.id.btnRegister)
        val etUsernameRegister = findViewById<EditText>(R.id.etUsernameRegister)
        val etPasswordRegister = findViewById<EditText>(R.id.etPasswordRegister)
        val etEmailRegister = findViewById<EditText>(R.id.etEmailRegister)
        val etPhoneRegister = findViewById<EditText>(R.id.etPhoneRegister)
        val etConfirmPassword = findViewById<EditText>(R.id.etConfirmPassword)

        val database =
            FirebaseDatabase.getInstance("https://pethub-6346f-default-rtdb.asia-southeast1.firebasedatabase.app").reference
        tvGoToLogin.setOnClickListener {
            val intent = Intent(this, LoginActivity::class.java)
            startActivity(intent)
        }

        btnRegister.setOnClickListener {
            val username = etUsernameRegister.text.toString().trim()
            val password = etPasswordRegister.text.toString().trim()
            val email = etEmailRegister.text.toString().trim()
            val phoneNumber = etPhoneRegister.text.toString().trim()
            val confirmPassword = etConfirmPassword.text.toString().trim()

            if(!validateInput(username, password, confirmPassword, email, phoneNumber)) {
                return@setOnClickListener
            }

            val auth = FirebaseAuth.getInstance()
            auth.createUserWithEmailAndPassword(email, password)
                .addOnCompleteListener { task ->
                    if (task.isSuccessful) {
                        val uid = auth.currentUser?.uid
                        if (uid != null) {
                            val user = mapOf(
                                "username" to username,
                                "phone" to phoneNumber,
                                "email" to email,
                                "password" to password
                            )
                            database.child("users").child(uid).setValue(user)
                                .addOnCompleteListener { dbTask ->
                                    if (dbTask.isSuccessful) {
                                        Toast.makeText(
                                            this,
                                            "Registrasi Berhasil!",
                                            Toast.LENGTH_SHORT
                                        ).show()
                                        val intent = Intent(this, HomeActivity::class.java)
                                        startActivity(intent)
                                        finish()
                                    } else {
                                        Toast.makeText(
                                            this,
                                            "Gagal menyimpan data: ${dbTask.exception?.message}",
                                            Toast.LENGTH_LONG
                                        ).show()
                                    }
                                }
                        } else {
                            Toast.makeText(
                                this,
                                "Gagal mendapatkan UID Pengguna.",
                                Toast.LENGTH_SHORT
                            ).show()
                        }
                    } else {
                            Toast.makeText(
                                this,
                                "Gagal Registrasi: ${task.exception?.message}",
                                Toast.LENGTH_LONG
                            ).show()
                        }
                    }
                }
        }

    private fun validateInput(
        username: String,
        password: String,
        confirmPassword : String,
        email: String,
        phoneNumber: String
    ): Boolean {
        if (username.length < 8) {
            Toast.makeText(this, "Username harus minimal 8 karakter", Toast.LENGTH_SHORT).show()
            return false
        }
        if (password.length < 8 || !password.matches("^(?=.*[A-Za-z])(?=.*\\d)[A-Za-z\\d]{8,}\$".toRegex())) {
            Toast.makeText(
                this,
                "Password minimal 8 karakter, mengandung huruf dan angka",
                Toast.LENGTH_SHORT
            ).show()
            return false
        }

        if (password != confirmPassword) {
            Toast.makeText(this,
                "Password dan Confirm Password tidak cocok!",
                Toast.LENGTH_SHORT).show()
            return false
        }

        if (!email.endsWith("@gmail.com")) {
            Toast.makeText(this, "Email harus diakhiri dengan @gmail.com", Toast.LENGTH_SHORT)
                .show()
            return false
        }

        if (phoneNumber.length !in 11..13 || !phoneNumber.all { it.isDigit() }) {
            Toast.makeText(
                this,
                "Nomor telepon harus terdiri dari 11-13 digit angka",
                Toast.LENGTH_SHORT
            ).show()
            return false
        }
        return true
    }
}