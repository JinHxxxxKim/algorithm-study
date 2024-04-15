import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/**
 * Union-Find 알고리즘 사용
 */

public class Main {

    static BufferedReader br;
    static StringTokenizer st;
    static StringBuilder sb;

    static int n, m;

    // union-find 연산을 위한 부모 노드 저장 배열
    // parents[i] = i라면, i가 최상위 노드임을 의미한다.
    static int[] parents;


    public static void main(String[] args) throws IOException {
        // 변수 초기화
        setInitVariable();

        for (int cnt=0; cnt<m; cnt++) {
            st = new StringTokenizer(br.readLine().trim());

            int operationIdx = Integer.parseInt(st.nextToken());
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());

            // 0으로 시작하는 입력의 경우 : union 연산 수행
            if (operationIdx == 0) {
                union(a, b);
            }
            // 1로 시작하는 입력의 경우 : find 연산 수행
            else {
                boolean flag = find(a, b);
                sb.append(flag? "YES": "NO").append("\n");
            }
        }
        System.out.println(sb);
    }

    // a와 b가 같은 집합에 포함되는지 여부를 반환
    // a와 b의 최상위 노드를 각각 구해, 서로 같은지를 비교하면 된다
    private static boolean find(int a, int b) {
        int parentA = findParent(a);
        int parentB = findParent(b);

        return parentA == parentB;
    }

    // a가 포함되어 있는 집합과, b가 포함되어 있는 집합을 합침
    // a와 b의 최상위 노드를 각각 구한 후, parentB의 부모 노드를 parentA로 업데이트한다
    // rank를 비교하여 어느 쪽으로 합칠지 결정할 수도 있으나 사용하지 않음
    private static void union(int a, int b) {
        int parentA = findParent(a);
        int parentB = findParent(b);

        parents[parentB] = parentA;
    }

    // a의 최상위 노드를 반환하는 메서드
    // 재귀 호출을 이용
    private static int findParent(int a) {
        if (parents[a] == a) {
            return a;
        }

        // 깊이가 너무 깊어지는 것을 방지하기 위해, findParent가 실행될 때마다 parents 배열 업데이트
        return parents[a] = findParent(parents[a]);
    }

    // 문제에서 주어지는 변수 초기화
    private static void setInitVariable() throws IOException {
        br = new BufferedReader(new InputStreamReader(System.in));
        sb= new StringBuilder();

        st = new StringTokenizer(br.readLine().trim());
        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());

        parents = new int[n+1];
        // 처음에는, 각 노드의 부모는 자기 자신이 됨
        for (int idx=0; idx<=n; idx++) {
            parents[idx] = idx;
        }
    }
}