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
public class Vector2 {

    /**
     * Position X.
     */
    public int x;

    /**
     * Position Y.
     */
    public int y;

    /**
     *
     */
    public Vector2() {
        this.x = 0;
        this.y = 0;
    }

    /**
     *
     * @param inputX X Value
     * @param inputY Y Value
     */
    public Vector2(final int inputX, final int inputY) {
        this.x = inputX;
        this.y = inputY;
    }
}
