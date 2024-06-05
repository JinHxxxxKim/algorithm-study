package algorithm_study.BOJ;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

/**
 * [ init() ]
 * 1. 노드의 개수 입력
 * 2. 각 노드의 부모와 부모 노드까지 레벨을 저장하는 배열 생성
 *
 * [ set(현재 노드, 레벨, 부모 노드) ]
 * 3. 각 부모 노드 및 레벨을 저장
 *  3-1. 1번 노드를 루트로 레벨 1부터 시작
 *  3-2. 현재 노드의 자식 노드를 탐색해 다음 레벨로 재귀호출
 *      3-2-1. 부모와 같다면 제외
 *
 * [ findParent() ]
 * 4. 공통 조상 찾기
 *  4-1. 레벨이 다르면 같은 레벨로 변경
 *  4-2. 레벨이 같으면 같은 부모가 나올 때 까지 이동
 *
 * [ main ]
 * 5. 노드의 쌍 개수 입력
 * 6. 쌍을 입력 받으며 공통 조상 출력
 */
public class s11437_LCA_SangHu {
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static StringBuilder sb = new StringBuilder();
    static StringTokenizer st;

    static int nodeCount;
    static int[] parents, levels;
    static List<List<Integer>> tree;

    public static void set(int curNode, int level, int parentNode) {
        parents[curNode] = parentNode;
        levels[curNode] = level;

        // 3-2. 현재 노드의 자식 노드를 탐색해 다음 레벨로 재귀호출
        for(int nextNode : tree.get(curNode)) {
            // 3-2-1. 부모와 같다면 제외
            if (nextNode == parentNode)
                continue;

            set(nextNode, level + 1, curNode);
        }
    }

    public static int findParent(int nodeA, int nodeB) {
        int levelA = levels[nodeA];
        int levelB = levels[nodeB];

        // 4-1. 레벨이 다르면 같은 레벨로 변경
        while (levelA > levelB) {
            nodeA = parents[nodeA];
            levelA--;
        }

        while (levelA < levelB) {
            nodeB = parents[nodeB];
            levelB--;
        }

        // 4-2. 레벨이 같으면 같은 부모가 나올 때 까지 이동
        while(true) {
            if (nodeA == nodeB)
                return nodeA;

            nodeA = parents[nodeA];
            nodeB = parents[nodeB];
        }
    }

    public static void main(String[] args) throws IOException {
        init();
        // 3-1. 1번 노드를 루트로 레벨 1부터 시작
        set(1, 1, 0);

        // 5. 노드의 쌍 개수 입력
        int pairCount = Integer.parseInt(br.readLine().trim());
        for(int idx = 0; idx < pairCount; idx++) {
            st = new StringTokenizer(br.readLine().trim());
            int nodeA = Integer.parseInt(st.nextToken());
            int nodeB = Integer.parseInt(st.nextToken());

            // 6. 쌍을 입력 받으며 공통 조상 출력
            int result = findParent(nodeA, nodeB);
            sb.append(result).append("\n");
        }

        System.out.println(sb);
    }

    public static void init() throws IOException {
        // 1. 노드의 개수 입력
        nodeCount = Integer.parseInt(br.readLine().trim());
        // 2. 각 노드의 부모와 부모 노드까지 레벨을 저장하는 배열 생성
        parents = new int[nodeCount + 1];
        levels = new int[nodeCount + 1];

        // 트리를 구성하는 list 생성
        tree = new ArrayList<>();
        for (int idx = 0; idx < nodeCount+1; idx++) {
            tree.add(new ArrayList<>());
        }

        // 트리 정보 입력
        for (int idx = 0; idx < nodeCount-1; idx++) {
            st = new StringTokenizer(br.readLine().trim());
            int from = Integer.parseInt(st.nextToken());
            int to = Integer.parseInt(st.nextToken());

            tree.get(from).add(to);
            tree.get(to).add(from);
        }
    }
}