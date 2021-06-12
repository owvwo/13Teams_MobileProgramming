package com.example.mvplayer

import android.app.AlertDialog
import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import com.bumptech.glide.Glide
import com.example.mvplayer.databinding.FragmentSearchBinding
import kotlinx.coroutines.*
import org.json.JSONObject
import org.jsoup.Jsoup
import java.net.URLEncoder


class SearchFragment : Fragment() {
    lateinit var binding: FragmentSearchBinding
    val Youtube_API_KEY = "AIzaSyBfb8TeqSPWU7atw7xDg3mvVrxurrTenbc"
    var part = "snippet"
    var type = "video"
    var maxResults = 10
    var boundurl = "https://www.googleapis.com/youtube/v3/search?"
    var url = ""
    var imgurl = ""
    var title = ""

    //videoid and macro value for intent by 이동준
    var vId = ""
    val YT_VIEW_REQUEST = 1000

    lateinit var job: Job

    val scope = CoroutineScope(Dispatchers.IO)

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentSearchBinding.inflate(layoutInflater, container, false)
        return binding!!.root

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        getThumnail()
    }

    fun getThumnail(){

        binding!!.searchbutton.setOnClickListener {
            val search_q = URLEncoder.encode(binding.serachword.text.toString() + "+MV", "UTF-8")
            url += boundurl + "q=" + search_q + "&"
            url += "part=" + part + "&"
            url += "key=" + Youtube_API_KEY + "&"
            url += "maxResults=" + maxResults + "&"
            url += "type=" + type
            runBlocking {
                job = scope.launch {
                    val doc = Jsoup.connect(url).ignoreContentType(true).execute().body()
                    val jObject = JSONObject(doc.toString())
                    val itemobject = jObject.getJSONArray("items").getJSONObject(0)
                    val snippetobject = JSONObject(itemobject.getString("snippet"))
                    val thumnailobject = JSONObject(snippetobject.getString("thumbnails"))
                    val mediumobject = JSONObject(thumnailobject.getString("medium"))
                    imgurl = mediumobject.getString("url")
                    title = snippetobject.getString("title")

                    //videoid 받아오기 by 이동준
                    val idobject =  JSONObject(itemobject.getString("id"))
                    vId = idobject.getString("videoId")
                }
                job.join()
            }

            Glide.with(this).load(imgurl).into(binding!!.thumnailImage)
            binding.titleView.text = title
            binding.playlistimg.visibility = View.VISIBLE

            //youtubeviewActivity에 intent 전달 이벤트 by 이동준
            binding.thumnailImage.setOnClickListener {
                val newIntent = Intent(context, YoutubeviewActivity::class.java)
                newIntent.putExtra("ytViewIntent", vId)
                startActivity(newIntent)
            }

            binding.playlistimg.setOnClickListener{
                val dlgBuilder = AlertDialog.Builder(getActivity())
                        .setTitle("재생목록 추가")
                        .setPositiveButton("추가"){ _, _ ->
                            Toast.makeText(activity,"추가",Toast.LENGTH_SHORT).show()
                        }
                        .setNeutralButton("취소",null)
                        .show()
            }
            url = ""
        }
    }

}