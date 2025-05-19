package com.example.e_commerce.ui.createAccount

import android.content.Intent
import android.graphics.*
import android.net.Uri
import android.os.Bundle
import android.text.InputType
import android.util.Base64
import android.util.Log
import android.view.Gravity
import android.widget.*
import androidx.activity.enableEdgeToEdge
import androidx.activity.result.contract.ActivityResultContracts
import androidx.appcompat.app.AppCompatActivity
import androidx.core.graphics.createBitmap
import com.example.e_commerce.R
import com.example.e_commerce.ui.ApiService.BaseApiService
import com.example.e_commerce.ui.ApiService.UserResponse
import com.example.e_commerce.ui.ApiService.UserService
import com.example.e_commerce.ui.login.LoginScreen
import okhttp3.MediaType.Companion.toMediaTypeOrNull
import okhttp3.RequestBody.Companion.toRequestBody
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class CreateAccount : AppCompatActivity() {

    private lateinit var baseApi: BaseApiService
    private lateinit var emailText: EditText
    private lateinit var passwordField: EditText
    private lateinit var mobileNoText: EditText
    private lateinit var toggleIcon: ImageView
    private lateinit var button: Button
    private lateinit var dottedCircle: FrameLayout
    private lateinit var imageView: ImageView

    private var isPasswordVisible = false
    private var base64Image = ""

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_create_account)

        initViews()
        baseApi = BaseApiService(getString(R.string.BASE_URL))

        toggleIcon.setOnClickListener { togglePasswordVisibility() }
        val imagePickerLauncher =
            registerForActivityResult(ActivityResultContracts.GetContent()) { uri: Uri? ->
                uri?.let {
                    base64Image = uriToBase64(it)
                    displayBase64Image(base64Image)
                }
            }

        dottedCircle.setOnClickListener {
            imagePickerLauncher.launch("image/*")
        }

        button.setOnClickListener {
            val email = emailText.text.toString()
            val password = passwordField.text.toString()
            val mobile = mobileNoText.text.toString()
            onClickToSubmit(email, password, mobile, base64Image)
        }
    }

    private fun initViews() {
        emailText = findViewById(R.id.emailText)
        passwordField = findViewById(R.id.passwordText)
        mobileNoText = findViewById(R.id.mobileNoText)
        toggleIcon = findViewById(R.id.toggleIcon)
        button = findViewById(R.id.button2)
        dottedCircle = findViewById(R.id.dottedCircle)
        imageView = findViewById(R.id.cameraIcon)
        button.backgroundTintList = null
    }

    private fun togglePasswordVisibility() {
        isPasswordVisible = !isPasswordVisible
        passwordField.inputType = if (isPasswordVisible) {
            InputType.TYPE_TEXT_VARIATION_VISIBLE_PASSWORD
        } else {
            InputType.TYPE_CLASS_TEXT or InputType.TYPE_TEXT_VARIATION_PASSWORD
        }
        toggleIcon.setImageResource(if (isPasswordVisible) R.drawable.eye_open else R.drawable.eye_close)
        passwordField.setSelection(passwordField.text.length)
    }

    private fun onClickToSubmit(
        email: String,
        password: String,
        mobile: String,
        base64Image: String
    ) {
        // Basic email validation
        if (!android.util.Patterns.EMAIL_ADDRESS.matcher(email).matches()) {
            showToast("Please enter a valid email address.")
            return
        }
        // Basic mobile number validation (e.g., must be 10 digits)
        if (mobile.length != 10 || !mobile.all { it.isDigit() }) {
            showToast("Please enter a valid 10-digit mobile number.")
            return
        }
        // Password validation (min 6 chars, at least 1 uppercase, 1 number, 1 special char)
        val passwordPattern =
            Regex("^(?=.*[A-Z])(?=.*\\d)(?=.*[@\$!%*?&])[A-Za-z\\d@\$!%*?&]{6,}\$")
        if (!passwordPattern.matches(password)) {
            showToast("Password must be at least 6 characters long and include one uppercase letter, one number, and one special character.")
            return
        }
        val cleanBase64 = base64Image.replace("\n", "").replace("\r", "")

        val json = """
            {
                "email": "$email",
                "password": "$password",
                "mobile": "$mobile",
                "image": "$cleanBase64"
            }
            """.trimIndent()

        val requestBody = json.toRequestBody("application/json".toMediaTypeOrNull())
        val userService = baseApi.createService(UserService::class.java)
        userService.registerUser(requestBody).enqueue(object : Callback<UserResponse> {
            override fun onResponse(call: Call<UserResponse>, response: Response<UserResponse>) {
                if (response.isSuccessful) {
                    val data = response.body()
                    if(data?.status == true){
                        val i = Intent(this@CreateAccount, LoginScreen::class.java)
                        startActivity(i)
                    }else{
                        showToast(data?.error.toString())
                        Log.d("CreateAccount", "Mobile: ${data?.error}")
                    }

                } else {
                    Log.d("CreateAccount", "error ${response.errorBody()?.string()}")
                }
            }

            override fun onFailure(call: Call<UserResponse>, t: Throwable) {
                Log.d("CreateAccount", "error -- $t")
            }
        })
    }

    private fun showToast(message: String) {
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show()
    }

    private fun uriToBase64(uri: Uri): String {
        val inputStream = contentResolver.openInputStream(uri)
        val bytes = inputStream?.readBytes() ?: return ""
        return Base64.encodeToString(bytes, Base64.DEFAULT)
    }

    private fun displayBase64Image(base64String: String) {
        val decodedBytes = Base64.decode(base64String, Base64.DEFAULT)
        val bitmap = BitmapFactory.decodeByteArray(decodedBytes, 0, decodedBytes.size)

        val params = FrameLayout.LayoutParams(
            FrameLayout.LayoutParams.MATCH_PARENT,
            FrameLayout.LayoutParams.MATCH_PARENT
        ).apply { gravity = Gravity.CENTER }

        imageView.layoutParams = params
        imageView.setImageBitmap(getCircularBitmap(bitmap))
        imageView.scaleType = ImageView.ScaleType.FIT_CENTER
    }

    private fun getCircularBitmap(bitmap: Bitmap): Bitmap {
        val size = minOf(bitmap.width, bitmap.height)
        val output = createBitmap(size, size)
        val canvas = Canvas(output)
        val paint = Paint(Paint.ANTI_ALIAS_FLAG)
        val rect = Rect(0, 0, size, size)
        val rectF = RectF(rect)

        canvas.drawOval(rectF, paint)
        paint.xfermode = PorterDuffXfermode(PorterDuff.Mode.SRC_IN)
        canvas.drawBitmap(bitmap, null, rect, paint)

        return output
    }
}
