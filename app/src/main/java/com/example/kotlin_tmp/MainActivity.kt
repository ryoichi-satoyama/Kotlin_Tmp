package com.example.kotlin_tmp

import android.Manifest
import android.app.Activity
import android.app.DatePickerDialog
import android.app.TimePickerDialog
import android.content.ContentValues
import android.content.Intent
import android.content.pm.PackageManager
import android.graphics.Bitmap
import android.net.Uri
import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.StrictMode
import android.provider.MediaStore
import android.util.Log
import android.view.View
import android.widget.EditText
import kotlinx.android.synthetic.main.activity_main.*
import java.time.LocalDateTime
import kotlin.math.min

class MainActivity : AppCompatActivity() {
    var uri: Uri? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

//        val builder = StrictMode.ThreadPolicy.Builder()
//        StrictMode.setThreadPolicy(builder.permitAll().build())
        StrictMode.setThreadPolicy(StrictMode.ThreadPolicy.Builder().permitAll().build())

        contentButton.setOnClickListener {
//            if (Build.VERSION.SDK_INT > Build.VERSION_CODES.M) {
//                if (checkSelfPermission(Manifest.permission.WRITE_EXTERNAL_STORAGE) == PackageManager.PERMISSION_GRANTED) {
//                    getContent()
//                } else {
//                    requestPermissions(arrayOf(Manifest.permission.WRITE_EXTERNAL_STORAGE), 0)
//                }
//            } else {
//                getContent()
//            }
//            getContent()
        }

//        dateEditText.setOnClickListener {
//            showDatePickerDialog(it)
//        }
//
//        timeEditText.setOnClickListener { showTimePickerDialog(it) }

//        imageView.setImageResource(R.drawable.flower)
        val departments = Json.get<List<Departments>, MutableList<Departments>>("departments")
        Log.d("TAG", departments.toString())
    }

//    override fun onRequestPermissionsResult(requestCode: Int, permissions: Array<out String>, grantResults: IntArray) {
//        super.onRequestPermissionsResult(requestCode, permissions, grantResults)
//        when(requestCode) {
//            0 -> if(grantResults[0] == PackageManager.PERMISSION_GRANTED) {
//                getContent()
//            }
//        }
//    }

    private fun getContent() {
//        val intent = Intent(Intent.ACTION_GET_CONTENT)
//        intent.type = "image/*"
//        startActivityForResult(intent, 1)

//        val filename = System.currentTimeMillis().toString() + ".jpeg"
//        val values = ContentValues()
//        values.put(MediaStore.Images.Media.TITLE, filename)
//        values.put(MediaStore.Images.Media.MIME_TYPE, "image/jpeg")
//        uri = contentResolver.insert(MediaStore.Images.Media.EXTERNAL_CONTENT_URI, values)
//        val intent = Intent(MediaStore.ACTION_IMAGE_CAPTURE)
//        intent.putExtra(MediaStore.EXTRA_OUTPUT, uri)
//        startActivityForResult(intent, 1)

        val intent = Intent(MediaStore.ACTION_IMAGE_CAPTURE)
        startActivityForResult(intent, 1)


    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (requestCode == 1) {
            if (resultCode == Activity.RESULT_OK) {
//                val imageUri = if(data == null || data.data == null) uri else data.data
//                imageView.setImageURI(imageUri)
                imageView.setImageBitmap(data?.extras?.get("data") as Bitmap)
            }
        }
    }

    private fun showDatePickerDialog(v: View) {
        val now = LocalDateTime.now()
        val editText = v as EditText
        DatePickerDialog(this,
                DatePickerDialog.OnDateSetListener { view, year, month, dayOfMonth ->
                    editText.setText("$year-${f(2, month + 1)}-${f(2, dayOfMonth)}")
                }, now.year, now.monthValue - 1, now.dayOfMonth).show()
    }

    //    private fun showTimePickerDialog(v: View) {
//        val now = LocalDateTime.now()
//        val editText = v as EditText
//        TimePickerDialog(this,
//        TimePickerDialog.OnTimeSetListener { view, hourOfDay, minute ->
//            editText.setText("${f(2,hourOfDay)}:${f(2, minute)}")
//        }, now.hour, now.minute,false).show()
//    }
    private fun showTimePickerDialog(v: View) {
        val now = LocalDateTime.now()
        val editText = v as EditText
        TimePickerDialog(this,
                { view, hourOfDay, minute -> editText.setText("${f(2, hourOfDay)}:${f(2, minute)}") },
                now.hour, now.minute, false).show()
    }
}

fun f(digit: Int, num: Int): String {
    return String.format("%0${digit}d", num)
}