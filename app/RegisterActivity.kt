package id.ac.binus.pethub2

import android.annotation.SuppressLint
import android.content.Intent
import android.media.Image
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.google.firebase.database.FirebaseDatabase

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
        val ivBackToMain = findViewById<ImageView>(R.id.ivBackToMain)

        tvGoToLogin.setOnClickListener {
            val intent = Intent(this, LoginActivity::class.java)
            startActivity(intent)

        }

        btnRegister.setOnClickListener {
            val username = etUsernameRegister.text.toString().trim()
            val password = etPasswordRegister.text.toString().trim()
            val email = etEmailRegister.text.toString().trim()
            val phoneNumber = etPhoneRegister.text.toString().trim()

            if (username.length < 8) {
                Toast.makeText(this, "Username harus minimal 8 karakter", Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            }
            if (password.length < 8 || !password.matches("^(?=.*[A-Za-z])(?=.*\\d)[A-Za-z\\d]{8,}\$".toRegex())) {
                Toast.makeText(
                    this,
                    "Password minimal 8 karakter, mengandung huruf dan angka",
                    Toast.LENGTH_SHORT
                ).show()
                return@setOnClickListener
            }

            if (!email.endsWith("@gmail.com")) {
                Toast.makeText(this, "Email harus diakhiri dengan @gmail.com", Toast.LENGTH_SHORT)
                    .show()
                return@setOnClickListener
            }

            if (phoneNumber.length !in 11..13 || !phoneNumber.all { it.isDigit() }) {
                Toast.makeText(
                    this,
                    "Nomor telepon harus terdiri dari 11-13 digit angka",
                    Toast.LENGTH_SHORT
                ).show()
                return@setOnClickListener
            }

            Toast.makeText(this, "Proses registrasi dimulai", Toast.LENGTH_SHORT).show()

            isUsernameRegistered(username) { isRegistered ->
                if (isRegistered) {
                    Toast.makeText(this, "Username sudah terdaftar", Toast.LENGTH_SHORT).show()
                } else {
                    val database = FirebaseDatabase.getInstance().reference.child("users")
                    val user = mapOf(
                        "email" to email,
                        "password" to password,
                        "phone" to phoneNumber
                    )
                    database.child(username).setValue(user).addOnCompleteListener {
                        if (it.isSuccessful) {
                            Toast.makeText(this, "Registrasi Berhasil", Toast.LENGTH_SHORT).show()
                            val intent = Intent(this, LoginActivity::class.java)
                            startActivity(intent)
                            finish()
                        } else {
                            Toast.makeText(
                                this,
                                "Gagal melakukan registrasi. Silahkan coba lagi.",
                                Toast.LENGTH_SHORT
                            ).show()
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

    private fun isUsernameRegistered(username: String, callback: (Boolean) -> Unit) {
        val database = FirebaseDatabase.getInstance().reference.child("users")
        database.child(username).get().addOnCompleteListener { task ->
            if (task.isSuccessful) {
                callback(task.result.exists())
            } else {
                callback(false)
            }
        }
    }

    }

