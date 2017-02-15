package com.kayako.sdk.android.k5.conversationlistpage;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.kayako.sdk.android.k5.R;

public class ConversationListContainerFragment extends Fragment implements ConversationListContainerContract.View {

    private ConversationListContainerContract.Presenter mPresenter;
    private View mRoot;
    private ConversationListContract.ConfigureView mConversationListView;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mPresenter = ConversationListContainerFactory.getPresenter(this);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return mRoot = inflater.inflate(R.layout.ko__fragment_conversation_list_container, container, false);
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        mPresenter.onOpenPage();

        mConversationListView = (ConversationListContract.ConfigureView) getChildFragmentManager().findFragmentById(R.id.ko__conversation_list);
        mConversationListView.setOnScrollListener(new ConversationListContract.OnScrollListener() {
            @Override
            public void onScroll(boolean isScrolling) {
                mPresenter.onScrollConversationList(isScrolling);
            }
        });
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        mPresenter.onClosePage();
    }

    private boolean hasPageLoaded() {
        return isAdded() && getActivity() != null;
    }

    @Override
    public void hideNewConversationButton() {
        if(!hasPageLoaded()){
            return;
        }

        View button = mRoot.findViewById(R.id.ko__new_conversation_button);
        button.setVisibility(View.GONE);
        // TODO: Animations?
    }

    @Override
    public void showNewConversationButton() {
        if(!hasPageLoaded()){
            return;
        }

        View button = mRoot.findViewById(R.id.ko__new_conversation_button);
        button.setVisibility(View.VISIBLE);
        // TODO: Animations?
    }

    @Override
    public void openNewConversationPage() {
        if(!hasPageLoaded()){
            return;
        }

        // TODO:
    }
}
