package com.example.sendbird_multilingual_chat.custom

import android.net.Uri
import com.sendbird.android.BaseMessage
import com.sendbird.android.FileMessageParams
import com.sendbird.android.UserMessageParams
import com.sendbird.uikit.fragments.ChannelFragment

class CustomChannelFragment : ChannelFragment() {
    var isHighlightMode = false

    override fun onBeforeSendUserMessage(params: UserMessageParams) {
        super.onBeforeSendUserMessage(params)
        params.setCustomType(type)
            .setData(null)
            .setMentionedUserIds(null)
            .setMentionedUsers(null)
            .setMentionType(null)
            .setMetaArrays(null)
            .setParentMessageId(0)
            .setPushNotificationDeliveryOption(null)
            .setTranslationTargetLanguages(null)
    }

    override fun onBeforeSendFileMessage(params: FileMessageParams) {
        super.onBeforeSendFileMessage(params)
        params.setCustomType(type)
            .setData(null)
            .setMentionedUserIds(null)
            .setMentionedUsers(null)
            .setMentionType(null)
            .setMetaArrays(null)
            .setParentMessageId(0)
            .setPushNotificationDeliveryOption(null)
    }

    override fun onBeforeUpdateUserMessage(params: UserMessageParams) {
        super.onBeforeUpdateUserMessage(params)
        params.setCustomType(type)
            .setData(null)
            .setMentionedUserIds(null)
            .setMentionedUsers(null)
            .setMentionType(null)
            .setMetaArrays(null)
            .setParentMessageId(0)
            .setPushNotificationDeliveryOption(null)
            .setTranslationTargetLanguages(null)
    }

    override fun sendUserMessage(params: UserMessageParams) {
        super.sendUserMessage(params)
    }

    override fun sendFileMessage(uri: Uri) {
        super.sendFileMessage(uri)
    }

    override fun updateUserMessage(messageId: Long, params: UserMessageParams) {
        super.updateUserMessage(messageId, params)
    }

    override fun deleteMessage(message: BaseMessage) {
        super.deleteMessage(message)
    }

    override fun resendMessage(message: BaseMessage) {
        super.resendMessage(message)
    }

    private val type: String?
        private get() {
            var type: String? = null
            if (isHighlightMode) {
                type =  "highlight"
            }
            return type
        }
}
