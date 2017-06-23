package com.example.android.downloadablefonts;

import android.content.Context;
import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.view.View;

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
    }

    private static List<TypefaceText> createTypefaceTexts(@NonNull Context context) {
        List<TypefaceText> items = new ArrayList<>();
        String[] familyNames = context.getResources().getStringArray(R.array.family_names);
        for (String familyName : familyNames) {
            TypefaceText item = new TypefaceText();
            item.setTypeface(familyName);
            item.setLoadingState(TypefaceText.LoadingState.INIT);
            items.add(item);
        }
        return items;
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
