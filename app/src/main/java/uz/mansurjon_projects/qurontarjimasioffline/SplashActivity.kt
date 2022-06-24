package uz.mansurjon_projects.qurontarjimasioffline

import android.annotation.SuppressLint
import android.app.PendingIntent.getActivity
import android.content.Intent
import android.graphics.Color
import android.os.Bundle
import android.os.Handler
import android.view.View
import android.view.animation.AnimationUtils
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.constraintlayout.widget.ConstraintSet

@SuppressLint("CustomSplashScreen")
class SplashActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash)
        supportActionBar?.hide()
        window?.statusBarColor = Color.TRANSPARENT

        running()

    }

    private fun running() {

        val tvBottom = findViewById<TextView>(R.id.tvBottom)
        val tv = findViewById<TextView>(R.id.tv)
        val tv1 = findViewById<TextView>(R.id.tv1)
        val tv2 = findViewById<TextView>(R.id.tv2)
        val tv3 = findViewById<TextView>(R.id.tv3)
        val tv4 = findViewById<TextView>(R.id.tv4)
        val tv5 = findViewById<TextView>(R.id.tv5)
        val tv6 = findViewById<TextView>(R.id.tv6)
        val tv7 = findViewById<TextView>(R.id.tv7)
        val tv8 = findViewById<TextView>(R.id.tv8)
        val tv9 = findViewById<TextView>(R.id.tv9)
        val tv10 = findViewById<TextView>(R.id.tv10)
        val tv11 = findViewById<TextView>(R.id.tv11)
        val animation = AnimationUtils.loadAnimation(this, R.anim.animation)
        val animation1 = AnimationUtils.loadAnimation(this, R.anim.animation1)
        val animation2 = AnimationUtils.loadAnimation(this, R.anim.animation2)
        val animation3 = AnimationUtils.loadAnimation(this, R.anim.animation3)
        val animation4 = AnimationUtils.loadAnimation(this, R.anim.animation4)
        val animation5 = AnimationUtils.loadAnimation(this, R.anim.animation5)
        val animation6 = AnimationUtils.loadAnimation(this, R.anim.animation6)
        val animation7 = AnimationUtils.loadAnimation(this, R.anim.animation7)
        val animation8 = AnimationUtils.loadAnimation(this, R.anim.animation8)
        val animation9 = AnimationUtils.loadAnimation(this, R.anim.animation9)
        val animation10 = AnimationUtils.loadAnimation(this, R.anim.animation10)
        val animation11 = AnimationUtils.loadAnimation(this, R.anim.animation11)
        val anim = AnimationUtils.loadAnimation(this, R.anim.alpha)

        animation.reset()
        tv.startAnimation(animation)
        tv1.startAnimation(animation1)
        tv2.startAnimation(animation2)
        tv3.startAnimation(animation3)
        tv4.startAnimation(animation4)
        tv5.startAnimation(animation5)
        tv6.startAnimation(animation6)
        tv7.startAnimation(animation7)
        tv8.startAnimation(animation8)
        tv9.startAnimation(animation9)
        tv10.startAnimation(animation10)
        tv11.startAnimation(animation11)

        tvBottom.startAnimation(anim)

        Handler().postDelayed(Runnable {
            startActivity(Intent(this, MainActivity::class.java))
            finish()
        }, 6000)

    }


}