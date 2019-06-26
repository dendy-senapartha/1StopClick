package com.a1stopclick.appssetting;

import android.view.MotionEvent;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.core.content.ContextCompat;
import androidx.recyclerview.widget.RecyclerView;

import com.a1stopclick.R;

public class AppsSettingItemViewHolder extends RecyclerView.ViewHolder {
    FragmentAppsSetting parentFragment;
    private TextView settingItemName;
    private ImageView settingArrowRight;

    public AppsSettingItemViewHolder(View itemView, FragmentAppsSetting parentFragment) {
        super(itemView);
        this.parentFragment = parentFragment;
        settingItemName = (TextView) itemView.findViewById(R.id.setting_item_name);
        settingArrowRight = (ImageView) itemView.findViewById(R.id.setting_arrow_right);
    }

    public void setTextSettingItemName(String text) {
        this.settingItemName.setText(text);
    }

    public void setSettingArrowRightShown(boolean shown) {
        if (shown) {
            settingArrowRight.setVisibility(View.VISIBLE);
        } else {
            settingArrowRight.setVisibility(View.GONE);
        }
    }

    public void bind(SettingItem item) {
        setTextSettingItemName(item.settingTitle);
        setSettingArrowRightShown(item.shownRightArrow);
        itemView.setOnClickListener(onClick -> {
                    switch (item.settingTitle) {
                        case FragmentAppsSetting.LOGOUT:
                            parentFragment.getPresenter().logOut();
                            break;
                        case FragmentAppsSetting.PROFILE:

                            break;
                    }
                }
        );

        itemView.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                if(event.getAction() == MotionEvent.ACTION_DOWN)
                {
                    v.setBackgroundColor(ContextCompat.getColor(parentFragment.getContext(), R.color.list_item_selected_state));
                }
                if (event.getAction() == MotionEvent.ACTION_UP || event.getAction() == MotionEvent.ACTION_CANCEL)
                {
                    v.setBackgroundColor(ContextCompat.getColor(parentFragment.getContext(), R.color.list_item_normal_state));
                }
                //return false on this method because this need for working OnClickListener
                return false;
            }
        });
    }
}
