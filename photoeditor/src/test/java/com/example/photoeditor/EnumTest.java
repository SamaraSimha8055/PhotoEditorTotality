package com.example.photoeditor;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

import com.example.photoeditor.shape.ShapeType;

public class EnumTest {

    @Test
    public void testNumberOfViewTypes() {
        assertEquals(ViewType.values().length, 4);
    }

    @Test
    public void testNumberOfShapeTypes() {
        assertEquals(ShapeType.values().length, 4);
    }

    @Test
    public void testNumberOfPhotoFilterTypes() {
        assertEquals(PhotoFilter.values().length, 24);
    }

}
