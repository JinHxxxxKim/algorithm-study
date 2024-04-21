package algorithm_study.BOJ;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/**
 * [ inputTestCase() ]
 * 1. 집합을 구성하는 숫자의 개수 n, 연산의 개수 m 을 입력받는다.
 *
 * [ make() ]
 * 2. 각 숫자로 본인을 부모로 하는 초기 집합의 상태를 만든다.
 *
 * [ find(), union() ]
 * 3. m 개의 연산을 입력 받으며 0 이면 union, 1 이면 각 숫자를 find 하여 나온 부모를 비교한다.
 *  3-1. 부모가 같다면 YES, 다르다면 NO 를 출력한다.
 */
public class s1717_집합의표현_SangHu {
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static StringBuilder sb = new StringBuilder();
    static StringTokenizer st;

    static int numCount, operCount;
    static int[] parents;

    public static void union(int rootA, int rootB) {
        int parentA = find(rootA);
        int parentB = find(rootB);

        if (parentA > parentB) {
            parents[parentB] = parentA;
        } else {
            parents[parentA] = parentB;
        }
    }

    public static int find(int root) {
        if (parents[root] == root) {
            return root;
        }

        return parents[root] = find(parents[root]);
    }

    public static void make() {
        parents = new int[numCount+1];

        for (int idx = 0; idx < numCount+1; idx++) {
            parents[idx] = idx;
        }
    }

    public static void main(String[] args) throws IOException {
        inputTestCase();

        System.out.println(sb);
    }

    public static void inputTestCase() throws IOException {
        // 1. 집합을 구성하는 숫자의 개수 n, 연산의 개수 m 을 입력받는다.
        st = new StringTokenizer(br.readLine().trim());
        numCount = Integer.parseInt(st.nextToken());
        operCount = Integer.parseInt(st.nextToken());

        // 2. 각 숫자로 본인을 부모로 하는 초기 집합의 상태를 만든다.
        make();

        for (int oper = 0; oper < operCount; oper++) {
            st = new StringTokenizer(br.readLine().trim());
            int operate = Integer.parseInt(st.nextToken());
            int rootA = Integer.parseInt(st.nextToken());
            int rootB = Integer.parseInt(st.nextToken());

            // 3. m 개의 연산을 입력 받으며 0 이면 union, 1 이면 각 숫자를 find 하여 나온 부모를 비교한다.
            if (operate == 0) {
                union(rootA, rootB);
            }
            else {
                int parentA = find(rootA);
                int parentB = find(rootB);

                // 3-1. 부모가 같다면 YES, 다르다면 NO 를 출력한다.
                if (parentA == parentB) {
                    sb.append("YES").append("\n");
                } else {
                    sb.append("NO").append("\n");
                }
            }
        }
    }
}