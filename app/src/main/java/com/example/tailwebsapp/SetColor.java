package com.example.tailwebsapp;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.widget.TextView;

import androidx.appcompat.app.ActionBar;
import androidx.palette.graphics.Palette;

public class SetColor {

    public SetColor(Context context) {
        this.context = context;
    }

    private Context context;

    public void setTextColorForImage(TextView textView, TextView textView1, Bitmap firstPhoto) {
        Palette.from(firstPhoto)
                .generate(new Palette.PaletteAsyncListener() {
                    @Override
                    public void onGenerated(Palette palette) {
                        Palette.Swatch swatch = palette.getVibrantSwatch();
                        if (swatch == null && palette.getSwatches().size() > 0) {
                            swatch = palette.getSwatches().get(0);
                        }

                        int titleTextColor = Color.WHITE;

                        if (swatch != null) {
                            titleTextColor = swatch.getRgb();
                            //titleTextColor = ColorUtils.setAlphaComponent(titleTextColor, 255);
                        }
                        textView.setTextColor(titleTextColor);
                        textView1.setTextColor(titleTextColor);

                    }
                });
    }
}
