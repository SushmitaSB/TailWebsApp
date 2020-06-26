package com.example.tailwebsapp.controller.adapter;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.viewpager.widget.PagerAdapter;

import com.example.tailwebsapp.R;
import com.example.tailwebsapp.controller.SetColor;

public class SlideViewPagerAdapter extends PagerAdapter {

   private Context context;
   private LinearLayout linearLayout;
   private TextView textViewHeading, textView;
   private ImageView indicator1, indicator2, indicator3;
   private SetColor setColor;

    public SlideViewPagerAdapter(Context context) {
        this.context = context;
    }

    @Override
    public int getCount() {
        return 3;
    }

    @Override
    public boolean isViewFromObject(@NonNull View view, @NonNull Object object) {
        return view == object;
    }

    @NonNull
    @Override
    public Object instantiateItem(@NonNull ViewGroup container, int position) {
        LayoutInflater layoutInflater = (LayoutInflater) context.getSystemService(context.LAYOUT_INFLATER_SERVICE);
        View view = layoutInflater.inflate(R.layout.slider_screen,container,false);
        // finding all the ids of views
        initializedViews(view);
        //set text color according to background image by calling serColor class object.
        setTextViewColor(position);
        container.addView(view);
        return view;
    }

    private void setTextViewColor(int position) {
        setColor = new SetColor(context);
        switch (position){
            case 0: indicator1.setImageResource(R.drawable.active_dot_layout);
                    indicator2.setImageResource(R.drawable.non_active_dot_xml);
                    indicator3.setImageResource(R.drawable.non_active_dot_xml);
                    linearLayout.setBackgroundResource(R.drawable.man_img);
                    Bitmap bitmap = BitmapFactory.decodeResource(context.getResources(),
                            R.drawable.man_img);
                    setColor.setTextColorForImage(textViewHeading,textView, bitmap);
                    textViewHeading.setText(R.string.be_part);
                    textView.setText(R.string.our_world);
                break;
            case 1: indicator1.setImageResource(R.drawable.non_active_dot_xml);
                    indicator2.setImageResource(R.drawable.active_dot_layout);
                    indicator3.setImageResource(R.drawable.non_active_dot_xml);
                    linearLayout.setBackgroundResource(R.drawable.women_img);
                Bitmap bitmap1 = BitmapFactory.decodeResource(context.getResources(),
                        R.drawable.women_img);
                    setColor.setTextColorForImage(textViewHeading,textView, bitmap1);
                    textViewHeading.setText(R.string.perfect_your);
                    textView.setText(R.string.everything);
                break;
            case 2: indicator1.setImageResource(R.drawable.non_active_dot_xml);
                    indicator2.setImageResource(R.drawable.non_active_dot_xml);
                    indicator3.setImageResource(R.drawable.active_dot_layout);
                    linearLayout.setBackgroundResource(R.drawable.earth_img);
                    Bitmap bitmap2 = BitmapFactory.decodeResource(context.getResources(),
                        R.drawable.earth_img);
                    setColor.setTextColorForImage(textViewHeading,textView, bitmap2);
                    textViewHeading.setText(R.string.postgraduate);
                    textView.setText(R.string.join);
                break;
        }
    }

    private void initializedViews(View view) {
        indicator1 = view.findViewById(R.id.ind1);
        indicator2 = view.findViewById(R.id.ind2);
        indicator3 = view.findViewById(R.id.ind3);
        linearLayout = view.findViewById(R.id.backgroundLayout);
        textViewHeading = view.findViewById(R.id.tvHeading);
        textView = view.findViewById(R.id.tvText);
    }

    @Override
    public void destroyItem(@NonNull ViewGroup container, int position, @NonNull Object object) {
        container.removeView((View) object);
    }
}
