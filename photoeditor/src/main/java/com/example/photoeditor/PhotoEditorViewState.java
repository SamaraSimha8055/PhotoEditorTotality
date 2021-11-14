package com.example.photoeditor;

import android.view.View;

import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

class PhotoEditorViewState {

    private View currentSelectedView;
    private final List<View> addedViews;
    private final Stack<View> redoViews;

    PhotoEditorViewState() {
        this.currentSelectedView = null;
        this.addedViews = new ArrayList<>();
        this.redoViews = new Stack<>();
    }

    View getCurrentSelectedView() {
        return currentSelectedView;
    }

    void setCurrentSelectedView(View currentSelectedView) {
        this.currentSelectedView = currentSelectedView;
    }

    void clearCurrentSelectedView() {
        this.currentSelectedView = null;
    }

    View getAddedView(int index) {
        return addedViews.get(index);
    }

    int getAddedViewsCount() {
        return addedViews.size();
    }

    void clearAddedViews() {
        addedViews.clear();
    }

    void addAddedView(final View view) {
        addedViews.add(view);
    }

    void removeAddedView(final View view) {
        addedViews.remove(view);
    }

    View removeAddedView(final int index) {
        return addedViews.remove(index);
    }

    boolean containsAddedView(final View view) {
        return addedViews.contains(view);
    }

    void replaceAddedView(final View view) {
        final int i = addedViews.indexOf(view);
        if (i > -1) {
            addedViews.set(i, view);
        }
    }

    void clearRedoViews() {
        redoViews.clear();
    }

    void pushRedoView(final View view) {
        redoViews.push(view);
    }

    void popRedoView() {
        redoViews.pop();
    }

    int getRedoViewsCount() {
        return redoViews.size();
    }

    View getRedoView(int index) {
        return redoViews.get(index);
    }
}
