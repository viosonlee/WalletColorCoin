package lee.vioson.network.core;


import io.reactivex.Observer;
import io.reactivex.disposables.Disposable;

/**
 * Created by viosonlee
 * on 2018/5/26.
 * for
 */
public abstract class BaseObserver<T extends BaseResponse> implements Observer<T> {
    private static final String TAG = "BaseObserver";
    private LoadingListener loadingListener;

    public BaseObserver(LoadingListener loadingListener) {
        this.loadingListener = loadingListener;
    }

    public BaseObserver() {
    }
    @Override
    public void onSubscribe(Disposable d) {
        if (loadingListener != null) {
            loadingListener.onStartLoading();
        }
    }

    @Override
    public void onNext(T response) {
        try {
            if (responseIsOk(response)) {
                onHandleSuccess(response);
            } else onHandleError(new BaseApiException(response));
        } catch (Exception e) {
            onHandleError(new BaseApiException(-999, e.getMessage()));//其他错误
        }
    }

    protected void onHandleError(BaseApiException e) {
        DebugLog.e(TAG, e.getMessage());
        if (loadingListener!=null) {
            loadingListener.onDismissLoading();
        }
    }


    protected abstract void onHandleSuccess(T data);

    @Override
    public void onComplete() {
        DebugLog.d(TAG, "onComplete");
        if (loadingListener != null) {
            loadingListener.onDismissLoading();
        }
    }

    @Override
    public void onError(Throwable e) {
        onHandleError(new BaseApiException(-999, e.getMessage()));
    }

    public boolean responseIsOk(T response) {
        return response.isOk();
    }
}
