package com.kayako.sdk.android.k5.messenger.homescreenpage.adapter.widget.recentcases;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.kayako.sdk.android.k5.R;
import com.kayako.sdk.android.k5.common.utils.DateTimeUtils;
import com.kayako.sdk.android.k5.common.utils.ImageUtils;
import com.kayako.sdk.android.k5.core.Kayako;
import com.kayako.sdk.android.k5.messenger.data.conversationstarter.RecentConversation;

import java.util.ArrayList;
import java.util.List;

public class RecentConversationAdapter extends RecyclerView.Adapter {

    List<RecentConversation> recentConversationList = new ArrayList<>();
    OnClickRecentConversationListener onClickRecentConversationListener;

    public RecentConversationAdapter(List<RecentConversation> conversations, OnClickRecentConversationListener onClickRecentConversationListener) {
        this.recentConversationList = conversations;
        this.onClickRecentConversationListener = onClickRecentConversationListener;

        if (recentConversationList == null || onClickRecentConversationListener == null) {
            throw new IllegalArgumentException("Can't be null");
        }
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View recentConversationViewHolder = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.ko__list_home_screen_widget_conversation, parent, false);
        return new RecentConversationViewHolder(recentConversationViewHolder);
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        RecentConversationViewHolder viewHolder = (RecentConversationViewHolder) holder;
        final RecentConversation recentConversation = recentConversationList.get(position);

        Context context = Kayako.getApplicationContext();
        ImageUtils.setAvatarImage(context, viewHolder.avatar, recentConversation.getAvatarUrl());
        viewHolder.name.setText(recentConversation.getName());
        viewHolder.subject.setText(recentConversation.getSubject());
        viewHolder.time.setText(DateTimeUtils.formatShortDateTime(System.currentTimeMillis(), recentConversation.getTimeInMilleseconds()));

        viewHolder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (onClickRecentConversationListener != null) {
                    onClickRecentConversationListener.onClickRecentConversation(recentConversation.getConversationId());
                }
            }
        });
    }

    @Override
    public int getItemCount() {
        return recentConversationList.size();
    }
}