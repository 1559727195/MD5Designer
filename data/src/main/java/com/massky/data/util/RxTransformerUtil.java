package com.massky.data.util;

import io.reactivex.FlowableTransformer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;

public class RxTransformerUtil {
    public static <T> FlowableTransformer<T, T> normalTransformer() {
        return observable -> observable.subscribeOn(Schedulers.io())
                .unsubscribeOn(Schedulers.io())
//                .observeOn(Schedulers.io())
//                .onErrorResumeNext(e -> {  可能会卡顿，舍弃了
//                    Throwable throwable;
//                    if (NetworkUtils.isNetworkAvailable()) {
//                        throwable = e;
//                    } else {
//                        throwable = new ApiException(CodeConstant.CODE_NO_NET, e.getMessage());
//                    }
//                    return Flowable.error(throwable);
//                })
                .observeOn(AndroidSchedulers.mainThread());
    }
}
