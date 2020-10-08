package com.example.sendbird_multilingual_chat.custom

import com.sendbird.android.UserMessageParams
import com.sendbird.uikit.fragments.ChannelFragment

class CustomChannelFragment : ChannelFragment() {

    override fun onBeforeSendUserMessage(params: UserMessageParams) {
        super.onBeforeSendUserMessage(params)
        params
            .setTranslationTargetLanguages(null)
    }

    override fun onBeforeUpdateUserMessage(params: UserMessageParams) {
        super.onBeforeUpdateUserMessage(params)
        params
            .setTranslationTargetLanguages(null)
    }

}
