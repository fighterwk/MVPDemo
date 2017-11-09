package com.kyle.mvp.net;

public interface RequestCallback<T> {

    public abstract void onStart();

    public abstract void onComplete();

    public abstract void onSuccess(T bean);

    public abstract void onFail(Throwable e);

}