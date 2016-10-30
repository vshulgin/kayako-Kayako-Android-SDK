package com.kayako.sdk.android.k5.common.adapter.messengerlist;

import com.kayako.sdk.android.k5.common.adapter.BaseListItem;

import java.util.Map;

public class SimpleMessageContinuedOtherListItem extends BaseListItem {

    private String message;
    private Map<String, Object> data;

    public SimpleMessageContinuedOtherListItem(String message, Map<String, Object> data) {
        super(MessengerListType.SIMPLE_MESSAGE_CONTINUED_OTHER);
        this.message = message;
        this.data = data;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public Map<String, Object> getData() {
        return data;
    }

    public void setData(Map<String, Object> data) {
        this.data = data;
    }
}