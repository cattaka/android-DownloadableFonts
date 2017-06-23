package com.example.android.downloadablefonts;

import android.content.Context;
import android.databinding.DataBindingUtil;
import android.graphics.Typeface;
import android.os.Bundle;
import android.os.Handler;
import android.os.HandlerThread;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.provider.FontRequest;
import android.support.v4.provider.FontsContractCompat;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.view.View;
import android.widget.Toast;

import com.example.android.downloadablefonts.adapter.TypefaceTextViewHolderFactory;
import com.example.android.downloadablefonts.data.TypefaceText;
import com.example.android.downloadablefonts.databinding.ActivityTypefaceListBinding;

import net.cattaka.android.adaptertoolbox.adapter.ScrambleAdapter;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by takao on 2017/06/23.
 */

public class TypefaceListActivity extends AppCompatActivity {
    static Handler sHandler;

    TypefaceListActivity me = this;
    ActivityTypefaceListBinding mBinding;
    ScrambleAdapter<TypefaceText> mAdapter;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mBinding = DataBindingUtil.setContentView(this, R.layout.activity_typeface_list);
        mBinding.setActivity(this);

        mAdapter = new ScrambleAdapter<TypefaceText>(this, createTypefaceTexts(this), null, new TypefaceTextViewHolderFactory());
        mBinding.recycler.setAdapter(mAdapter);
        mBinding.recycler.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false));

        applyText();
        requestDownloadAllFont();
    }

    private static List<TypefaceText> createTypefaceTexts(@NonNull Context context) {
        List<TypefaceText> items = new ArrayList<>();
        String[] familyNames = context.getResources().getStringArray(R.array.family_names);
        for (String familyName : familyNames) {
            TypefaceText item = new TypefaceText();
            item.setTypefaceName(familyName);
            item.setLoadingState(TypefaceText.LoadingState.INIT);
            items.add(item);
        }
        return items;
    }

    private void requestDownloadAllFont() {
        for (TypefaceText item : mAdapter.getItems()) {
            requestDownload(item.getTypefaceName());
        }
    }

    private void requestDownload(final String familyName) {
        QueryBuilder queryBuilder = new QueryBuilder(familyName)
                .withBestEffort(true);
        String query = queryBuilder.build();

        FontRequest request = new FontRequest(
                "com.google.android.gms.fonts",
                "com.google.android.gms",
                query,
                R.array.com_google_android_gms_fonts_certs);

        FontsContractCompat.FontRequestCallback callback = new FontsContractCompat
                .FontRequestCallback() {
            @Override
            public void onTypefaceRetrieved(Typeface typeface) {
                me.onTypefaceRetrieved(familyName, TypefaceText.LoadingState.SUCCEED, typeface);
            }

            @Override
            public void onTypefaceRequestFailed(int reason) {
                me.onTypefaceRetrieved(familyName, TypefaceText.LoadingState.FAILED, null);
                Toast.makeText(me,
                        getString(R.string.request_failed, reason), Toast.LENGTH_LONG)
                        .show();
            }
        };
        FontsContractCompat.requestFont(me, request, callback, getHandlerThreadHandler());
    }

    private static synchronized Handler getHandlerThreadHandler() {
        if (sHandler == null) {
            HandlerThread handlerThread = new HandlerThread("fonts");
            handlerThread.start();
            sHandler = new Handler(handlerThread.getLooper());
        }
        return sHandler;
    }


    public void onTypefaceRetrieved(String familyName, TypefaceText.LoadingState loadingState, Typeface typeface) {
        int i = 0;
        for (TypefaceText item : mAdapter.getItems()) {
            if (familyName.equals(item.getTypefaceName())) {
                item.setLoadingState(loadingState);
                item.setTypeface(typeface);
                mAdapter.notifyItemChanged(i);
            }
            i++;
        }
    }

    public void applyText() {
        String text = String.valueOf(mBinding.edit.getText());
        int i = 0;
        for (TypefaceText item : mAdapter.getItems()) {
            item.setText(text);
            mAdapter.notifyItemChanged(i);
            i++;
        }
    }

    public void onClickApplyText(View view) {
        applyText();
    }
}
