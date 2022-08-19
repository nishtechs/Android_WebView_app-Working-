package nish.advancedwebview

import android.annotation.SuppressLint
import android.app.ProgressDialog
import android.net.http.SslError
import android.os.Build
import android.os.Bundle
import android.view.KeyEvent
import android.view.View
import android.webkit.SslErrorHandler
import android.webkit.WebChromeClient
import android.webkit.WebView
import android.webkit.WebViewClient
import android.widget.ProgressBar
import androidx.appcompat.app.AppCompatActivity
import kotlinx.android.synthetic.main.activity_main.*


class MainActivity : AppCompatActivity() {

    private lateinit var webview: WebView

    private var mProgressDialog: ProgressDialog? = null

    companion object {
        const val PAGE_URL = "https://whatoplay.com/"
        const val PAGE_URL2 = "https://www.instructables.com/"
        const val PAGE_URL3 = "https://ncs.io/music"
        const val PAGE_URL4 = "https://digitaldefynd.com/"
        const val Email_URL = "http://Webmail1.hostinger.in"
        const val MAX_PROGRESS = 100

        /*fun newIntent(context: Context, pageUrl: String): Intent {
            val intent = Intent(context, WebActivity::class.java)
            intent.putExtra(PAGE_URL, pageUrl)
            return intent
        }*/


    }
    private lateinit var pageUrl: String


    @SuppressLint("SetJavaScriptEnabled")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        webview = findViewById(R.id.webview)
        // get pageUrl from String
       /* pageUrl = intent.getStringExtra(PAGE_URL)
            ?: throw IllegalStateException("$PAGE_URL")*/
        loadUrl(PAGE_URL3)
        initWebView()
        setWebClient()
        handlePullToRefresh()



   /*     showProgressDialog()



        val url = "https://whatoplay.com/"

        webview.loadUrl(url);
        webview.settings.javaScriptEnabled = true
        webview.settings.loadWithOverviewMode = true
        webview.settings.useWideViewPort = true

        webview.settings.domStorageEnabled = true
        webview.webViewClient = object : WebViewClient() {
            override
            fun onReceivedSslError(view: WebView?, handler: SslErrorHandler?, error: SslError?) {
                handler?.proceed()
            }
        }*/
    }



    private fun handlePullToRefresh() {
    }
    @SuppressLint("SetJavaScriptEnabled")
    private fun initWebView() {
        webview.settings.javaScriptEnabled = true
        webview.settings.loadWithOverviewMode = true
        webview.settings.useWideViewPort = true
        webview.settings.domStorageEnabled = true
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN_MR1) {
            webview.settings.mediaPlaybackRequiresUserGesture = false
        }

        webview.webViewClient = object : WebViewClient() {
            override
            fun onReceivedSslError(view: WebView?, handler: SslErrorHandler?, error: SslError?) {
                handler?.proceed()
            }
        }
    }
    private fun setWebClient() {
        webview.webChromeClient = object : WebChromeClient() {
            override fun onProgressChanged(
                view: WebView,
                newProgress: Int
            ) {
                super.onProgressChanged(view, newProgress)
                progressBar.progress = newProgress
                if (newProgress < MAX_PROGRESS && progressBar.visibility == ProgressBar.GONE) {
                    progressBar.visibility = ProgressBar.VISIBLE

                    //showProgressDialog()
                }
                if (newProgress == MAX_PROGRESS) {
                    progressBar.visibility = ProgressBar.GONE
                    webview.visibility = View.VISIBLE
                    //hideProgressDialog()
                }
            }
        }
    }
    override fun onKeyDown(keyCode: Int, event: KeyEvent?): Boolean {
        // Check if the key event was the Back button and if there's history
        if (keyCode == KeyEvent.KEYCODE_BACK && webview.canGoBack()) {
            webview.goBack()
            return true
        }
        // If it wasn't the Back key or there's no web page history, exit the activity)
        return super.onKeyDown(keyCode, event)
    }
    private fun loadUrl(pageUrl: String) {
        webview.loadUrl(pageUrl)
    }


    private fun hideProgressDialog() {
        mProgressDialog!!.dismiss()
    }

    private fun showProgressDialog() {
        mProgressDialog = ProgressDialog(this)
        mProgressDialog!!.dismiss()
        mProgressDialog!!.setCanceledOnTouchOutside(false)
        mProgressDialog!!.setMessage("Please Wait...")
        mProgressDialog!!.isIndeterminate = true
        mProgressDialog!!.show()
    }

    override fun onResume() {
        webview.onResume();
        super.onResume()
    }

    override fun onPause() {
        webview.onPause();
        super.onPause()
    }

    override fun onBackPressed() {
     if(webview.canGoBack()){
        webview.goBack();
     }else{
         finish()
     }
        super.onBackPressed()
    }

    override fun onDestroy() {
        webview.destroy();

        super.onDestroy();
    }

}


