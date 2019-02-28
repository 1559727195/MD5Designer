package com.massky.md5designer.ui.adapter;

import android.app.Activity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;
import com.crazysunj.multitypeadapter.helper.RecyclerViewAdapterHelper;
import com.massky.domain.entity.base.MultiTypeIdEntity;
import com.massky.domain.entity.common.CommonHeaderEntity;
import com.massky.domain.entity.zhihu.ZhihuNewsEntity;
import com.massky.md5designer.R;
import com.massky.md5designer.base.BaseHelperAdapter;
import com.massky.md5designer.base.BaseViewHolder;
import com.massky.md5designer.di.module.EntityModule;
import com.massky.md5designer.entity.ExpandCollapseFooterEntity;
import com.massky.md5designer.module.image.ImageLoader;
import com.massky.md5designer.ui.adapter.helper.HomeAdapterHelper;
import java.util.List;
import java.util.Locale;
import javax.inject.Inject;
import javax.inject.Named;
import androidx.annotation.NonNull;
import androidx.appcompat.widget.AppCompatImageView;

public class HomeAdapter extends BaseHelperAdapter<MultiTypeIdEntity,BaseViewHolder,HomeAdapterHelper> {

    /**
     * 固定不变的或者直接在Adapter中修改数据源的(如footer)可以用这种方法
     */
    @Named(EntityModule.NAME_ZHIHU)
    @Inject
    CommonHeaderEntity mZhihuHeaderEntity;


    @Named(EntityModule.NAME_ZHIHU)
    @Inject
    ExpandCollapseFooterEntity mZhihuFooterEntity;


    private String mRemoveVideoItemId;

    @Inject
    public HomeAdapter(HomeAdapterHelper helper) {
        super(helper);
    }

    @Override
    protected void convert(BaseViewHolder holder, MultiTypeIdEntity item) {
        switch (item.getItemType()) {
//            case NeihanItemEntity.TYPE_NEIHAN:
//                renderNeihan(holder, (NeihanItemEntity) item);
//                break;
            case ZhihuNewsEntity.StoriesEntity.TYPE_ZHIHU_NEWS:
                renderZhihuNews(holder, (ZhihuNewsEntity.StoriesEntity) item);
                break;
            case HomeAdapterHelper.LEVEL_ZHIHU - RecyclerViewAdapterHelper.FOOTER_TYPE_DIFFER:
            case HomeAdapterHelper.LEVEL_GANK_IO - RecyclerViewAdapterHelper.FOOTER_TYPE_DIFFER:
                renderExpandCollapseFooter(holder, (ExpandCollapseFooterEntity) item);
                break;

            case HomeAdapterHelper.LEVEL_GANK_IO - RecyclerViewAdapterHelper.HEADER_TYPE_DIFFER:
                renderGankioHeader(holder, (CommonHeaderEntity) item);
                break;
            case HomeAdapterHelper.LEVEL_ZHIHU - RecyclerViewAdapterHelper.HEADER_TYPE_DIFFER:
            case HomeAdapterHelper.LEVEL_GAOXIAO - RecyclerViewAdapterHelper.HEADER_TYPE_DIFFER:
                renderHeader(holder, (CommonHeaderEntity) item);
                break;
            default:
                break;
        }
    }

    @NonNull
    @Override
    public BaseViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        BaseViewHolder holder = super.onCreateViewHolder(parent, viewType);
        return holder;
    }

    // *********************** 搞笑视频 ***********************

    private DownloadCallback mDownloadCallback;

    public void setDownloadCallback(DownloadCallback callback) {
        mDownloadCallback = callback;
    }

    public interface DownloadCallback {
        void onDownload(String url);
    }



    private int getPosition(BaseViewHolder holder) {
        return holder.getAdapterPosition();
    }

    @Override
    public void onViewRecycled(@NonNull BaseViewHolder holder) {
        super.onViewRecycled(holder);
        final int position = getPosition(holder);
        if (position < 0) {
            return;
        }
        final MultiTypeIdEntity item = mHelper.getItem(position);

    }

    public void notifyZhihuNewsList(List<ZhihuNewsEntity.StoriesEntity> data) {
        final int level = HomeAdapterHelper.LEVEL_ZHIHU;
        final String title = String.format(Locale.getDefault(), "展开（剩余%d个）", data.size() - HomeAdapterHelper.MIN_ZHIHU);
        mZhihuFooterEntity.initStatus(title);
//        mHelper.notifyModuleDataAndHeaderAndFooterChanged(data, mZhihuHeaderEntity, mZhihuFooterEntity, level);
        mHelper.notifyModuleDataAndHeaderAndFooterChanged(data,mZhihuHeaderEntity,mZhihuFooterEntity,level);
    }


    @Override
    public void onViewAttachedToWindow(@NonNull BaseViewHolder holder) {
        super.onViewAttachedToWindow(holder);
        final int position = getPosition(holder);
        if (position < 0) {
            return;
        }
        final MultiTypeIdEntity item = mHelper.getItem(position);

    }

    @Override
    public void onViewDetachedFromWindow(@NonNull BaseViewHolder holder) {
        super.onViewDetachedFromWindow(holder);
        final int position = getPosition(holder);
        if (position < 0) {
            return;
        }
        final MultiTypeIdEntity item = mHelper.getItem(position);

    }



    private void renderGankioHeader(BaseViewHolder holder, CommonHeaderEntity item) {
        final int level = mHelper.getLevel(item.getItemType());
        holder.setText(R.id.header_title, item.getTitle());
        TextView optionsView = holder.getView(R.id.header_options);
        final String options = item.getOptions();
        optionsView.setText(options);
        optionsView.setTextColor(HomeAdapterHelper.getColor(level));
        optionsView.setOnClickListener(v -> {
            if (mOnHeaderClickListener != null) {
                mOnHeaderClickListener.onHeaderClick(level, options);
            }
        });
    }


    // ***********************知乎 ***********************

    private void renderHeader(BaseViewHolder holder, CommonHeaderEntity item) {
        final int level = mHelper.getLevel(item.getItemType());
        holder.setText(R.id.header_title, item.getTitle());
        TextView optionsView = holder.getView(R.id.header_options);
        final String options = item.getOptions();
        optionsView.setText(options);
        optionsView.setTextColor(HomeAdapterHelper.getColor(level));
        optionsView.setOnClickListener(v -> {
            if (mOnHeaderClickListener != null) {
                mOnHeaderClickListener.onHeaderClick(level, options);
            }
        });
    }

    private void renderZhihuNews(BaseViewHolder holder, ZhihuNewsEntity.StoriesEntity item) {
        final AppCompatImageView icon = holder.getView(R.id.item_zhihu_news_icon);
        ImageLoader.load(mContext, getUrl(item.getImages()), icon);
        holder.setText(R.id.item_zhihu_news_title, item.getTitle());
//        holder.itemView.setOnClickListener(v -> ZhihuNewsDetailActivity.start((Activity) v.getContext(), item.getId(), icon));
    }

    //*********************** 公共Footer ***********************
    private void renderExpandCollapseFooter(BaseViewHolder holder, ExpandCollapseFooterEntity item) {
        String stringId = item.getStringId();
        holder.setText(R.id.footer_title, stringId);
        holder.setImageResource(R.id.footer_icon, item.getIconResId());
        holder.itemView.setOnClickListener(v -> {
            item.switchStatus();
            mHelper.foldType(mHelper.getLevel(item.getItemType()), item.isFlod());
            mHelper.notifyDataChanged(item);
        });
    }



    private String getUrl(List<String> images) {
        if (images == null || images.isEmpty()) {
            return null;
        }
        return images.get(0);
    }



    //*********************** 接口 ***********************

    private OnHeaderClickListener mOnHeaderClickListener;

    public void setOnHeaderClickListener(OnHeaderClickListener listener) {
        mOnHeaderClickListener = listener;
    }

    public interface OnHeaderClickListener {
        void onHeaderClick(int level, String options);
    }
}
