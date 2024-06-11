package BOJ;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.StringTokenizer;

/**
 * DFS 탐색
 *
 * 1. 변수 초기화
 *    1-1. ArrayList<Integer>[] neighborNodes
 *         - neighborNodes[id]: id번 노드와 인접한 노드들의 리스트
 *    1-2. int[] parents
 *         - parents[id] : id번 노드의 부모 노드
 *
 * 2. 트리의 루트부터 시작하여 DFS 탐색
 *    2-1. 자식 노드와 부모 노드의 id를 인자로 받아, parents 배열 업데이트
 *    2-2. neighborNodes를 이용하여 아래로 더 탐색할 수 있는지 판단
 *         id가 부모 노드의 id가 아니라면, 자식 노드이다.(트리의 부모는 하나)
 *
 * 3. parents 배열을 이용해 정답 출력
 */

public class s_11725_트리의부모찾기_YongSoo {
    static BufferedReader br;
    static StringTokenizer st;
    static StringBuilder sb;

    static int N;
    static ArrayList<Integer>[] neighborNodes;
    static int[] parents;

    public static void main(String[] args) throws IOException {
        // 1. 변수 초기화
        setInitVariable();

        // 2. DFS 탐색하며 parents 배열 업데이트
        setParent(1, -1);

        // 3. parents 배열을 이용하여 정답 출력
        for (int id=2; id<=N; id++) {
            sb.append(parents[id]).append("\n");
        }

        System.out.println(sb);
    }

    // DFS 탐색용 재귀함수
    private static void setParent(int nodeId, int parentId) {
        parents[nodeId] = parentId;
        // 인접한 노드 중, 부모 노드가 아닐 경우 모두 자식 노드이다.
        for (int neighborNodeId: neighborNodes[nodeId]) {
            if (neighborNodeId != parentId) {
                // 자식 노드들에 대해 재귀 탐색
                setParent(neighborNodeId, nodeId);
            }
        }
    }

    private static void setInitVariable() throws IOException{
        br = new BufferedReader(new InputStreamReader(System.in));
        sb = new StringBuilder();
        N = Integer.parseInt(br.readLine().trim());

        neighborNodes = new ArrayList[N+1];
        for (int id=1; id<=N; id++) {
            neighborNodes[id] = new ArrayList<>();
        }

        // id1과 id2가 주어졌을 때,
        // neighborNodes[id1]과 neighborNodes[id2]를 각각 업데이트
        for (int cnt=0; cnt<N-1; cnt++) {
            st = new StringTokenizer(br.readLine().trim());
            int id1 = Integer.parseInt(st.nextToken());
            int id2 = Integer.parseInt(st.nextToken());

            neighborNodes[id1].add(id2);
            neighborNodes[id2].add(id1);
        }

        parents = new int[N+1];
    }
}
