package com.example.flickappmain

import android.os.AsyncTask
import android.util.Log
import org.json.JSONException
import org.json.JSONObject
import java.lang.Exception

class GetFlickrDataJson(private val listener: OnDataAvailable) : AsyncTask<String, Void, ArrayList<Photo>>() {

    interface OnDataAvailable {
        fun onDataAvailable(data: List<Photo>)
        fun onError(exception: Exception)
    }
    private val TAG = "GetFlickrDataJson"

    override fun doInBackground(vararg params: String) : ArrayList<Photo>{
        Log.d(TAG, "doInBackground start")
        val photoList = ArrayList<Photo>()
        try {
            val jsonData = JSONObject(params[0])
            val itemsArray = jsonData.getJSONArray("items")

            for (i in 0 until itemsArray.length()) {
                val jsonPhoto = itemsArray.getJSONObject(i)
                val title = jsonPhoto.getString("title")
                val author = jsonPhoto.getString("author")
                val authorId = jsonPhoto.getString("author_id")
                val tags = jsonPhoto.getString("tags")
                val jsonMedia = jsonPhoto.getJSONObject("media")
                val photoUrl = jsonMedia.getString("m")
                val link = photoUrl.replaceFirst("_m.jpg", "_b.jpg")

                val photoObject = Photo(title, author, authorId, link, tags, photoUrl)
                photoList.add(photoObject)
            }

        } catch (e: JSONException) {
            e.printStackTrace()
            Log.e(TAG, ".doInBackground json error. Error is ${e.message}")
            cancel(true)
            listener.onError(e)
        }
        return photoList
    }

    override fun onPostExecute(result: ArrayList<Photo>) {
        super.onPostExecute(result)
        Log.d(TAG, "onPostExecute start")
        listener.onDataAvailable(result)
        Log.d(TAG, "onPostExecute ends")
    }
}