package com.xiaowu.myself.utils

import android.util.Log

/**
 * Created by flyinbed on 17/11/10.
 */
class ObtainEncrys {

    companion object {

        fun getEncry(map: Map<String, String>): String {
            val stringBuffer = StringBuffer()
            val iterator = map.entries.iterator()
            var i = 0
            while (iterator.hasNext()) {
                val next = iterator.next()
                if (i == 0) {
                    stringBuffer.append(next.key + "=" + next.value)
                } else {
                    stringBuffer.append("&" + next.key + "=" + next.value)
                }
                i++
            }
            val encry = stringBuffer.toString() + "9q238*whsdkj18374skdh&^*&^jksd(23z7^&*12ksjSKW9"
            //        String encry = stringBuffer.toString()+MD5;
            Log.e("encry", encry)
            val md = Md5Utils.md5(encry)
            Log.e("md", md)
            return md
        }
    }
}