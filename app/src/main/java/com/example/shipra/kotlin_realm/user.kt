package com.example.shipra.kotlin_realm

import io.realm.RealmObject
import io.realm.annotations.PrimaryKey

open class user: RealmObject()
{

    @PrimaryKey
      private var id :Long=0
      private lateinit var uName:String
      private lateinit var pass:String


    //getter and setter functions

     fun setId(id:Long)
     {
         this.id=id
     }

    fun getId():Long
    {
        return id
    }


    fun setuName(uName:String)
    {
        this.uName=uName
    }

    fun getuName():String
    {
        return uName
    }

    fun setpass(pass:String)
    {
        this.pass=pass
    }

    fun getpass():String
    {
        return pass
    }

}