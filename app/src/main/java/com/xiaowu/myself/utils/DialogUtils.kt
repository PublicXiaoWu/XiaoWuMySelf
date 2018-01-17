package com.xiaowu.myself.utils

import android.app.Dialog
import android.content.Context
import android.widget.TextView
import android.widget.Toast
import com.xiaowu.myself.R
import kotlinx.android.synthetic.main.frist_fragment.view.*


/**
 * 作者：Administrator on 2017/8/31 14:49
 * 邮箱：zhanghuaiha@gmail.com
 * dialog工具类
 */

//提示dialog
fun Context.TsDialog(msg: String, flag: Boolean) {
    val progressDialog = Dialog(this, R.style.progress_dialog)
    progressDialog.setContentView(R.layout.dialog_ts)
    progressDialog.setCancelable(true)
    progressDialog.setCanceledOnTouchOutside(flag)
    progressDialog.window!!.setBackgroundDrawableResource(android.R.color.transparent)
    val tv_msg =progressDialog.findViewById<TextView>(R.id.id_tv_loadingmsg)
    val save = progressDialog.findViewById<TextView>(R.id.dialog_save)
    save.setOnClickListener {
        progressDialog.dismiss()
    }
    tv_msg.text = msg
    progressDialog.show()
}

//加载等待dialog
fun Context.loadDialog(msg: String, flag: Boolean): Dialog {
    val progressDialog = Dialog(this, R.style.progress_dialog)
    progressDialog.setContentView(R.layout.dialog_loading)
    progressDialog.setCancelable(true)
    progressDialog.setCanceledOnTouchOutside(flag)
    progressDialog.window!!.setBackgroundDrawableResource(android.R.color.transparent)
    val tv_msg = progressDialog.findViewById<TextView>(R.id.id_tv_loadingmsg)
    tv_msg.text = msg
    return progressDialog
}

//强大的Toast
fun Context.showToast(message: String): Toast {
    var toast: Toast = Toast.makeText(this, message, Toast.LENGTH_SHORT)
//    toast.setGravity(Gravity.CENTER,0,0)
    toast.show()
    return toast
}

