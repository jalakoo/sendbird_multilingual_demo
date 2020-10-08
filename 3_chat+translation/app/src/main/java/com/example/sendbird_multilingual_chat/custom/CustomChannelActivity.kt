package com.example.sendbird_multilingual_chat.custom

import com.sendbird.uikit.activities.ChannelActivity
import com.sendbird.uikit.fragments.ChannelFragment

class CustomChannelActivity : ChannelActivity() {
    private var customChannelFragment: CustomChannelFragment? = null
    override fun createChannelFragment(channelUrl: String): ChannelFragment {
        customChannelFragment = CustomChannelFragment()
        return ChannelFragment.Builder(channelUrl)
            .setCustomChannelFragment<ChannelFragment>(customChannelFragment)
            .build()
    }

}
