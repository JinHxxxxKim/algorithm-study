package programmers;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;

/**
 * BOJ_1717_집합의표현
 * @author parkrootseok
 *
 *  1. 집합 원소 개수와 연산의 개수를 받는다.
 *  2. Union & Find를 수행하기 위해 배열을 초기화
 *  3. 연산에 대한 정보를 받아 연산을 수행한다.
 **/
public class s1747_집합표현하기_GeunSeok {

    static final int UNION = 0;
    static final int FIND = 1;

    static BufferedReader br;
    static BufferedWriter bw;
    static StringBuilder sb;
    static String[] inputs;

    static int[] set;
    static int[] rank;
    static int setSize;
    static int compareNumber;

    public static void main(String[] args) throws IOException {

        br = new BufferedReader(new InputStreamReader(System.in));
        bw = new BufferedWriter(new OutputStreamWriter(System.out));
        sb = new StringBuilder();

        // 1. 집합 원소 개수와 연산의 개수를 받는다.
        inputs = br.readLine().trim().split(" ");
        setSize = Integer.parseInt(inputs[0]);
        compareNumber = Integer.parseInt(inputs[1]);

        // 2. Union & Find를 수행하기 위해 배열을 초기화
        init();

        // 3. 연산에 대한 정보를 받아 연산을 수행한다.
        for (int curCompareNumber = 0; curCompareNumber < compareNumber; curCompareNumber++) {

            inputs = br.readLine().trim().split(" ");

            int cmd = Integer.parseInt(inputs[0]);
            int a = Integer.parseInt(inputs[1]);
            int b = Integer.parseInt(inputs[2]);

            switch (cmd) {

                case UNION:
                    union(a, b);
                    break;

                case FIND:

                    if (find(a) == find(b)) {
                            sb.append("YES").append("\n");
                    } else {
                        sb.append("NO").append("\n");
                    }

                    break;

            }

        }

        bw.write(sb.toString());
        bw.close();
        return;

    }

    public static void init() {
        set = new int[setSize + 1];
        rank = new int[setSize + 1];

        for (int element = 0; element <= setSize; element++) {
            set[element] = element;
            rank[element] = 0;
        }

    }

    public static int find(int a) {

        if (a == set[a]) {
            return a;
        }

        return set[a] = find(set[a]);

    }

    public static void union(int a, int b) {

        int findA = find(a);
        int findB = find(b);

        if (findA == findB) {
            return;
        }

        if (rank[findA] > rank[findB]) {
            set[findB] = findA;
            return;
        }

        set[findA] = findB;

        if (rank[findA] == rank[findB]) {
            rank[findB]++;
        }

    }

}