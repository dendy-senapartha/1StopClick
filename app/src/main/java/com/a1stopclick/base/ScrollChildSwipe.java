package com.a1stopclick.base;

import android.content.Context;

import android.util.AttributeSet;
import android.view.View;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

/*
 * Created by dendy-prtha on 25/02/2019.
 * scroll swipe for refresh
 */

public class ScrollChildSwipe extends SwipeRefreshLayout {

    private View mScrollUpChild;

    public ScrollChildSwipe(Context context) {
        super(context);
    }

    public ScrollChildSwipe(@NonNull Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
    }

    @Override
    public boolean canChildScrollUp() {
        if (mScrollUpChild != null) {
            return canScrollVertically(-1);
        }
        return super.canChildScrollUp();
    }

    public boolean canScrollVertically(int direction) {
        final int offset = computeVerticalScrollOffset();
        final int range = computeVerticalScrollRange() - computeVerticalScrollExtent();
        if (range == 0) return false;
        if (direction < 0) {
            return offset > 0;
        } else {
            return offset < range - 1;
        }
    }

    public void setScrollUpChild(View view) {
        mScrollUpChild = view;
    }
}
