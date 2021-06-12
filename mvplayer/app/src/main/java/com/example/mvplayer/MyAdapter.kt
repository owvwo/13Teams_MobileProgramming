package com.example.mvplayer

import android.app.Activity
import android.app.AlertDialog
import android.content.Context
import android.content.Intent
import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.Toast
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.mvplayer.databinding.RowBinding

class MyAdapter(val items:ArrayList<MyData>): RecyclerView.Adapter<MyAdapter.MyViewHolder>() {

    lateinit var thiscontext: Context
    lateinit var thisactivity: Activity

    var imgurl = ""
    val imgurl1 = "https://i.ytimg.com/vi/"
    val imgurl2 = "/mqdefault.jpg"

    inner class MyViewHolder(val binding: RowBinding): RecyclerView.ViewHolder(binding.root){
        init{
        }
    }

    fun clear(){
        items.clear()
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyAdapter.MyViewHolder {
        val view = RowBinding.inflate(LayoutInflater.from(parent.context),parent, false)
        return MyViewHolder(view)
    }

    override fun getItemCount(): Int {
        return items.size
    }

    override fun onBindViewHolder(holder: MyAdapter.MyViewHolder, position: Int) {
        holder.binding.NoText.text = (position+1).toString()
        imgurl = imgurl1 + items[position].vidoeId.toString() + imgurl2
        Glide.with(thiscontext).load(imgurl).into(holder.binding.thumnailimg)
        holder.binding.newstitle.text = items[position].songtitle
        holder.binding.singer.text = items[position].singer
        holder.binding.videoId.text = items[position].vidoeId.toString()

        //recyclerView 썸네일 이미지 클릭 후 인텐트 전달 이벤트 by 이동준
        holder.binding.thumnailimg.setOnClickListener {
            val newIntent = Intent(thiscontext, YoutubeviewActivity::class.java)
            newIntent.putExtra("ytViewIntent", items[position].vidoeId)
            thisactivity.startActivity(newIntent)
        }

        holder.binding.playlistimg.setOnClickListener{
            val dlgBuilder = AlertDialog.Builder(thisactivity)
                    .setTitle("재생목록 추가")
                    .setPositiveButton("추가"){ _, _ ->
                        Toast.makeText(thisactivity,"추가",Toast.LENGTH_SHORT).show()
                    }
                    .setNeutralButton("취소",null)
                    .show()
        }
    }
}