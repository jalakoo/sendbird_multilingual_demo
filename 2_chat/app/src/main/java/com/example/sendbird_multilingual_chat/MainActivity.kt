package com.example.sendbird_multilingual_chat

import android.content.Intent
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import androidx.appcompat.app.AppCompatActivity
import com.google.android.material.floatingactionbutton.FloatingActionButton
import com.sendbird.android.SendBirdException
import com.sendbird.android.User
import com.sendbird.uikit.SendBirdUIKit
import com.sendbird.uikit.activities.ChannelListActivity
import com.sendbird.uikit.adapter.SendBirdUIKitAdapter
import com.sendbird.uikit.interfaces.UserInfo
import com.sendbird.uikit.log.Logger
import com.sendbird.uikit.widgets.WaitingDialog


//import org.omg.PortableServer.IdAssignmentPolicyValue.USER_ID

const val USER_ID = "batman"
const val USER_NICKNAME = "Batman"

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        // 1. Init Sendbird
        SendBirdUIKit.init(object : SendBirdUIKitAdapter {
            override fun getAppId(): String {
                return resources.getString(R.string.sendbird_appid);
            }

            override fun getAccessToken(): String {
                return ""
            }

            override fun getUserInfo(): UserInfo {
                return object : UserInfo {
                    override fun getUserId(): String {
                        return USER_ID
                    }

                    override fun getNickname(): String {
                        return USER_NICKNAME
                    }

                    override fun getProfileUrl(): String {
                        return ""
                    }
                }
            }
        }, this)

        setContentView(R.layout.activity_main)
        setSupportActionBar(findViewById(R.id.toolbar))

        findViewById<FloatingActionButton>(R.id.fab).setOnClickListener { view ->
            // 2. Connect User
            SendBirdUIKit.connect { user: User?, e: SendBirdException? ->
                if (e != null) {
                    Logger.e(e)
                    println("Error: " + e)
                    WaitingDialog.dismiss()
                    return@connect
                }
                WaitingDialog.dismiss()
                // 3. Kick off Group Channels list UI
                startActivity(ChannelListActivity.newIntent(this))
                finish()

            }
        }
    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        // Inflate the menu; this adds items to the action bar if it is present.
        menuInflater.inflate(R.menu.menu_main, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        return when (item.itemId) {
            R.id.action_settings -> true
            else -> super.onOptionsItemSelected(item)
        }
    }
}