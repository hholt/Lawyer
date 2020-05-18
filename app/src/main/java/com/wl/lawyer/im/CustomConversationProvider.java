package com.wl.lawyer.im;

import android.util.Log;

import com.tencent.qcloud.tim.uikit.modules.conversation.ConversationListAdapter;
import com.tencent.qcloud.tim.uikit.modules.conversation.base.ConversationInfo;
import com.tencent.qcloud.tim.uikit.modules.conversation.interfaces.ConversationFilter;
import com.tencent.qcloud.tim.uikit.modules.conversation.interfaces.IConversationAdapter;
import com.tencent.qcloud.tim.uikit.modules.conversation.interfaces.IConversationProvider;
import com.wl.lawyer.im.adapter.CustomConversationListAdapter;

import java.util.ArrayList;
import java.util.List;

public class CustomConversationProvider implements IConversationProvider {
    private List<ConversationInfo> mDataSource = new ArrayList();
    private CustomConversationListAdapter mAdapter;

    private ConversationFilter mFilter = new ConversationFilter() {
        @Override
        public List<ConversationInfo> filter(List<ConversationInfo> conversations) {
            return conversations;
        }
    };

    @Override
    public List<ConversationInfo> getDataSource() {
        return mDataSource;
    }

    /**
     * 设置会话数据源
     *
     * @param dataSource
     */
    public void setDataSource(List<ConversationInfo> dataSource) {
        this.mDataSource.clear();
        this.mDataSource.addAll(mFilter.filter((dataSource)));
        updateAdapter();
    }


    /**
     * 批量添加会话数据
     *
     * @param conversations 会话数据集合
     * @return
     */
    @Override
    public boolean addConversations(List<ConversationInfo> conversations) {
        if (conversations.size() == 1) {
            ConversationInfo conversation = conversations.get(0);
            for (int i = 0; i < mDataSource.size(); i++) {
                if (mDataSource.get(i).getId().equals(conversation.getId()))
                    return true;
            }
        }
        boolean flag = mDataSource.addAll(mFilter.filter(conversations));
        if (flag) {
            updateAdapter();
        }
        return flag;
    }

    /**
     * 批量删除会话数据
     *
     * @param conversations 会话数据集合
     * @return
     */
    @Override
    public boolean deleteConversations(List<ConversationInfo> conversations) {
        List<Integer> removeIndexs = new ArrayList();
        for (int i = 0; i < mDataSource.size(); i++) {
            for (int j = 0; j < conversations.size(); j++) {
                if (mDataSource.get(i).getId().equals(conversations.get(j).getId())) {
                    removeIndexs.add(i);
                    conversations.remove(j);
                    break;
                }
            }

        }
        if (removeIndexs.size() > 0) {
            for (int i = 0; i < removeIndexs.size(); i++) {
                mDataSource.remove(removeIndexs.get(i));
            }
            updateAdapter();
            return true;
        }
        return false;
    }

    /**
     * 删除单个会话数据
     *
     * @param index 会话在数据源集合的索引
     * @return
     */
    public void deleteConversation(int index) {
        if (mDataSource.remove(index) != null) {
            updateAdapter();
        }

    }

    /**
     * 删除单个会话数据
     *
     * @param id 会话ID
     * @return
     */
    public void deleteConversation(String id) {
        for (int i = 0; i < mDataSource.size(); i++) {
            if (mDataSource.get(i).getId().equals(id)) {
                if (mDataSource.remove(i) != null) {
                    updateAdapter();
                }
                return;
            }
        }
    }

    /**
     * 批量更新会话
     *
     * @param conversations 会话数据集合
     * @return
     */
    @Override
    public boolean updateConversations(List<ConversationInfo> conversations) {
        boolean flag = false;
        for (int i = 0; i < mDataSource.size(); i++) {
            for (int j = 0; j < conversations.size(); j++) {
                ConversationInfo update = conversations.get(j);
                if (mDataSource.get(i).getId().equals(update.getId())) {
                    mDataSource.remove(i);
                    mDataSource.add(i, update);
                    conversations.remove(j);
                    flag = true;
                    break;
                }
            }

        }
        if (flag) {
            updateAdapter();
            return true;
        } else {
            return false;
        }

    }

    /**
     * 清空会话
     */
    public void clear() {
        mDataSource.clear();
        updateAdapter();
        mAdapter = null;
    }

    /**
     * 会话会话列界面，在数据源更新的地方调用
     */
    public void updateAdapter() {
        if (mAdapter != null) {
            mAdapter.notifyDataSetChanged();
        }
    }


    /**
     * 会话列表适配器绑定数据源是的回调
     *
     * @param adapter 会话UI显示适配器
     */
    @Override
    public void attachAdapter(IConversationAdapter adapter) {
        this.mAdapter = (CustomConversationListAdapter) adapter;
    }

    public ConversationFilter getFilter() {
        return mFilter;
    }

    public void setFilter(ConversationFilter mFilter) {
        this.mFilter = mFilter;
        mDataSource = mFilter.filter(mDataSource);
        Log.d("TAG", "setFilter: " + mDataSource);
    }
}
