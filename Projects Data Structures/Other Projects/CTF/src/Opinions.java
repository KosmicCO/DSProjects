
import java.util.ArrayList;

public class Opinions {

    public Opinions() {
    }

    public static void main(String[] wvwv) {
        java.util.Scanner s = new java.util.Scanner(System.in);
        System.out.println("Enter flag: ");
        String input = "lqqrj]mqcrlccasmpcwr"; // start
        ArrayList<String> substrings = new ArrayList();
        for (int i = 0; i < input.length(); i++) {
            substrings.add(input.substring(i));
        }
        ArrayList<Character> endChars = new ArrayList();
        int size = substrings.size();
        char c;
        int n;
        java.util.Iterator<Character> it;
        int noom;
        switch (size) {
            case 20:
//                int num = 4;
                for (String x : substrings) {
//        if ((num % 2 != 0) || (num % 3 != 1) || (num % 2 == 0) || (num % 3 == 1)) {
                    c = x.charAt(0);
                    c = (char) (c + '\002');
                    endChars.add(c);
//                    num++;
//        }
//        if ((num % 7 != 0) || (num % 4 != 1) || (num % 7 == 0) || (num % 4 == 1)) {
                    c = x.charAt(0);
                    c = (char) (c - '\001');
                    endChars.add(c);
//        }
//        if ((num % 5 != 1) || (num % 23 != 0) || (num % 5 == 1) || (num % 23 == 0)) {
                    c = x.charAt(0);
                    c = (char) (c + '\005');
                    endChars.add(c);
//        }
                }
                n = 0;
                for (it = endChars.iterator(); it.hasNext();) {
                    it.next();
                    if (n % 2 != 0) {
                        it.remove();
                    }
                    n++;
                }
                break;
            case 22:
                noom = 4;
                for (String x : substrings) {
                    if (noom >= 9) {
                        endChars.add(x.charAt(0));
//                        noom++;
                    } else { //if (noom % 1 == 0) {
                        c = x.charAt(x.length() - 1);
                        c = (char) (c + '\002');
                        endChars.add(c);
//                        noom++;
                    }
                    noom++;
                }
        }

        String converted = "";
        for (Character d : endChars) {
            converted = converted + Character.toString(d);
        }
        System.out.println(converted);
        String message = converted.equals("jmrehshk^knradsjmdadbqtnnqduxs") ? "You found his opinion!" : "Big oof";
        System.out.println(message);
    }
}

// jm r eh s hk ^ kn r ad s jm d ad b qt n nq d ux s
// 
