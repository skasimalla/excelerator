/*
 * Decompiled with CFR 0_114.
 */
package com.skasimallaATgmail.test;

import java.io.PrintStream;
import java.util.HashMap;
import java.util.Stack;

public class acalgo {
    HashMap<Character, Float> hmap = new HashMap();
    HashMap<Character, Float> cdf = new HashMap();
    Stack<Float> l = new Stack();
    Stack<Float> h = new Stack();

    acalgo() {
        this.hmap.put(Character.valueOf(' '), Float.valueOf(0.0f));
        this.hmap.put(Character.valueOf('a'), Float.valueOf(0.8f));
        this.hmap.put(Character.valueOf('b'), Float.valueOf(0.02f));
        this.hmap.put(Character.valueOf('c'), Float.valueOf(0.18f));
        this.cdf.put(Character.valueOf(' '), Float.valueOf(0.0f));
        this.cdf.put(Character.valueOf('a'), Float.valueOf(0.8f));
        this.cdf.put(Character.valueOf('b'), Float.valueOf(0.82f));
        this.cdf.put(Character.valueOf('c'), Float.valueOf(1.0f));
        this.l.push(Float.valueOf(0.0f));
        this.h.push(Float.valueOf(1.0f));
    }

    public float cdf(char c) {
        float v = 0.0f;
        return v;
    }

    public Float encode() {
        Float f = Float.valueOf(0.0f);
        char[] S = new char[]{' ', 'a', 'c', 'b', 'a'};
        Float r = Float.valueOf(1.0f);
        int i = 1;
        while (i < 5) {
            System.out.println(" r " + r + "   " + S[i] + "  " + S[i - 1] + "  " + this.cdf.get(Character.valueOf(S[i - 1])) + "  " + this.cdf.get(Character.valueOf(S[i])));
            Float t = Float.valueOf(this.l.get(i - 1).floatValue() + Float.valueOf(r.floatValue() * this.cdf.get(Character.valueOf(S[i - 1])).floatValue()).floatValue());
            this.l.push(t);
            t = Float.valueOf(this.l.get(i - 1).floatValue() + Float.valueOf(r.floatValue() * this.cdf.get(Character.valueOf(S[i])).floatValue()).floatValue());
            this.h.push(t);
            r = Float.valueOf(this.h.get(i).floatValue() - this.l.get(i).floatValue());
            System.out.println("low :  " + this.l.get(i) + "  high :  " + this.h.get(i) + "   range  :  " + r);
            ++i;
        }
        return f;
    }

    public static void main(String[] args) {
        System.out.println("Arithematic Encoder \nenter the characters: ");
        acalgo obj = new acalgo();
        obj.encode();
    }
}

