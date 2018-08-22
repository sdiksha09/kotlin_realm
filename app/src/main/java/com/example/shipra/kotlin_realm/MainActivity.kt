package com.example.shipra.kotlin_realm

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.EditText
import android.widget.Toast
import io.realm.Realm
import io.realm.RealmConfiguration
import io.realm.exceptions.RealmException
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    lateinit var etUser:EditText
    lateinit var etPass:EditText
    lateinit var etUserName:EditText
    lateinit var realm:Realm

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        Realm.init(this)
        val config = RealmConfiguration.Builder().name("user.realm").build()      //configration of real m database
        val realm=Realm.getInstance(config)

        btn_add.setOnClickListener {
            realm.beginTransaction()
            val user = realm.createObject(user::class.java, 1)
            user.setuName("diksha")
            user.setpass("1234")
            realm.commitTransaction()
            val alluser = realm.where(user::class.java).findFirst()
            var k:String=""

            var id: Long =alluser.getId()
            var userName: String = alluser.getuName()
            var password: String = alluser.getpass()
            k = k + "$id $userName $password\n\n"
            showToast("$k")













            etUser = findViewById(R.id.edt_user) as EditText
            etPass = findViewById(R.id.edt_pass) as EditText
            etUserName = findViewById(R.id.edt_viewUser)


        }


    }





    fun addUser()
    {
        realm.beginTransaction()

        try{

            var nextId:Long=realm.where(user::class.java).count()+1
            var u=realm.createObject(user::class.java,nextId)


        }catch (e:RealmException){

            Log.d("Tag",e.message)

        }
    }



    fun viewUser() {

        var realmResults = realm.where(user::class.java).contains("uName", etUserName.text.toString()).findAll()
        var k:String=""
        for (i in 0..realmResults.size-1) {
            var id: Long =realmResults.get(i).getId()
            var userName: String = realmResults.get(i).getuName()
            var password: String = realmResults.get(i).getpass()
            k = k + "$id $userName $password\n\n"
        }

        showToast("$k")

    }


    fun viewUsers() {
        var realmResults= realm.where(user::class.java).findAll()
        var k:String=""
        for(i in 0..realmResults.size)
        {
            var id:Long=realmResults.get(i).getId()
            var userName:String=realmResults.get(i).getuName()
            var password:String=realmResults.get(i).getpass()
            k= k+"$id $userName $password\n\n"
        }

        showToast("$k")


    }

    fun updateUser()
    {

        realm.executeTransaction(object :Realm.Transaction{
            override fun execute(realm: Realm?) {
                var u:user=realm!!.where(user::class.java).equalTo("uName","abc").findFirst()!!
                u.setuName("Test")
                showToast("User Updated Successfully!!")



            }


        })


    }



/*
    fun deleteUser(view:View) {
        var results: RealmResults<user> =  realm!!.where(user::class.java).equalTo("uName","abc").findFirst()!!

        realm.executeTransaction(object : Realm.Transaction {
            override fun execute(realm: Realm?) {
                results.deleteAllFromRealm()
                showToast("User deleted Successfully!!")

            }
        })
    }
*/

    private fun showToast(s:String)
    {
        Toast.makeText(applicationContext,s,Toast.LENGTH_SHORT).show()

    }

}
