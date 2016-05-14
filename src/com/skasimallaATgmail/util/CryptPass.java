/*
 * Decompiled with CFR 0_114.
 */
package com.skasimallaATgmail.util;

import java.io.InputStream;
import java.io.PrintStream;
import java.net.InetAddress;
import java.net.UnknownHostException;
import java.util.Hashtable;
import java.util.Scanner;

class CryptPass {
    CryptPass() {
    }

    public static void main(String[] args) {
        try {
            System.out.println(InetAddress.getLocalHost().getCanonicalHostName());
        }
        catch (UnknownHostException e) {
            e.printStackTrace();
        }
        System.out.print("Enter the String to be encrypted: ");
        Scanner scanner = new Scanner(System.in);
        String str = scanner.nextLine();
        scanner.close();
        System.out.println("The encrypted String is:" + CryptPass.encrypt(str));
    }

    public static String decrypt(String encString) {
        StringBuffer stringBuffer = new StringBuffer(0);
        String string = "";
        String string2 = "";
        String string3 = "";
        string = encString.trim();
        if (string.length() % 5 != 0) {
            System.out.println("Invalid encrypted passwd.");
            return "ERROR";
        }
        Hashtable<String, String> hashtable = new Hashtable<String, String>(100);
        hashtable.put("01266", " ");
        hashtable.put("01267", "!");
        hashtable.put("01268", "\"");
        hashtable.put("01269", "#");
        hashtable.put("01270", "$");
        hashtable.put("01271", "%");
        hashtable.put("01272", "&");
        hashtable.put("01273", "'");
        hashtable.put("01274", "(");
        hashtable.put("01275", ")");
        hashtable.put("01276", "*");
        hashtable.put("01277", "+");
        hashtable.put("01278", ",");
        hashtable.put("01279", "-");
        hashtable.put("01280", ".");
        hashtable.put("01281", "/");
        hashtable.put("01282", "0");
        hashtable.put("01283", "1");
        hashtable.put("01284", "2");
        hashtable.put("01285", "3");
        hashtable.put("01286", "4");
        hashtable.put("01287", "5");
        hashtable.put("01288", "6");
        hashtable.put("01289", "7");
        hashtable.put("01290", "8");
        hashtable.put("01291", "9");
        hashtable.put("01292", ":");
        hashtable.put("01293", ";");
        hashtable.put("01294", "<");
        hashtable.put("01295", "=");
        hashtable.put("01296", ">");
        hashtable.put("01297", "?");
        hashtable.put("01298", "@");
        hashtable.put("01299", "A");
        hashtable.put("01300", "B");
        hashtable.put("01301", "C");
        hashtable.put("01302", "D");
        hashtable.put("01303", "E");
        hashtable.put("01304", "F");
        hashtable.put("01305", "G");
        hashtable.put("01306", "H");
        hashtable.put("01307", "I");
        hashtable.put("01308", "J");
        hashtable.put("01309", "K");
        hashtable.put("01310", "L");
        hashtable.put("01311", "M");
        hashtable.put("01312", "N");
        hashtable.put("01313", "O");
        hashtable.put("01314", "P");
        hashtable.put("01315", "Q");
        hashtable.put("01316", "R");
        hashtable.put("01317", "S");
        hashtable.put("01318", "T");
        hashtable.put("01319", "U");
        hashtable.put("01320", "V");
        hashtable.put("01321", "W");
        hashtable.put("01322", "X");
        hashtable.put("01323", "Y");
        hashtable.put("01324", "Z");
        hashtable.put("01325", "[");
        hashtable.put("01326", "\\");
        hashtable.put("01327", "]");
        hashtable.put("01328", "^");
        hashtable.put("01329", "_");
        hashtable.put("01330", "`");
        hashtable.put("01331", "a");
        hashtable.put("01332", "b");
        hashtable.put("01333", "c");
        hashtable.put("01334", "d");
        hashtable.put("01335", "e");
        hashtable.put("01336", "f");
        hashtable.put("01337", "g");
        hashtable.put("01338", "h");
        hashtable.put("01339", "i");
        hashtable.put("01340", "j");
        hashtable.put("01341", "k");
        hashtable.put("01342", "l");
        hashtable.put("01343", "m");
        hashtable.put("01344", "n");
        hashtable.put("01345", "o");
        hashtable.put("01346", "p");
        hashtable.put("01347", "q");
        hashtable.put("01348", "r");
        hashtable.put("01349", "s");
        hashtable.put("01350", "t");
        hashtable.put("01351", "u");
        hashtable.put("01352", "v");
        hashtable.put("01353", "w");
        hashtable.put("01354", "x");
        hashtable.put("01355", "y");
        hashtable.put("01356", "z");
        hashtable.put("01357", "{");
        hashtable.put("01358", "|");
        hashtable.put("01359", "}");
        hashtable.put("01360", "~");
        string2 = string;
        int i = 0;
        while (i < string.length() / 5) {
            string3 = string2.substring(0, 5);
            string2 = string2.substring(5);
            stringBuffer.append((String)hashtable.get(string3));
            ++i;
        }
        return stringBuffer.toString();
    }

    public static String encrypt(String decString) {
        Hashtable<String, String> hashtable = new Hashtable<String, String>(100);
        hashtable.put(" ", "01266");
        hashtable.put("!", "01267");
        hashtable.put("\\", "01268");
        hashtable.put("#", "01269");
        hashtable.put("$", "01270");
        hashtable.put("%", "01271");
        hashtable.put("&", "01272");
        hashtable.put("'", "01273");
        hashtable.put("(", "01274");
        hashtable.put(")", "01275");
        hashtable.put("*", "01276");
        hashtable.put("+", "01277");
        hashtable.put(",", "01278");
        hashtable.put("-", "01279");
        hashtable.put(".", "01280");
        hashtable.put("/", "01281");
        hashtable.put("0", "01282");
        hashtable.put("1", "01283");
        hashtable.put("2", "01284");
        hashtable.put("3", "01285");
        hashtable.put("4", "01286");
        hashtable.put("5", "01287");
        hashtable.put("6", "01288");
        hashtable.put("7", "01289");
        hashtable.put("8", "01290");
        hashtable.put("9", "01291");
        hashtable.put(":", "01292");
        hashtable.put(";", "01293");
        hashtable.put("<", "01294");
        hashtable.put("=", "01295");
        hashtable.put(">", "01296");
        hashtable.put("?", "01297");
        hashtable.put("@", "01298");
        hashtable.put("A", "01299");
        hashtable.put("B", "01300");
        hashtable.put("C", "01301");
        hashtable.put("D", "01302");
        hashtable.put("E", "01303");
        hashtable.put("F", "01304");
        hashtable.put("G", "01305");
        hashtable.put("H", "01306");
        hashtable.put("I", "01307");
        hashtable.put("J", "01308");
        hashtable.put("K", "01309");
        hashtable.put("L", "01310");
        hashtable.put("M", "01311");
        hashtable.put("N", "01312");
        hashtable.put("O", "01313");
        hashtable.put("P", "01314");
        hashtable.put("Q", "01315");
        hashtable.put("R", "01316");
        hashtable.put("S", "01317");
        hashtable.put("T", "01318");
        hashtable.put("U", "01319");
        hashtable.put("V", "01320");
        hashtable.put("W", "01321");
        hashtable.put("X", "01322");
        hashtable.put("Y", "01323");
        hashtable.put("Z", "01324");
        hashtable.put("[", "01325");
        hashtable.put("\\", "01326");
        hashtable.put("]", "01327");
        hashtable.put("^", "01328");
        hashtable.put("_", "01329");
        hashtable.put("`", "01330");
        hashtable.put("a", "01331");
        hashtable.put("b", "01332");
        hashtable.put("c", "01333");
        hashtable.put("d", "01334");
        hashtable.put("e", "01335");
        hashtable.put("f", "01336");
        hashtable.put("g", "01337");
        hashtable.put("h", "01338");
        hashtable.put("i", "01339");
        hashtable.put("j", "01340");
        hashtable.put("k", "01341");
        hashtable.put("l", "01342");
        hashtable.put("m", "01343");
        hashtable.put("n", "01344");
        hashtable.put("o", "01345");
        hashtable.put("p", "01346");
        hashtable.put("q", "01347");
        hashtable.put("r", "01348");
        hashtable.put("s", "01349");
        hashtable.put("t", "01350");
        hashtable.put("u", "01351");
        hashtable.put("v", "01352");
        hashtable.put("w", "01353");
        hashtable.put("x", "01354");
        hashtable.put("y", "01355");
        hashtable.put("z", "01356");
        hashtable.put("{", "01357");
        hashtable.put("|", "01358");
        hashtable.put("}", "01359");
        hashtable.put("~", "01360");
        StringBuffer stringBuffer = new StringBuffer(0);
        int i = 0;
        while (i < decString.length()) {
            stringBuffer.append((String)hashtable.get(decString.substring(i, i + 1)));
            ++i;
        }
        return stringBuffer.toString();
    }
}

