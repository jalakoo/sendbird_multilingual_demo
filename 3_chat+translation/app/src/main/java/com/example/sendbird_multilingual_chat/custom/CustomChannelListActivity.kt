package com.example.sendbird_multilingual_chat.custom

import com.sendbird.uikit.activities.ChannelListActivity
import com.sendbird.uikit.fragments.ChannelListFragment

public class CustomChannelListActivity : ChannelListActivity() {
    var customChannelListActivity: CustomChannelListActivity? = null

    override fun createChannelListFragment(): ChannelListFragment{
        customChannelListActivity = CustomChannelListActivity()
        return ChannelListFragment.Builder()
            .setUseHeader(true)
            .build()
    }

}