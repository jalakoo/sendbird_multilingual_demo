package com.example.sendbird_multilingual_chat.custom

import android.util.Log
import com.sendbird.android.UserMessageParams
import com.sendbird.uikit.fragments.ChannelFragment
import java.util.*

class CustomChannelFragment : ChannelFragment() {

    override fun onBeforeSendUserMessage(params: UserMessageParams) {
        val languages: MutableList<String> = ArrayList()
        languages.add("en")
        languages.add("ja") // Japanese
        languages.add("ko") // Korean
        languages.add("it") // Italian
        languages.add("fr") // French
        languages.add("de") // German
        super.onBeforeSendUserMessage(params)
        params
            .setTranslationTargetLanguages(languages)
    }

    override fun onBeforeUpdateUserMessage(params: UserMessageParams) {
        val languageCode = Locale.getDefault().language
        Log.i("CustomChannelFragment", languageCode)
        val languages: MutableList<String> = ArrayList()
        languages.add(languageCode)

        super.onBeforeUpdateUserMessage(params)
        params
            .setTranslationTargetLanguages(languages)
    }

}
