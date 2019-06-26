package com.a1stopclick.appssetting;

import android.app.Activity;
/*
 * Created by dendy-prtha on 26/06/2019.
 * Setting Item
 */

public class SettingItem {

    public String settingTitle;
    public boolean shownRightArrow;

    public SettingItem(String settingTitle, boolean shownRightArrow)
    {
        this.settingTitle = settingTitle;
        this.shownRightArrow =shownRightArrow;
    }
}
