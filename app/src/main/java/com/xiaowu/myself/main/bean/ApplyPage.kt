package com.xiaowu.myself.main.bean

import android.os.Parcel
import android.os.Parcelable

/**
 * Created by flyinbed on 17/11/30.
 */

class ApplyPage constructor(parcel: Parcel) : Parcelable {

    /**
     * id : 5
     * agent_id : 4
     * apply_num : 200
     * apply_date : 2017-11-29
     * do_status : 0
     * reject_content : null
     * express_num : 0
     * express_name : null
     * express_order : null
     * express_date : null
     * created : 2017-11-29 10:32:12
     */

    var id: String? = null
    var agent_id: String? = null
    var apply_num: String? = null
    var apply_date: String? = null
    var do_status: String? = null
    var reject_content: String? = null
    var express_num: String? = null
    var express_name: String? = null
    var express_order: String? = null
    var express_date: String? = null
    var created: String? = null
    var check_date: String? = null

    var confirm_date: String? = null

    init {
        id = parcel.readString()
        agent_id = parcel.readString()
        apply_num = parcel.readString()
        apply_date = parcel.readString()
        do_status = parcel.readString()
        reject_content = parcel.readString()
        express_num = parcel.readString()
        express_name = parcel.readString()
        express_order = parcel.readString()
        express_date = parcel.readString()
        created = parcel.readString()
        check_date = parcel.readString()
        confirm_date = parcel.readString()
    }

    override fun describeContents(): Int {
        return 0
    }

    override fun writeToParcel(dest: Parcel, flags: Int) {
        dest.writeString(id)
        dest.writeString(agent_id)
        dest.writeString(apply_num)
        dest.writeString(apply_date)
        dest.writeString(do_status)
        dest.writeString(reject_content)
        dest.writeString(express_num)
        dest.writeString(express_name)
        dest.writeString(express_order)
        dest.writeString(express_date)
        dest.writeString(created)
        dest.writeString(check_date)
        dest.writeString(confirm_date)
    }

    companion object {

        val CREATOR: Parcelable.Creator<ApplyPage> = object : Parcelable.Creator<ApplyPage> {
            override fun createFromParcel(parcel: Parcel): ApplyPage {
                return ApplyPage(parcel)
            }

            override fun newArray(size: Int): Array<ApplyPage?> {
                return arrayOfNulls(size)
            }
        }
    }
}
