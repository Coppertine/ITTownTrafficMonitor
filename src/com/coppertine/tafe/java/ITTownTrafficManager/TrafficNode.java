/*
 * The MIT License
 *
 * Copyright 2019 Coppertine.
 *
 * Permission is hereby granted, free of charge, to any person obtaining a copy
 * of this software and associated documentation files (the "Software"), to deal
 * in the Software without restriction, including without limitation the rights
 * to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
 * copies of the Software, and to permit persons to whom the Software is
 * furnished to do so, subject to the following conditions:
 *
 * The above copyright notice and this permission notice shall be included in
 * all copies or substantial portions of the Software.
 *
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
 * IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
 * FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
 * AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
 * LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
 * OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN
 * THE SOFTWARE.
 */
package com.coppertine.tafe.java.ITTownTrafficManager;

import java.time.LocalDateTime;
import java.util.Objects;

/**
 *
 * @author Coppertine
 */
public class TrafficNode {

    /**
     * The LocalDateTime value of the traffic node.
     */
    private LocalDateTime time;
    /**
     * The key value in Integer form for the traffic node.
     */
    private int intKey;

    /**
     * The constructor for the traffic node.
     *
     * @param key The LocalDateTime or Integer values of the key.
     */
    public TrafficNode(final Object key) {
        if (key instanceof LocalDateTime) {
            this.time = (LocalDateTime) key;
        } else if (key instanceof Integer) {
            this.intKey = (Integer) key;
        }
    }

    @Override
    public final boolean equals(final Object value) {
        if (value instanceof LocalDateTime) {
            return this.time.equals(value);
        } else if (value instanceof Integer) {
            return this.intKey == (int) value;
        } else {
            return false;
        }
    }
    /**
     * The initial value for the hash code calculation.
     */
    private static final int HASH_INITIAL_VALUE = 7;
    /**
     * The multiply value for the hash code calculation.
     */
    private static final int HASH_MULTIPLY_VALUE = 61;

    @Override
    @SuppressWarnings("all")
    public final int hashCode() {
        int hash = HASH_INITIAL_VALUE;
        hash = HASH_MULTIPLY_VALUE * hash + Objects.hashCode(this.time);
        hash = HASH_MULTIPLY_VALUE * hash + this.intKey;
        return hash;
    }

    @Override
    public final String toString() {
        return "TrafficNode{" + "time=" + time + ", intKey=" + intKey + '}';
    }

}
