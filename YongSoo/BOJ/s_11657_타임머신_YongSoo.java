package BOJ;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

import java.util.*;

/**
 * 벨만-포드 알고리즘 이용
 * 1. 입력받은 변수를 저장
 *    1-1. 버스 노선의 경우에는, 간선리스트 형식으로 저장한다.
 *    1-2. 가장 빠른 시간을 저장하는 minTimes 배열을 만든다.
 *         minTimes[idx] : 1번 도시에서 {idx}번 도시로 가는 가장 빠른 시간
 *         처음에 minTimes[1] = 0, 나머지는 오버플로우가 나지 않도록 적당히 큰 값으로 초기화
 * 2. 총 N-1번 간선리스트를 순회하며, 각 간선에 대해 해당 간선을 거쳐서 더 빨리 도착할 수 있는 경우 minTimes[idx]를 갱신
 * 3. 사이클이 존재하는지 확인하기 위해 한 번 더 간선리스트를 순회한다. 이때 minTimes가 갱신된다면, 사이클이 존재
 *
 */

public class s_11657_타임머신_YongSoo {
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static StringTokenizer st;
    static StringBuilder sb = new StringBuilder();
    static final int INF = 1_000_000_000;
    static int N, M;
    static Edge[] edges;

    // 실수한 부분 1
    // 음의 간선이 존재하므로, minTimes의 lower bound는 int의 범위를 벗어날 수 있다.
    // (최대 - 500 * 60000 * 10000)
    static long[] minTimes;
    static class Edge {
        int from;
        int to;
        int time;

        public Edge(int from, int to, int time) {
            this.from = from;
            this.to = to;
            this.time = time;
        }
    }


    public static void main(String[] args) throws IOException {
        // 1. 입력받은 변수를 저장
        setInitVariable();

        // 2. 총 N-1번 간선리스트를 순회하며, 각 간선에 대해 해당 간선을 거쳐서 더 빨리 도착할 수 있는 경우 minTimes[idx]를 갱신
        for (int cnt=0; cnt<N-1; cnt++) {
            for (Edge edge: edges) {

                // from에서 to로 가는 간선을 이용해, minTimes[to]를 업데이트 가능한 경우
                // 실수한 부분 2
                // 음의 간선이 존재하므로, minTimes[edge.from] == INF인 경우를 제외해야 한다.
                if (minTimes[edge.from] != INF && minTimes[edge.from] + edge.time < minTimes[edge.to]) {
                    minTimes[edge.to] = minTimes[edge.from] + edge.time;
                }
            }
        }

        // 3. 사이클이 존재하는지 확인하기 위해 한 번 더 간선리스트를 순회한다. 이때 minTimes가 갱신된다면, 사이클이 존재
        boolean flag = false;   // 사이클 존재 여부
        for (Edge edge: edges) {
            // from에서 to로 가는 간선을 이용해, minTimes[to]를 업데이트 가능한 경우
            if (minTimes[edge.from] != INF && minTimes[edge.from] + edge.time < minTimes[edge.to]) {
                flag = true;
                break;
            }
        }

//        System.out.println("flag = " + flag);
        if (flag) System.out.println(-1);
        else {
            for (int id=2; id<=N; id++) {
                sb.append(minTimes[id]==INF? -1: minTimes[id]).append("\n");
            }

            System.out.print(sb);
        }


    }

    private static void setInitVariable() throws IOException {
        st = new StringTokenizer(br.readLine().trim());

        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());

        // 1-1. 버스 노선의 경우에는, 간선리스트 형식으로 저장한다.
        edges = new Edge[M];

        for (int idx=0; idx<M; idx++) {
            st = new StringTokenizer(br.readLine().trim());

            int from = Integer.parseInt(st.nextToken());
            int to = Integer.parseInt(st.nextToken());
            int time = Integer.parseInt(st.nextToken());

            edges[idx] = new Edge(from, to, time);
        }

        // 1-2. 가장 빠른 시간을 저장하는 minTimes 배열을 만든다.
        minTimes = new long[N+1];
        Arrays.fill(minTimes, INF);
        minTimes[1] = 0;


    }
}