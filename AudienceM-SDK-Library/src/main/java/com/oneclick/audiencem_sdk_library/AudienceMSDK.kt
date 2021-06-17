package com.oneclick.audiencem_sdk_library

import android.content.Context
import android.os.AsyncTask
import android.util.Log
import com.google.gson.*
import com.segment.analytics.Analytics
import com.segment.analytics.Properties
import org.json.JSONObject


class AudienceMSDK(var mContext: Context, var AMKey: String) : AsyncTask<Void?, Void?, String?>() {

    fun AMInit(AMName:String, AMData: JSONObject) {
        val propertiesData = toProperties(AMData)

        val analytics = Analytics.Builder(mContext, "BIPs9934EYbVKIU8zp86wBxM6JE0hhOn")
                .trackApplicationLifecycleEvents() // Enable this to record certain application events automatically!
                .recordScreenViews() // Enable this to record screen views automatically!
                .build()
        Analytics.setSingletonInstance(analytics);

        Analytics.with(mContext).track(AMName, propertiesData)
    }

    fun AMTrack(AMName:String, AMData: JSONObject) {
        val propertiesData = toProperties(AMData)

        Analytics.with(mContext).track(AMName, propertiesData)
    }


    fun toProperties(JsonData: JSONObject) : Properties {
        JsonData.put("appKey",AMKey)

        val propertiesData = Properties()

        val parser = JsonParser()
        val element: JsonElement = parser.parse(JsonData.toString())
        val obj: JsonObject = element.getAsJsonObject() //since you know it's a JsonObject
        val entries: Set<Map.Entry<String?, JsonElement?>> = obj.entrySet() //will return members of your object

        for ((key, value) in entries) {
            val stringCheck: Int = java.lang.String.valueOf(value).indexOf("\"")
            if (stringCheck == 0) {
                var propertiesDataValue: String = java.lang.String.valueOf(value)
                propertiesDataValue = propertiesDataValue.replaceFirst("\"".toRegex(), "")
                propertiesDataValue = propertiesDataValue.substring(0, propertiesDataValue.lastIndexOf("\""))
                propertiesData.putValue(key, propertiesDataValue)
            } else {
                propertiesData.putValue(key, value)
            }
        }

        return propertiesData
    }

    override fun doInBackground(vararg params: Void?): String? {
        TODO("Not yet implemented")
    }
}


