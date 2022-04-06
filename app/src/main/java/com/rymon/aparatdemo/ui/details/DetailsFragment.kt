package com.rymon.aparatdemo.ui.details

import android.os.Bundle
import android.view.View
import android.view.Window
import android.view.WindowManager
import android.webkit.WebChromeClient
import android.webkit.WebSettings
import android.webkit.WebView
import android.webkit.WebViewClient
import androidx.appcompat.widget.Toolbar
import androidx.core.view.isVisible
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.navArgs
import com.google.android.material.bottomnavigation.BottomNavigationView
import com.rymon.aparatdemo.R
import com.rymon.aparatdemo.databinding.FragmentDetailsBinding
import com.rymon.aparatdemo.ui.search.SearchFragmentArgs
import com.rymon.aparatdemo.utils.slideVisibility


class DetailsFragment : Fragment(R.layout.fragment_details) {

    private val args: DetailsFragmentArgs by navArgs()
    private var videoFrameUrl: String? = null
    private lateinit var bottomNavigationView: BottomNavigationView
    private lateinit var toolBar: Toolbar

    override fun onDetach() {
        super.onDetach()
        requireActivity().window.clearFlags(WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS)

        toolBar.slideVisibility(View.VISIBLE,1500)
        bottomNavigationView.slideVisibility(View.VISIBLE,1700)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        requireActivity().window.addFlags(WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS)
        val binding = FragmentDetailsBinding.bind(view)

        args.videoFrame?.let { videoFrame ->
            videoFrameUrl = videoFrame
        }
        binding.apply {
            videoFrameUrl?.let {
                try {
                    bottomNavigationView = activity?.findViewById(R.id.bottom_nav)!!
                    toolBar = activity?.findViewById(R.id.toolbar)!!

                } catch (e: Exception) {
                    //can't find
                }
                loadVideo(webView, videoFrameUrl!!)
                toolBar.slideVisibility(View.GONE,1500)
                bottomNavigationView.slideVisibility(View.GONE,1500)
            }
//            showError()
        }
/*
        binding.apply {
//            val photo = args.photo
            val photo = null

            Glide.with(this@DetailsFragment)
                .load(photo.urls.full)
                .error(R.drawable.ic_error)
                .listener(object : RequestListener<Drawable> {
                    override fun onLoadFailed(
                        e: GlideException?,
                        model: Any?,
                        target: Target<Drawable>?,
                        isFirstResource: Boolean
                    ): Boolean {
                        progressBar.isVisible = false
                        return false
                    }

                    override fun onResourceReady(
                        resource: Drawable?,
                        model: Any?,
                        target: Target<Drawable>?,
                        dataSource: DataSource?,
                        isFirstResource: Boolean
                    ): Boolean {
                        progressBar.isVisible = false
                        textViewCreator.isVisible = true
                        textViewDescription.isVisible = photo.description != null
                        return false
                    }
                })
                .into(imageView)

            textViewDescription.text = photo.description

            val uri = Uri.parse(photo.user.attributionUrl)
            val intent = Intent(Intent.ACTION_VIEW, uri)

            textViewCreator.apply {
                text = "Photo by ${photo.user.name} on Unsplash"
                setOnClickListener {
                    context.startActivity(intent)
                }
                paint.isUnderlineText = true
            }
        }
    */
    }

    private fun showError() {
        TODO("Not yet implemented")
    }

    private fun loadVideo(webView: WebView, vid_url: String) {

        webView.webViewClient = WebViewClient()
        webView.settings.javaScriptEnabled = true
        webView.settings.javaScriptCanOpenWindowsAutomatically = true
        webView.settings.pluginState = WebSettings.PluginState.ON
        webView.settings.mediaPlaybackRequiresUserGesture = false
        webView.webChromeClient = WebChromeClient()
        webView.loadUrl(vid_url)
    }
}