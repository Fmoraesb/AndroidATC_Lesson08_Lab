package com.fernandomoraes.androidatc_lesson08_lab

import android.app.Notification
import android.app.NotificationChannel
import android.app.NotificationManager
import android.content.Context
import android.os.Build
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.view.View
import android.webkit.WebViewClient
import androidx.appcompat.app.AppCompatActivity
import androidx.core.app.NotificationCompat
import androidx.core.app.NotificationManagerCompat
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    var CHANNEL_ID = "br.com.fernandomoraes"

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        //Código de criação de um canal de Notificação
        fun createNotificationChannel() {
                val name = getString(R.string.channel_name)
                val descriptionText = getString(R.string.channel_description)
                val importance = NotificationManager.IMPORTANCE_DEFAULT

                val channel = NotificationChannel(CHANNEL_ID, name, importance).apply {
                    description = descriptionText
                }
                val notificationManager: NotificationManager =
                    getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager
                notificationManager.createNotificationChannel(channel)
        }
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.menu, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        mywebview.webViewClient = WebViewClient()
                val notification = Notification
                    .Builder(this, CHANNEL_ID)
                    .setSmallIcon(R.drawable.notification_icon)
                    .setContentTitle("Android ATC Notification")
                    .setContentText("Check Android ATC new course!")
                    .setChannelId(CHANNEL_ID)

        when (item.itemId) {
            R.id.it_Courses -> {
                mywebview.loadUrl("https://www.androidatc.com")
                val notificationManager =
                    getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager
                notificationManager.notify(101, notification.build())
            }
            R.id.it_Exam -> {
                mywebview.loadUrl("https://home.pearsonvue.com/androidatc")
            }
        }
        return super.onOptionsItemSelected(item)
    }

    fun goToWeb(view: View) {
        mywebview.settings.javaScriptEnabled = true
        mywebview.settings.loadsImagesAutomatically = true
        mywebview.scrollBarStyle = View.SCROLLBARS_INSIDE_OVERLAY

        val url = edtSite.text.toString()
        mywebview.loadUrl("https://$url")
    }
}