package com.example.photoeditor;

import android.graphics.Typeface;
import android.graphics.drawable.Drawable;
import android.graphics.drawable.GradientDrawable;
import android.os.Build;
import android.view.View;
import android.widget.TextView;

import androidx.annotation.NonNull;

import java.util.HashMap;
import java.util.Map;

public class TextStyleBuilder {

    private final Map<TextStyle, Object> values = new HashMap<>();
    protected Map<TextStyle, Object> getValues() { return values; }

    public void withTextSize(float size) {
        values.put(TextStyle.SIZE, size);
    }

    public void withTextShadow(float radius, float dx, float dy, int color) {
        TextShadow shadow = new TextShadow(radius, dx, dy, color);
        withTextShadow(shadow);
    }

    public void withTextColor(int color) {
        values.put(TextStyle.COLOR, color);
    }

    public void withTextFont(@NonNull Typeface textTypeface) {
        values.put(TextStyle.FONT_FAMILY, textTypeface);
    }

    public void withGravity(int gravity) {
        values.put(TextStyle.GRAVITY, gravity);
    }

    public void withBackgroundColor(int background) {
        values.put(TextStyle.BACKGROUND, background);
    }

    public void withBackgroundDrawable(@NonNull Drawable bgDrawable) {
        values.put(TextStyle.BACKGROUND, bgDrawable);
    }

    public void withTextAppearance(int textAppearance) {
        values.put(TextStyle.TEXT_APPEARANCE, textAppearance);
    }

    public void withTextStyle(int typeface){
        values.put(TextStyle.TEXT_STYLE,typeface);
    }

    public void withTextFlag(int paintFlag){
        values.put(TextStyle.TEXT_FLAG,paintFlag);
    }

    public void withTextShadow(TextShadow textShadow) {
        values.put(TextStyle.SHADOW, textShadow);
    }

    public void withTextBorder(TextBorder textBorder){
        values.put(TextStyle.BORDER,textBorder);
    }

    void applyStyle(@NonNull TextView textView) {
        for (Map.Entry<TextStyle, Object> entry : values.entrySet()) {
            switch (entry.getKey()) {
                case SIZE: {
                    final float size = (float) entry.getValue();
                    applyTextSize(textView, size);
                }
                break;

                case COLOR: {
                    final int color = (int) entry.getValue();
                    applyTextColor(textView, color);
                }
                break;

                case FONT_FAMILY: {
                    final Typeface typeface = (Typeface) entry.getValue();
                    applyFontFamily(textView, typeface);
                }
                break;

                case GRAVITY: {
                    final int gravity = (int) entry.getValue();
                    applyGravity(textView, gravity);
                }
                break;

                case BACKGROUND: {
                    if (entry.getValue() instanceof Drawable) {
                        final Drawable bg = (Drawable) entry.getValue();
                        applyBackgroundDrawable(textView, bg);

                    } else if (entry.getValue() instanceof Integer) {
                        final int color = (Integer) entry.getValue();
                        applyBackgroundColor(textView, color);
                    }
                }
                break;

                case TEXT_APPEARANCE: {
                    if (entry.getValue() instanceof Integer) {
                        final int styleAppearance = (Integer)entry.getValue();
                        applyTextAppearance(textView, styleAppearance);
                    }
                }
                break;

                case TEXT_STYLE:{
                    final int typeface=(int) entry.getValue();
                    applyTextStyle(textView,typeface);
                }
                break;
                case TEXT_FLAG:{
                    int flag=(int) entry.getValue();
                    applyTextFlag(textView,flag);
                }
                break;

                case SHADOW: {
                    if (entry.getValue() instanceof TextShadow){
                        TextShadow textShadow=(TextShadow) entry.getValue();
                        applyTextShadow(textView,textShadow);
                    }

                }
                case BORDER: {
                    if (entry.getValue() instanceof TextBorder){
                        TextBorder textBorder=(TextBorder) entry.getValue();
                        applyTextBorder(textView,textBorder);
                    }

                }
            }
        }
    }

    protected void applyTextSize(TextView textView, float size) {
        textView.setTextSize(size);
    }

    protected void applyTextShadow(TextView textView, float radius, float dx, float dy, int color) {
        textView.setShadowLayer(radius, dx, dy, color);
    }

    protected void applyTextColor(TextView textView, int color) {
        textView.setTextColor(color);
    }

    protected void applyFontFamily(TextView textView, Typeface typeface) {
        textView.setTypeface(typeface);
    }

    protected void applyGravity(TextView textView, int gravity) {
        textView.setGravity(gravity);
    }

    protected void applyBackgroundColor(TextView textView, int color) {
        textView.setBackgroundColor(color);
    }

    protected void applyBackgroundDrawable(TextView textView, Drawable bg) {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN) {
            textView.setBackground(bg);
        } else {
            textView.setBackgroundDrawable(bg);
        }
    }

    // border
    protected  void applyTextBorder(TextView textView, TextBorder textBorder){
        GradientDrawable gd = new GradientDrawable();
        gd.setCornerRadius(textBorder.getCorner());
        gd.setStroke(textBorder.getStrokeWidth(), textBorder.getStrokeColor());
        gd.setColor(textBorder.getBackGroundColor());
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN) {
            textView.setBackground(gd);
        }
    }

    // shadow
    protected void applyTextShadow(TextView textView, TextShadow textShadow) {
        textView.setShadowLayer(textShadow.getRadius(), textShadow.getDx(), textShadow.getDy(), textShadow.getColor());
    }
    // bold or italic
    protected void applyTextStyle(TextView textView, int typeface) {
        textView.setTypeface(textView.getTypeface(),typeface);
    }

    // underline or strike
    protected void applyTextFlag(TextView textView, int flag) {
//        textView.setPaintFlags(textView.getPaintFlags()|flag);
        textView.getPaint().setFlags(flag);
    }

    protected void applyTextAppearance(TextView textView, int styleAppearance) {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            textView.setTextAppearance(styleAppearance);
        } else {
            textView.setTextAppearance(textView.getContext(), styleAppearance);
        }
    }

    /**
     * Enum to maintain current supported style properties used on on {@link PhotoEditor#addText(String, TextStyleBuilder)} and {@link PhotoEditor#editText(View, String, TextStyleBuilder)}
     */
    protected enum TextStyle {
        SIZE("TextSize"),
        COLOR("TextColor"),
        GRAVITY("Gravity"),
        FONT_FAMILY("FontFamily"),
        BACKGROUND("Background"),
        TEXT_APPEARANCE("TextAppearance"),
        TEXT_STYLE("TextStyle"),
        TEXT_FLAG("TextFlag"),
        SHADOW("Shadow"),
        BORDER("Border");

        TextStyle(String property) {
            this.property = property;
        }

        private final String property;
        public String getProperty() {return property;}
    }
}
