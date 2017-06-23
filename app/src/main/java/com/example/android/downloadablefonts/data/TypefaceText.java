package com.example.android.downloadablefonts.data;

import android.graphics.Typeface;

/**
 * Created by cattaka on 17/06/23.
 */

public class TypefaceText {
    private String typefaceName;
    private String text;
    private LoadingState loadingState;
    private Typeface typeface;

    public Typeface getTypeface() {
        return typeface;
    }

    public void setTypeface(Typeface typeface) {
        this.typeface = typeface;
    }

    public String getTypefaceName() {
        return typefaceName;
    }

    public void setTypefaceName(String typefaceName) {
        this.typefaceName = typefaceName;
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
