package com.kayako.sdk.android.k5.common.adapter.messengerlist.view;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

import com.kayako.sdk.android.k5.R;
import com.kayako.sdk.android.k5.common.view.CircleImageView;

public class BotMessageViewHolder extends RecyclerView.ViewHolder {

    public TextView message;
    public TextView time;
    public CircleImageView avatar;
    public CircleImageView channel;

    public BotMessageViewHolder(View itemView) {
        super(itemView);
        avatar = (CircleImageView) itemView.findViewById(R.id.avatar);
        channel = (CircleImageView) itemView.findViewById(R.id.channel);
        message = (TextView) itemView.findViewById(R.id.message);
        time = (TextView) itemView.findViewById(R.id.time);
    }
}