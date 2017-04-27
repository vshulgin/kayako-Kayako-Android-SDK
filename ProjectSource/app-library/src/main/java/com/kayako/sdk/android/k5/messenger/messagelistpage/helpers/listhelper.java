package com.kayako.sdk.android.k5.messenger.messagelistpage.helpers;

import com.kayako.sdk.android.k5.common.adapter.BaseListItem;
import com.kayako.sdk.android.k5.common.adapter.messengerlist.Attachment;
import com.kayako.sdk.android.k5.common.adapter.messengerlist.DataItem;
import com.kayako.sdk.android.k5.common.adapter.messengerlist.helper.DataItemHelper;
import com.kayako.sdk.android.k5.common.adapter.messengerlist.helper.UserDecorationHelper;
import com.kayako.sdk.android.k5.common.fragments.ListPageState;
import com.kayako.sdk.android.k5.core.MessengerPref;
import com.kayako.sdk.base.requester.AttachmentFile;
import com.kayako.sdk.messenger.attachment.Thumbnail;
import com.kayako.sdk.messenger.message.Message;

import java.net.MalformedURLException;
import java.net.URI;
import java.net.URL;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * Logic to show onboarding messages, actual api messages, optimistic sending messages and KRE indicator messages
 */
public class ListHelper {

    private ListPageState mListPageState;

    public ListPageState getListPageState() {
        return mListPageState;
    }

    public void setListPageState(ListPageState mListPageState) {
        this.mListPageState = mListPageState;
    }

    public List<BaseListItem> getMessageAsListItemViews(List<Message> messages, long lastOriginalMessageMarkedRead, long userId) {
        List<DataItem> dataItems = convertMessagesToDataItems(messages, lastOriginalMessageMarkedRead, userId);
        return DataItemHelper.getInstance().convertDataItemToListItems(dataItems);
    }

    private List<DataItem> convertMessagesToDataItems(List<Message> messageList, long lastOriginalMessageMarkedRead, long userId) {
        List<DataItem> dataItems = new ArrayList<>();

        for (Message message : messageList) {

            boolean isRead = false;
                /* Note: Using MarkReadHelper and not Conversation variable directly because the unread status should be preserved
                   That is, even though a conversation is marked read, the unread marker should not disappear until the user reopens the page!
                 */
            if (lastOriginalMessageMarkedRead > 0 // VALID value for lastOriginalMessageMarkedRead
                    && lastOriginalMessageMarkedRead >= message.getId()) {
                isRead = true;
            } else {
                isRead = false;
            }

            dataItems.add(
                    new DataItem(
                            message.getId(),
                            null,
                            UserDecorationHelper.getUserDecoration(message, userId),
                            null, // no channelDecoration
                            com.kayako.sdk.android.k5.common.adapter.messengerlist.helper.DeliveryIndicatorHelper.getDeliveryIndicator(message),
                            message.getContentText(),
                            message.getCreatedAt(),
                            convert(message.getAttachments()),
                            isRead
                    )
            );
        }

        return dataItems;
    }

    private List<Attachment> convert(List<com.kayako.sdk.messenger.attachment.Attachment> attachmentFiles) {
        if (attachmentFiles == null || attachmentFiles.size() == 0) {
            return Collections.emptyList();
        }

        List<Attachment> attachments = new ArrayList<>();
        for (com.kayako.sdk.messenger.attachment.Attachment attachment : attachmentFiles) {
            attachments.add(new Attachment(
                    appendFingerprintIdToUrl(getThumnailUrl(attachment)),
                    attachment.getName(),
                    attachment.getSize() == null ? 0 : attachment.getSize(),
                    attachment.getType(),
                    appendFingerprintIdToUrl(attachment.getUrlDownload())
            ));
        }

        return attachments;
    }

    private String getThumnailUrl(com.kayako.sdk.messenger.attachment.Attachment attachment) {
        List<Thumbnail> thumbnails = attachment.getThumbnails();
        if (thumbnails == null || thumbnails.size() == 0) {
            return null;
        }

        Thumbnail thumbnailWithAppropriateHeight = thumbnails.get(thumbnails.size() - 1); // largest by default

        // Select the size that's just above 150 in height
        for (Thumbnail thumbnail : thumbnails) {
            if (thumbnail.getHeight() > 150L) {
                thumbnailWithAppropriateHeight = thumbnail;
                break;
            } else {
                thumbnailWithAppropriateHeight = thumbnail;
            }
        }

        return thumbnailWithAppropriateHeight.getUrl();
    }

    private String appendFingerprintIdToUrl(String url) {
        if (url == null) {
            return null;
        }

        String fingerprintId = MessengerPref.getInstance().getFingerprintId();

        final String PARAM_FINGERPRINT_ID = "_fingerprint_id";

        if (url.contains("?")) {
            return String.format("%s&%s=%s", url, PARAM_FINGERPRINT_ID, fingerprintId);
        } else {
            return String.format("%s?%s=%s", url, PARAM_FINGERPRINT_ID, fingerprintId);
        }

    }
}