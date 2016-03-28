package com.magooup.learn.js;

/**
 * Created by zhiyong.ma on 2016/3/25.
 * from "http://utf-8.jp/public/jjencode.html"
 */
public class JsEncoder {
    public static void main(String[] args) throws Throwable {

        System.out.println(encode("function x() {\n" +
                "\n" +
                "\t\tvar r = document.referrer;\n" +
                "\t\tvar target1 = \"xxx\";\n" +
                "\t\tif(r.substring(0,target1.length) == target1) {\n" +
                "\t\t\tdocument.write(\"<\"+\"script type='text/javascript' src='g.js'></\"+\"script>\");\n" +
                "\t\t} else {\n" +
                "\t\t\tdocument.write(\"<\"+\"script type='text/javascript' src='h.js'></\"+\"script>\");\n" +
                "\t\t}\n" +
                "\t}"));
    }


    public static String encode(String text) {
        String gv = "$";
        String r = "";
        char n;
        String[] b = {"___", "__$", "_$_", "_$$", "$__", "$_$", "$$_", "$$$", "$___", "$__$", "$_$_", "$_$$", "$$__", "$$_$", "$$$_", "$$$$", ""};
        String s = "";
        for (int i = 0; i < text.length(); i++) {
            n = text.charAt(i);
            if (n == 0x22 || n == 0x5c) {
                s += "\\\\\\" + n;
            } else if ((0x21 <= n && n <= 0x2f) || (0x3A <= n && n <= 0x40) || (0x5b <= n && n <= 0x60) || (0x7b <= n && n <= 0x7f)) {
                //}else if( (0x20 <= n && n <= 0x2f) || (0x3A <= n == 0x40) || ( 0x5b <= n && n <= 0x60 ) || ( 0x7b <= n && n <= 0x7f ) ){
                s += n;
            } else if ((0x30 <= n && n <= 0x39) || (0x61 <= n && n <= 0x66)) {
                if (null != s && s.length() > 0) {
                    r += "\"" + s + "\"+";
                }
                r += gv + "." + b[n < 0x40 ? n - 0x30 : n - 0x57] + "+";
                s = "";
            } else if (n == 0x6c) { // 'l'
                if (null != s && s.length() > 0) {
                    r += "\"" + s + "\"+";
                }
                r += "(![]+\"\")[" + gv + "._$_]+";
                s = "";
            } else if (n == 0x6f) { // 'o'
                if (null != s && s.length() > 0) {
                    r += "\"" + s + "\"+";
                }
                r += gv + "._$+";
                s = "";
            } else if (n == 0x74) { // 'u'
                if (null != s && s.length() > 0) {
                    r += "\"" + s + "\"+";
                }
                r += gv + ".__+";
                s = "";
            } else if (n == 0x75) { // 'u'
                if (null != s && s.length() > 0) {
                    r += "\"" + s + "\"+";
                }
                r += gv + "._+";
                s = "";
            } else if (n < 128) {
                if (null != s && s.length() > 0) {
                    r += "\"" + s;
                } else {
                    r += "\"";
                }
                r += "\\\\\"+" + transformOct(Integer.toOctalString((int) n), b, gv);
                s = "";
            } else {
                if (null != s && s.length() > 0) {
                    r += "\"" + s;
                } else {
                    r += "\"";
                }
                r += "\\\\\"+" + gv + "._+" + transformHex(Integer.toHexString((int) n), b, gv);
                s = "";
            }
        }
        if (null != s && s.length() > 0) {
            r += "\"" + s + "\"+";
        }
        r = gv + "=~[];" +
                gv + "={___:++" + gv + ",$$$$:(![]+\"\")[" + gv + "],__$:++" + gv + ",$_$_:(![]+\"\")[" + gv + "],_$_:++" +
                gv + ",$_$$:({}+\"\")[" + gv + "],$$_$:(" + gv + "[" + gv + "]+\"\")[" + gv + "],_$$:++" + gv + ",$$$_:(!\"\"+\"\")[" +
                gv + "],$__:++" + gv + ",$_$:++" + gv + ",$$__:({}+\"\")[" + gv + "],$$_:++" + gv + ",$$$:++" + gv + ",$___:++" + gv + ",$__$:++" +
                gv + "};" +
                gv + ".$_=" +
                "(" + gv + ".$_=" + gv + "+\"\")[" + gv + ".$_$]+" +
                "(" + gv + "._$=" + gv + ".$_[" + gv + ".__$])+" +
                "(" + gv + ".$$=(" + gv + ".$+\"\")[" + gv + ".__$])+" +
                "((!" + gv + ")+\"\")[" + gv + "._$$]+" +
                "(" + gv + ".__=" + gv + ".$_[" + gv + ".$$_])+" +
                "(" + gv + ".$=(!\"\"+\"\")[" + gv + ".__$])+" +
                "(" + gv + "._=(!\"\"+\"\")[" + gv + "._$_])+" +
                gv + ".$_[" + gv + ".$_$]+" +
                gv + ".__+" +
                gv + "._$+" +
                gv + ".$;" +
                gv + ".$$=" +
                gv + ".$+" +
                "(!\"\"+\"\")[" + gv + "._$$]+" +
                gv + ".__+" +
                gv + "._+" +
                gv + ".$+" +
                gv + ".$$;" +
                gv + ".$=(" + gv + ".___)[" + gv + ".$_][" + gv + ".$_];" +
                gv + ".$(" + gv + ".$(" + gv + ".$$+\"\\\"\"+" + r + "\"\\\"\")())();";

        return r;
    }

    static String transformOct(String oct, String[] b, String gv) {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < oct.length(); i++) {
            char c = oct.charAt(i);
            sb.append(gv + ".").append(b[Integer.parseInt(c + "", 8)]).append("+");
        }
        return sb.toString();
    }

    static String transformHex(String hex, String[] b, String gv) {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < hex.length(); i++) {
            char c = hex.charAt(i);
            sb.append(gv + ".").append(b[Integer.parseInt(c + "", 16)]).append("+");
        }
        return sb.toString();
    }
}
