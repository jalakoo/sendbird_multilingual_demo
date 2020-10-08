package com.example.sendbird_multilingual_chat.custom

import android.app.AlertDialog
import android.util.Log
import android.view.View
import com.sendbird.android.BaseChannel
import com.sendbird.android.BaseMessage
import com.sendbird.android.GroupChannel
import com.sendbird.android.UserMessage
import com.sendbird.uikit.activities.ChannelActivity
import com.sendbird.uikit.fragments.ChannelFragment
import java.util.*


class CustomChannelActivity : ChannelActivity() {

    // Using default ChannelFragment
    override fun createChannelFragment(channelUrl: String): ChannelFragment {
        return ChannelFragment.Builder(channelUrl)
            .setItemLongClickListener { view, i, baseMessage -> showTranslations(view, i, baseMessage, channelUrl) }
            .build()
    }

    // Using a custom ChannelFragment
//    private var customChannelFragment: CustomChannelFragment? = null
//    override fun createChannelFragment(channelUrl: String): ChannelFragment {
//        customChannelFragment = CustomChannelFragment()
//        return ChannelFragment.Builder(channelUrl)
//            .setCustomChannelFragment<ChannelFragment>(customChannelFragment)
//            .setItemLongClickListener { view, i, baseMessage -> showTranslations(view, i, baseMessage, channelUrl) }
//            .build()
//    }


    private fun showTranslations(v: View, i: Int, message: BaseMessage, channelUrl: String) {
        var userMessage: UserMessage? = null
        val userLanguage = Locale.getDefault().language
        var userPreferredMessage = ""

        if (message is UserMessage) {
            userMessage = message
            val translations = arrayOfNulls<String>(userMessage.translations.size)

            // Pulling list of pre-translated messages from sender
            var c = 0
            for (translation in userMessage.translations.keys) {
                val translatedMessage = userMessage.translations[translation]
                translations[c] = translatedMessage
                translatedMessage?.let {
                    if (translation == userLanguage){
                        userPreferredMessage = translatedMessage
                    }
                }
                c++
            }

            // Get preferred language translation on the fly if not included in the original message translation options
            if (userPreferredMessage == "") {
                val languages: MutableList<String> = ArrayList()
                languages.add(userLanguage)
                val nestedHandler =
                    BaseChannel.TranslateUserMessageHandler { message, sendBirdException ->
                        run {
                            val translatedMessages: Array<String> =
                                message.translations.values.toTypedArray()
                            showDialog("Translations", translatedMessages)
                        }
                    }
                val handler = GroupChannel.GroupChannelGetHandler { channel, sendBirdException ->
                    Log.i("CustomChannelActivity", "GroupChannelGetHandler")
                    channel.translateUserMessage(userMessage, languages, nestedHandler)
                }
                GroupChannel.getChannel(channelUrl, handler)
            }
        }
    }

    private fun showDialog(title: String, translations: Array<String>){
        val builder: AlertDialog.Builder = AlertDialog.Builder(this)
        builder.setTitle(title)
            .setItems(translations, null)
            .create()
            .show()
    }

}
