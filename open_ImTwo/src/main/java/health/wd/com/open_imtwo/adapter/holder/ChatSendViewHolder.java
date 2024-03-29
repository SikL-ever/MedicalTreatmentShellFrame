package health.wd.com.open_imtwo.adapter.holder;

import android.os.Handler;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.jude.easyrecyclerview.adapter.BaseViewHolder;

import butterknife.BindView;
import butterknife.ButterKnife;
import health.wd.com.open_imtwo.R;
import health.wd.com.open_imtwo.R2;
import health.wd.com.open_imtwo.adapter.ChatAdapter;
import health.wd.com.open_imtwo.enity.MessageInfo;
import health.wd.com.open_imtwo.util.Constants;
import health.wd.com.open_imtwo.util.Utils;
import health.wd.com.open_imtwo.widget.BubbleImageView;
import health.wd.com.open_imtwo.widget.GifTextView;

import static com.dingtao.common.core.WDApplication.getContext;

/**
 * 作者：Rance on 2016/11/29 10:47
 * 邮箱：rance935@163.com
 */
public class ChatSendViewHolder extends BaseViewHolder<MessageInfo> {

    @BindView(R2.id.chat_item_date)
    TextView chatItemDate;
    @BindView(R2.id.chat_item_header)
    ImageView chatItemHeader;
    @BindView(R2.id.chat_item_content_text)
    GifTextView chatItemContentText;
    @BindView(R2.id.chat_item_content_image)
    BubbleImageView chatItemContentImage;
    @BindView(R2.id.chat_item_fail)
    ImageView chatItemFail;
    @BindView(R2.id.chat_item_progress)
    ProgressBar chatItemProgress;
    @BindView(R2.id.chat_item_voice)
    ImageView chatItemVoice;
    @BindView(R2.id.chat_item_layout_content)
    LinearLayout chatItemLayoutContent;
    @BindView(R2.id.chat_item_voice_time)
    TextView chatItemVoiceTime;
    private ChatAdapter.onItemClickListener onItemClickListener;
    private Handler handler;

    public ChatSendViewHolder(ViewGroup parent, ChatAdapter.onItemClickListener onItemClickListener, Handler handler) {
        super(parent, R.layout.item_chat_send);
        ButterKnife.bind(this,itemView);//绑定布局
        this.onItemClickListener = onItemClickListener;
        this.handler = handler;
    }


    @Override
    public void setData(MessageInfo data) {
        chatItemDate.setText(data.getTime() != null ? data.getTime() : "");
        Glide.with(getContext()).load(data.getHeader()).into(chatItemHeader);
        chatItemHeader.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onItemClickListener.onHeaderClick(getDataPosition());
            }
        });
        if (data.getContent() != null) {
            chatItemContentText.setSpanText(handler, data.getContent(), true);
            chatItemVoice.setVisibility(View.GONE);
            chatItemContentText.setVisibility(View.VISIBLE);
            chatItemLayoutContent.setVisibility(View.VISIBLE);
            chatItemVoiceTime.setVisibility(View.GONE);
            chatItemContentImage.setVisibility(View.GONE);
        } else if (data.getImageUrl() != null) {
            chatItemVoice.setVisibility(View.GONE);
            chatItemLayoutContent.setVisibility(View.GONE);
            chatItemVoiceTime.setVisibility(View.GONE);
            chatItemContentText.setVisibility(View.GONE);
            chatItemContentImage.setVisibility(View.VISIBLE);
            Glide.with(getContext()).load(data.getImageUrl()).into(chatItemContentImage);
            chatItemContentImage.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    onItemClickListener.onImageClick(chatItemContentImage, getDataPosition());
                }
            });
        } else if (data.getFilepath() != null) {
            chatItemVoice.setVisibility(View.VISIBLE);
            chatItemLayoutContent.setVisibility(View.VISIBLE);
            chatItemContentText.setVisibility(View.GONE);
            chatItemVoiceTime.setVisibility(View.VISIBLE);
            chatItemContentImage.setVisibility(View.GONE);
            chatItemVoiceTime.setText(Utils.formatTime(data.getVoiceTime()));
            chatItemLayoutContent.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    onItemClickListener.onVoiceClick(chatItemVoice, getDataPosition());
                }
            });
        }
        switch (data.getSendState()) {
            case Constants.CHAT_ITEM_SENDING:
                chatItemProgress.setVisibility(View.VISIBLE);
                chatItemFail.setVisibility(View.GONE);
                break;
            case Constants.CHAT_ITEM_SEND_ERROR:
                chatItemProgress.setVisibility(View.GONE);
                chatItemFail.setVisibility(View.VISIBLE);
                break;
            case Constants.CHAT_ITEM_SEND_SUCCESS:
                chatItemProgress.setVisibility(View.GONE);
                chatItemFail.setVisibility(View.GONE);
                break;
        }
    }
}
