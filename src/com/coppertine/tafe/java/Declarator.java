/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.coppertine.tafe.java;

/**
 *
 * @author Coppertine
 */
public class Declarator {

    /**
     * Object for Declarator.
     */
    private Object obj;
    /**
     * String that is used for labels, text or simular.
     */
    private String name;
    /**
     * Vector2 of the position.
     */
    private Vector2 pos;
    /**
     * Width of the object.
     */
    private int width;
    /**
     * Length of the object.
     */
    private int length;

    /**
     *
     * @param object Object that is used to be Generated.
     * @param inputName String of the object label.
     * @param position Vector2 of the Position
     * @param sizeWidth Width of the object
     * @param sizeLength Length of the object
     */
    public Declarator(
            final Object object, final String inputName,
            final Vector2 position, final int sizeWidth,
            final int sizeLength) {
        this.obj = object;
        this.name = inputName;
        this.pos = position;
        this.width = sizeWidth;
        this.length = sizeLength;
    }

    /**
     *
     * @return Object Declarator object
     */
    public final Object getObj() {
        return obj;
    }

    /**
     *
     * @param objInput Object of the declarator.
     */
    public final void setObj(final Object objInput) {
        this.obj = objInput;
    }

    /**
     *
     * @return String Object name.
     */
    public final String getName() {
        return name;
    }

    /**
     *
     * @param nameInput label string of the object.
     */
    public final void setName(final String nameInput) {
        this.name = nameInput;
    }

    /**
     *
     * @return Vector2 Position.
     */
    public final Vector2 getPos() {
        return pos;
    }

    /**
     *
     * @param posInput Vector2 Position.
     */
    public final void setPos(final Vector2 posInput) {
        this.pos = posInput;
    }

    /**
     *
     * @return Integer width of the object.
     */
    public final int getWidth() {
        return width;
    }

    /**
     *
     * @param widthInput Integer width of the object.
     */
    public final void setWidth(final int widthInput) {
        this.width = widthInput;
    }

    /**
     *
     * @return Integer Length of the object.
     */
    public final int getLength() {
        return length;
    }

    /**
     *
     * @param lengthInput Integer Length of the object.
     */
    public final void setLength(final int lengthInput) {
        this.length = lengthInput;
    }
}
