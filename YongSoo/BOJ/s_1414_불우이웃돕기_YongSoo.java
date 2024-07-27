package BOJ;

/**
 * 최소 스패닝 트리(MST)
 * 크루스칼 알고리즘 사용

 * 1. 주어진 변수 초기화
      - 크루스칼 알고리즘은 간선 리스트를 사용하므로, 해당 방식으로 초기화시킴
 * 2. 간선 리스트를 길이가 짧은 순으로 정렬
 * 3. 간선을 모두 탐색하며, 연결을 시도한다(크루스칼)
 *    3-1. 이미 두 컴퓨터가 연결이 되어 있는 경우 -> 해당 랜선 불필요(기부 가능)
 *    3-2. 아니면 연결 하는 것이 좋음(그리디)
 * 4. connectNum으로 모든 컴퓨터가 연결되었는지를 확인
 *    4-1. 연결되지 않은 경우 : -1 출력
 *    4-2. 연결된 경우 : 남은 랜선을 모두 기부 가능
 */

import java.io.*;
import java.util.*;

public class s_1414_불우이웃돕기_YongSoo {
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));;
    static StringTokenizer st;
    static StringBuilder sb = new StringBuilder();
    static int[] parents;
    static int N;

    static ArrayList<Cable> edgeLists;
    static int answer;
    static class Cable {
        int from;
        int to;
        int len;

        public Cable(int from, int to, int len) {
            this.from = from;
            this.to = to;
            this.len = len;
        }

        @Override
        public String toString() {
            return "Cable{" +
                    "from=" + from +
                    ", to=" + to +
                    ", len=" + len +
                    '}';
        }
    }
    public static void main(String[] args) throws IOException {

        // 1. 주어진 변수 초기화
        setInitVariable();

        // 2. 간선 리스트를 길이가 짧은 순으로 정렬
        edgeLists.sort(Comparator.comparingInt(e -> e.len));

        int connectNum = 1;
        int idx=0;

        // 3. 간선을 모두 탐색하며, 연결을 시도한다
        while (idx < edgeLists.size() && connectNum < N) {
            Cable cable = edgeLists.get(idx);

            int from = cable.from;
            int to = cable.to;
            int len = cable.len;

            // 3-1. 이미 두 컴퓨터가 연결이 되어 있는 경우 -> 해당 랜선 불필요(기부 가능)
            if (find(from) == find(to)) {
                answer += len;
            }

            // 3-2. 아니면 연결 하는 것이 좋음(그리디)
            else {
                union(from, to);
                connectNum++;
            }

            idx++;
        }

        // 모든 컴퓨터를 연결시키지 못한 경우
        if (connectNum < N) {
            answer = -1;
        }

        // 남은 간선은 모두 기부 가능
        else {
            for (int subIdx=idx; subIdx< edgeLists.size(); subIdx++) {
                answer += edgeLists.get(subIdx).len;
            }
        }

        System.out.println(answer);
    }

    private static void union(int from, int to) {
        int parent = find(to);
        parents[parent] = from;
    }

    private static int find(int idx) {
        if (parents[idx] == idx) return idx;

        return parents[idx] = find(parents[idx]);
    }


    static void setInitVariable() throws IOException {
        N = Integer.parseInt(br.readLine().trim());

        edgeLists = new ArrayList<>();

        for (int row=0; row<N; row++) {
            String s = br.readLine().trim();

            for (int col=0; col<N; col++) {
                char c = s.charAt(col);
                if (c == '0') continue;
                else if (c >= 'a') {
                    edgeLists.add(new Cable(row, col, c - 'a' + 1));
                }
                else {
                    edgeLists.add(new Cable(row, col, c - 'A' + 27));
                }
            }
        }

        parents = new int[N+1];

        for (int id=1; id<=N; id++) {
            parents[id] = id;
        }

        answer = 0;

    }

}
