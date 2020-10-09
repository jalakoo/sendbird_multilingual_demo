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
import kotlin.collections.ArrayList


class CustomChannelActivity : ChannelActivity() {

    // Using default ChannelFragment
    override fun createChannelFragment(channelUrl: String): ChannelFragment {
        return ChannelFragment.Builder(channelUrl)
            .setItemLongClickListener { view, i, baseMessage ->
                showTranslations(
                    view,
                    i,
                    baseMessage,
                    channelUrl
                )
            }
            .build()
    }

    // Using a custom ChannelFragment to customize chat displays
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

        // Only show translations for UserMessages
        if (message !is UserMessage) {
            Log.e("CustomChannelActivity", "Oop, message was not of type UserMessage")
            return
        }

        userMessage = message
        // Convert the translations map property to a list for display
        val translations = ArrayList<String>()
        userMessage.translations.forEach{(key, value) ->
            translations.add(value)
            if (key == userLanguage){
                // Also note if we have a translation for users preferred language
                userPreferredMessage = value
            }
        }

        // Get preferred language on demand if not included above
        if (userPreferredMessage == "") {
            val targetLanguages: MutableList<String> = arrayListOf(userLanguage)
            GroupChannel.getChannel(channelUrl, GroupChannel.GroupChannelGetHandler{ channel, sendbirdException ->
                channel.translateUserMessage(userMessage, targetLanguages, BaseChannel.TranslateUserMessageHandler{ message, sendbirdException ->
                    val translatedMessages: Array<String> =
                        message.translations.values.toTypedArray()
                    showDialog("Translations", translatedMessages)
                })
            })
        } else {
            showDialog("Translations", translations.toTypedArray())
        }

    }

    private fun showDialog(title: String, translations: Array<String>) {
        val builder: AlertDialog.Builder = AlertDialog.Builder(this)
        builder.setTitle(title)
            .setItems(translations, null)
            .create()
            .show()
    }

}
