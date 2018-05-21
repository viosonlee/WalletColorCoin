package com.a7.wallet.common;

import android.os.Bundle;

/**
 * Created by viosonlee
 * on 2018/5/21.
 * for
 */
public interface BaseActivityImp {
    String BUNDLE = "data";

    void startActivity(Class clazz);

    void startActivity(Class clazz, Bundle bundle);
}
