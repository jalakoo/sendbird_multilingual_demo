package com.example.sendbird_multilingual_chat.custom

import android.util.Log
import com.sendbird.android.BaseChannel
import com.sendbird.android.BaseMessage
import com.sendbird.android.SendBird
import com.sendbird.android.SendBird.ChannelHandler
import com.sendbird.android.UserMessage
import com.sendbird.uikit.activities.ChannelActivity
import com.sendbird.uikit.activities.ChannelListActivity
import com.sendbird.uikit.fragments.ChannelListFragment
import java.util.*


public class CustomChannelListActivity : ChannelListActivity() {
    var customChannelListActivity: CustomChannelListActivity? = null

    override fun createChannelListFragment(): ChannelListFragment{
        customChannelListActivity = CustomChannelListActivity()
        return ChannelListFragment.Builder()
            .setUseHeader(true)
            .setItemClickListener { view, i, groupChannel ->
                val intent = ChannelActivity.newIntentFromCustomActivity(
                    this,
                    CustomChannelActivity::class.java,
                    groupChannel.url
                )
                startActivity(intent)
            }
            .build()
    }
}