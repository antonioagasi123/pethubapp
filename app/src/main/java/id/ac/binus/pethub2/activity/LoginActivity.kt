package id.ac.binus.pethub2.activity

import android.annotation.SuppressLint
import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase
import id.ac.binus.pethub2.R

class LoginActivity : AppCompatActivity() {
    @SuppressLint("MissingInflatedId")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContentView(R.layout.activity_login)

        val tvLupaPassword = findViewById<TextView>(R.id.tvLupaPassword)
        val btnLogin = findViewById<Button>(R.id.btnLogin)
        val etUsername = findViewById<EditText>(R.id.etUsername)
        val etPassword = findViewById<EditText>(R.id.etPassword)
        val tvGoToRegister = findViewById<TextView>(R.id.tvGoToRegister)

        val auth = FirebaseAuth.getInstance()
        val database = FirebaseDatabase.getInstance(
            "https://pethub-6346f-default-rtdb.asia-southeast1.firebasedatabase.app"
        ).reference

        tvLupaPassword.setOnClickListener {
            val username = etUsername.text.toString().trim()

            if (username.isEmpty()) {
                Toast.makeText(this, "Masukkan username untuk reset password!", Toast.LENGTH_SHORT)
                    .show()
                return@setOnClickListener
            }

            fetchEmailByUsername(username, database) { email, error ->
                if (error != null) {
                    Toast.makeText(this, "Error: $error", Toast.LENGTH_LONG).show()
                } else if (email != null) {
                    auth.sendPasswordResetEmail(email).addOnCompleteListener { resetTask ->
                        if (resetTask.isSuccessful) {
                            Toast.makeText(
                                this,
                                "Email reset password telah dikirim ke $email!",
                                Toast.LENGTH_SHORT
                            ).show()
                        } else {
                            Toast.makeText(
                                this,
                                "Gagal mengirim email reset password: ${resetTask.exception?.message}",
                                Toast.LENGTH_LONG
                            )
                                .show()
                        }
                    }
                } else {
                    Toast.makeText(this, "Username tidak ditemukan.", Toast.LENGTH_SHORT).show()
                }
            }
        }

        btnLogin.setOnClickListener {
            val username = etUsername.text.toString().trim()
            val password = etPassword.text.toString().trim()

            if (username.isEmpty()) {
                Toast.makeText(
                    this,
                    "Username tidak boleh kosong!",
                    Toast.LENGTH_SHORT
                ).show()
                return@setOnClickListener
            }
            if (password.isEmpty()) {
                Toast.makeText(
                    this,
                    "Password tidak boleh kosong!",
                    Toast.LENGTH_SHORT
                ).show()
                return@setOnClickListener
            }
            database.child("users").orderByChild("username").equalTo(username)
                .get().addOnCompleteListener { task ->
                    if (task.isSuccessful) {
                        val result = task.result
                        if (result != null && result.childrenCount > 0) {
                            val userSnapshot = result.children.first()
                            val email = userSnapshot.child("email").value.toString()
                            val storedPassword = userSnapshot.child("password").value.toString()

                            if (storedPassword == password) {
                                auth.signInWithEmailAndPassword(email, password)
                                    .addOnCompleteListener { loginTask ->
                                        if (loginTask.isSuccessful) {
                                            Toast.makeText(
                                                this,
                                                "Login Berhasil!",
                                                Toast.LENGTH_SHORT
                                            ).show()
                                            val intent = Intent(this, HomeActivity::class.java)
                                            startActivity(intent)
                                            finish()
                                        } else {
                                            Toast.makeText(
                                                this,
                                                "Login Gagal: ${loginTask.exception?.message}",
                                                Toast.LENGTH_LONG
                                            ).show()
                                        }
                                    }
                            } else {
                                Toast.makeText(this, "Password salah!", Toast.LENGTH_SHORT).show()
                            }
                        } else {
                            Toast.makeText(
                                this,
                                "Username tidak ditemukan.",
                                Toast.LENGTH_SHORT
                            ).show()
                        }
                    } else {
                        Toast.makeText(this, "Error: ${task.exception?.message}", Toast.LENGTH_LONG)
                            .show()
                    }
                }
        }
        tvGoToRegister.setOnClickListener {
            val intent = Intent(this, RegisterActivity::class.java)
            startActivity(intent)
        }


        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main_login)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(
                systemBars.left,
                systemBars.top,
                systemBars.right,
                systemBars.bottom
            )
            insets
        }
    }

    private fun fetchEmailByUsername(
        username: String,
        database: DatabaseReference,
        callback: (email: String?, error: String?) -> Unit
    ) {
        database.child("users").orderByChild("username").equalTo(username)
            .get().addOnCompleteListener { task ->
                if (task.isSuccessful) {
                    val result = task.result
                    if (result != null && result.childrenCount > 0) {
                        val email = result.children.first().child("email").value.toString()
                        callback(email, null)
                    } else {
                        callback(null, "Username tidak ditemukan.")
                    }
                } else {
                    callback(null, task.exception?.message)
                }
            }
    }

    @SuppressLint("MissingSuperCall")
    @Deprecated("This method has been deprecated in favor of using the\n      {@link OnBackPressedDispatcher} via {@link #getOnBackPressedDispatcher()}.\n      The OnBackPressedDispatcher controls how back button events are dispatched\n      to one or more {@link OnBackPressedCallback} objects.")
    override fun onBackPressed() {
        moveTaskToBack(true)
    }
}
