package lb2;

import java.io.FileWriter;

public class M411LB2 {

    public static String getVorname() {
        return "Joel";
    }

    public static String getNachname() {
        return "Blaser";
    }

    /**
     * Gibt den grössten gemeinsamen Teiler von a und b zurück.
     */
    public static int getGGT(int a, int b) {
        if (a == b)
            return a;
        if (a > b)
            return getGGT(a - b, b);
        return getGGT(a, b - a);
    }

    public static int nachbarn(int[][] Welt) {
        if (Welt.length != 3 || Welt[0].length != 3 || Welt[1].length != 3 || Welt[2].length != 3)
            return -1;
        int sum = 0;
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                sum += Welt[i][j];
            }
        }
        if (Welt[1][1] == 1)
            sum--;
        return sum;
    }

    public static String caesar(int key, String text) {
        text = text.toLowerCase();
        char[] arr = text.toCharArray();
        for (int i = 0; i < arr.length; i++) {
            int k = key;
            if (k < 0) {
                while (k < 0) {
                    if (arr[i] == 'a')
                        arr[i] = 'z';
                    else if (arr[i] != ' ')
                        arr[i]--;
                    k++;
                }
            } else {
                while (k > 0) {
                    if (arr[i] == 'z')
                        arr[i] = 'a';
                    else if (arr[i] != ' ')
                        arr[i]++;
                    k--;
                }
            }
        }
        StringBuilder s = new StringBuilder();
        for (char c : arr) {
            s.append(c);
        }
        return s.toString().toUpperCase();
    }

    public static void writeToFile(String fileName, String text) {
        try {
            FileWriter writer = new FileWriter(fileName);
            writer.write(text);
            writer.close();
        } catch (Exception ignored) { }
    }

    public static int[] insertionSort(int [] unsorted) {
        int length = unsorted.length;
        for (int i = 1; i < length; i++) {
            int k = unsorted[i];
            for (int j = i - 1; j >= 0 ; j--) {
                if (unsorted[j] > k) {
                    unsorted[j + 1] = unsorted[j];
                    unsorted[j] = k;
                }
            }
        }
        return unsorted;
    }

    public static void main(String[] args) {
        System.out.println("LB2-M411");
        System.out.println("=========");
        System.out.println();
        System.out.println("Lösung von " + getVorname() + " " + getNachname());
        System.out.println("grösster gemeinsamen Teiler " + getGGT(100, 15));
        System.out.println("Nachbarn w1: " + nachbarn(welt_1));
        System.out.println("Nachbarn w2: " + nachbarn(welt_2));
        System.out.println(caesar(3, "Die Erde ist keine Scheibe"));
    }

    private static int[][] welt_1 = {
            {1, 0, 0},
            {1, 1, 0},
            {0, 0, 1}
    };
    private static int[][] welt_2 = {
            {0, 0, 0},
            {0, 0, 0}
    };
}
