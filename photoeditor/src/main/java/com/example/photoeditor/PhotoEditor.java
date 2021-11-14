package com.example.photoeditor;

import android.Manifest;
import android.annotation.SuppressLint;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Typeface;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.ColorInt;
import androidx.annotation.IntRange;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.annotation.RequiresPermission;
import androidx.annotation.UiThread;

import com.example.photoeditor.shape.ShapeBuilder;

public interface PhotoEditor {

    void addImage(Bitmap desiredImage);

    @SuppressLint("ClickableViewAccessibility")
    void addText(String text, int colorCodeTextView);

    @SuppressLint("ClickableViewAccessibility")
    void addText(@Nullable Typeface textTypeface, String text, int colorCodeTextView);

    @SuppressLint("ClickableViewAccessibility")
    void addText(String text, @Nullable TextStyleBuilder styleBuilder);

    void editText(@NonNull View view, String inputText, int colorCode);

    void editText(@NonNull View view, @Nullable Typeface textTypeface, String inputText, int colorCode);

    void editText(@NonNull View view, String inputText, @Nullable TextStyleBuilder styleBuilder);

    void addEmoji(String emojiName);

    void addEmoji(Typeface emojiTypeface, String emojiName);

    void setBrushDrawingMode(boolean brushDrawingMode);

    Boolean getBrushDrawableMode();

    @Deprecated
    void setBrushSize(float size);

    @Deprecated
    void setOpacity(@IntRange(from = 0, to = 100) int opacity);

    @Deprecated
    void setBrushColor(@ColorInt int color);

    void setBrushEraserSize(float brushEraserSize);

    float getEraserSize();

    float getBrushSize();

    int getBrushColor();

    void brushEraser();

    boolean undo();

    boolean redo();

    void clearAllViews();

    @UiThread
    void clearHelperBox();

    void setFilterEffect(CustomEffect customEffect);

    void setFilterEffect(PhotoFilter filterType);

    @RequiresPermission(allOf = {Manifest.permission.WRITE_EXTERNAL_STORAGE})
    void saveAsFile(@NonNull String imagePath, @NonNull OnSaveListener onSaveListener);

    @SuppressLint("StaticFieldLeak")
    @RequiresPermission(allOf = {Manifest.permission.WRITE_EXTERNAL_STORAGE})
    void saveAsFile(@NonNull String imagePath,
                    @NonNull SaveSettings saveSettings,
                    @NonNull OnSaveListener onSaveListener);

    @SuppressLint("StaticFieldLeak")
    void saveAsBitmap(@NonNull OnSaveBitmap onSaveBitmap);

    @SuppressLint("StaticFieldLeak")
    void saveAsBitmap(@NonNull SaveSettings saveSettings,
                      @NonNull OnSaveBitmap onSaveBitmap);

    void setOnPhotoEditorListener(@NonNull OnPhotoEditorListener onPhotoEditorListener);

    boolean isCacheEmpty();

    class Builder {

        Context context;
        PhotoEditorView parentView;
        ImageView imageView;
        View deleteView;
        DrawingView drawingView;
        Typeface textTypeface;
        Typeface emojiTypeface;
        // By default, pinch-to-scale is enabled for text
        boolean isTextPinchScalable = true;
        boolean clipSourceImage = false;

        public Builder(Context context, PhotoEditorView photoEditorView) {
            this.context = context;
            parentView = photoEditorView;
            imageView = photoEditorView.getSource();
            drawingView = photoEditorView.getDrawingView();
        }

        Builder setDeleteView(View deleteView) {
            this.deleteView = deleteView;
            return this;
        }

        public Builder setDefaultTextTypeface(Typeface textTypeface) {
            this.textTypeface = textTypeface;
            return this;
        }


        public Builder setDefaultEmojiTypeface(Typeface emojiTypeface) {
            this.emojiTypeface = emojiTypeface;
            return this;
        }

        public Builder setPinchTextScalable(boolean isTextPinchScalable) {
            this.isTextPinchScalable = isTextPinchScalable;
            return this;
        }

        public PhotoEditor build() {
            return new PhotoEditorImpl(this);
        }


        public Builder setClipSourceImage(boolean clip) {
            this.clipSourceImage = clip;
            return this;
        }
    }


    interface OnSaveListener {


        void onSuccess(@NonNull String imagePath);

        void onFailure(@NonNull Exception exception);
    }


    // region Shape
    void setShape(ShapeBuilder shapebuilder);
    // endregion

}
