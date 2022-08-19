package nish.advancedwebview

import android.content.Intent
import android.os.Bundle
import android.os.Handler
import androidx.appcompat.app.AppCompatActivity


class Splash : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash)


        Handler().postDelayed({
            val io = Intent(this@Splash, MainActivity::class.java)
            this@Splash.startActivity(io)
            finish()
        }, 2000)



    }
}