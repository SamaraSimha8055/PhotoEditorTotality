package com.example.photoeditor;

interface BrushViewChangeListener {
    void onViewAdd(DrawingView drawingView);

    void onViewRemoved(DrawingView drawingView);

    void onStartDrawing();

    void onStopDrawing();
}
