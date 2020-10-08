package com.example.sendbird_multilingual_chat.custom

import android.util.Log
import com.sendbird.android.UserMessageParams
import com.sendbird.uikit.fragments.ChannelFragment
import java.util.*

class CustomChannelFragment : ChannelFragment() {

    override fun onBeforeSendUserMessage(params: UserMessageParams) {
        super.onBeforeSendUserMessage(params)
        val languages: MutableList<String> = ArrayList()
        languages.add("en")
        languages.add("it") // Italian
        languages.add("fr") // French
        languages.add("de") // German
        params
            .setTranslationTargetLanguages(languages)
    }

    override fun onBeforeUpdateUserMessage(params: UserMessageParams) {
        super.onBeforeUpdateUserMessage(params)
        val languageCode = Locale.getDefault().language
        Log.i("CustomChannelFragment-b", languageCode)
        val languages: MutableList<String> = ArrayList()
        languages.add(languageCode)
        params
            .setTranslationTargetLanguages(languages)

    }

    override fun updateUserMessage(messageId: Long, params: UserMessageParams) {
        super.updateUserMessage(messageId, params)
        val languageCode = Locale.getDefault().language
        Log.i("CustomChannelFragment-u", languageCode)
        val languages: MutableList<String> = ArrayList()
        languages.add(languageCode)
        params
            .setTranslationTargetLanguages(languages)
    }

}
