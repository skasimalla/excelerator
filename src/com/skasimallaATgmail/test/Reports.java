/*
 * Decompiled with CFR 0_114.
 */
package com.skasimallaATgmail.test;

import java.util.ArrayList;
import javax.swing.JTable;
import javax.swing.JTextArea;

/*
 * This class specifies class file version 49.0 but uses Java 6 signatures.  Assumed Java 6.
 */
public class Reports {
    int low;
    int high;
    int scale;
    int underflow_bits;
    int step;
    int code;
    int cursor;
    int temp;
    String message;
    String decoded_msg;
    ArrayList<CharCount> p;
    ArrayList<Integer> output;

    public void enterMessage(String msg, JTextArea log) {
        this.message = msg;
        this.scale = msg.length();
        this.p = Reports.getCharList(this.message);
        int cursor = 0;
        int i = 1;
        while (i < this.p.size()) {
            this.p.get((int)i).high = cursor += this.p.get((int)i).count;
            ++i;
        }
    }

    public void encode(JTextArea log) {
        this.low = 0;
        this.high = 255;
        this.underflow_bits = 0;
        this.step = 0;
        this.output = new ArrayList();
    }

    public void encodeStep(JTextArea log) {
        int range = this.high - this.low + 1;
        this.high = this.low + range * this.p.get((int)this.ci((char)this.message.charAt((int)this.step))).high / this.scale - 1;
        this.low += range * this.p.get((int)(this.ci((char)this.message.charAt((int)this.step)) - 1)).high / this.scale;
        do {
            if (Reports.msb(this.low) == Reports.msb(this.high)) {
                this.output.add(Reports.msb(this.low));
                while (this.underflow_bits > 0) {
                    this.output.add(Reports.not(Reports.msb(this.low)));
                    --this.underflow_bits;
                }
                this.low = this.low << 1 & 255;
                this.high = this.high << 1 & 255 | 1;
                continue;
            }
            if (Reports.ssb(this.low) != 1 || Reports.ssb(this.high) != 0) break;
            ++this.underflow_bits;
            this.low &= 63;
            this.high |= 64;
            this.low = this.low << 1 & 255;
            this.high = this.high << 1 & 255 | 1;
        } while (true);
        ++this.step;
        if (this.step >= this.message.length()) {
            this.output.add(Reports.ssb(this.low));
            while (this.underflow_bits >= 0) {
                this.output.add(Reports.not(Reports.msb(this.low)));
                --this.underflow_bits;
            }
            int i = 0;
            while (i < 8) {
                this.output.add(0);
                ++i;
            }
            this.underflow_bits = 0;
        }
    }

    public void decode(JTextArea log) {
        this.low = 0;
        this.high = 255;
        this.underflow_bits = 0;
        this.step = 0;
        this.code = Reports.parseCode(this.output);
        this.decoded_msg = "";
        this.cursor = 8;
    }

    public void decodeStep(JTextArea log) {
        int range = this.high - this.low + 1;
        this.temp = ((this.code - this.low + 1) * this.scale - 1) / range;
        CharCount symbol = this.findSymbol(this.temp);
        this.decoded_msg = String.valueOf(this.decoded_msg) + symbol.c;
        range = this.high - this.low + 1;
        this.high = this.low + range * this.p.get((int)this.ci((char)this.message.charAt((int)this.step))).high / this.scale - 1;
        this.low += range * this.p.get((int)(this.ci((char)this.message.charAt((int)this.step)) - 1)).high / this.scale;
        do {
            if (Reports.msb(this.low) == Reports.msb(this.high)) {
                this.low = this.low << 1 & 255;
                this.high = this.high << 1 & 255 | 1;
                this.code = this.code << 1 & 255 | this.output.get(this.cursor++);
                continue;
            }
            if (Reports.ssb(this.low) != 1 || Reports.ssb(this.high) != 0) break;
            this.code ^= 64;
            this.low &= 63;
            this.high |= 64;
            this.low = this.low << 1 & 255;
            this.high = this.high << 1 & 255 | 1;
            this.code = this.code << 1 & 255 | this.output.get(this.cursor++);
        } while (true);
        ++this.step;
        this.message.length();
    }

    static ArrayList<CharCount> getCharList(String msg) {
        ArrayList<CharCount> p = new ArrayList<CharCount>();
        p.add(new CharCount());
        int i = 0;
        while (i < msg.length()) {
            boolean found = false;
            int j = 0;
            while (j < p.size()) {
                if (msg.charAt(i) == p.get((int)j).c) {
                    ++p.get((int)j).count;
                    found = true;
                    break;
                }
                ++j;
            }
            if (!found) {
                p.add(new CharCount(msg.charAt(i)));
            }
            ++i;
        }
        return p;
    }

    void autoCalcProbabilities() {
        int cursor = 0;
        int i = 1;
        while (i < this.p.size()) {
            this.p.get((int)i).high = cursor += this.p.get((int)i).count;
            ++i;
        }
    }

    int ci(char ch) {
        int i = 1;
        while (i < this.p.size()) {
            if (ch == this.p.get((int)i).c) {
                return i;
            }
            ++i;
        }
        return -1;
    }

    static int msb(int n) {
        return (n & 255) >> 7;
    }

    static int ssb(int n) {
        return (n & 127) >> 6;
    }

    static int not(int bit) {
        if (bit == 0) {
            return 1;
        }
        return 0;
    }

    static int parseCode(ArrayList<Integer> codeBits) {
        String codeString = "";
        int i = 0;
        while (i < 8) {
            codeString = codeBits.get(i) == 1 ? String.valueOf(codeString) + "1" : String.valueOf(codeString) + "0";
            ++i;
        }
        return Integer.parseInt(codeString, 2);
    }

    CharCount findSymbol(int n) {
        int i = 1;
        while (i < this.p.size()) {
            if (n < this.p.get((int)i).high) {
                return this.p.get(i);
            }
            ++i;
        }
        return this.p.get(this.p.size() - 1);
    }

    public String bitString(int n) {
        String str = "";
        str = String.valueOf(str) + ((n & 255) >> 7);
        str = String.valueOf(str) + ((n & 127) >> 6);
        str = String.valueOf(str) + ((n & 63) >> 5);
        str = String.valueOf(str) + ((n & 31) >> 4);
        str = String.valueOf(str) + ((n & 15) >> 3);
        str = String.valueOf(str) + ((n & 7) >> 2);
        str = String.valueOf(str) + ((n & 3) >> 1);
        str = String.valueOf(str) + (n & 1);
        return str;
    }

    public String outputString() {
        String str = "";
        int i = 0;
        while (i < this.output.size()) {
            str = String.valueOf(str) + this.output.get(i);
            ++i;
        }
        if (str.endsWith("00000000")) {
            str = str.substring(0, str.length() - 8);
        }
        return str;
    }

    public void customProbabilities(JTable t, JTextArea log) {
        this.autoCalcProbabilities();
    }

    static class CharCount {
        char c;
        int count;
        int high;

        public CharCount(char pc) {
            this.c = pc;
            this.count = 1;
        }

        public CharCount() {
            this.c = '\u0000';
            this.high = 0;
        }
    }

}

