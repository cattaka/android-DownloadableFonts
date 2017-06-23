package com.example.android.downloadablefonts.data;

/**
 * Created by cattaka on 17/06/23.
 */

public class TypefaceText {
    private String typeface;
    private String text;
    private LoadingState loadingState;

    public String getTypeface() {
        return typeface;
    }

    public void setTypeface(String typeface) {
        this.typeface = typeface;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public LoadingState getLoadingState() {
        return loadingState;
    }

    public void setLoadingState(LoadingState loadingState) {
        this.loadingState = loadingState;
    }

    public enum LoadingState {
        INIT,
        LOADING,
        SUCCEED,
        FAILED
    }
}
