package com.kayako.sdk.android.k5.messagelistpage;

public class ReplyBoxPresenter implements ReplyBoxContract.Presenter {

    private ReplyBoxContract.View mView;
    private ReplyBoxContract.ReplyBoxListener mListener;

    public ReplyBoxPresenter(ReplyBoxContract.View view) {
        mView = view;
    }

    @Override
    public void setView(ReplyBoxContract.View view) {
        mView = view;
    }

    @Override
    public void initPage() {
    }

    @Override
    public void onReplyTyped(String message) {
        if (message == null || message.length() == 0) {
            mView.disableSendButton();
        } else {
            mView.enableSendButton();
        }
    }

    @Override
    public void onClickSend() {
        String messageContent = mView.getReplyBoxText();

        if (messageContent == null || messageContent.length() == 0) {
            // TODO: Disable the button when no item typed?
        } else {
            mListener.onClickSend(messageContent);
            mView.setReplyBoxText(null); // Clear reply box after clicking send
        }
    }

    @Override
    public void setReplyBoxListener(ReplyBoxContract.ReplyBoxListener listener) {
        mListener = listener;
    }
}