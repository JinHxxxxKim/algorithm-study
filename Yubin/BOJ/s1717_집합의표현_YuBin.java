import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/**
 * 1. 초기에 n+1개의 집합이 {0}, {1}, ... , {n}으로 주어진다.
 * 2. 여기에 합집합 연산과 두 원소가 같은 집합에 포함되어 있는지 확인하는 연산을 여러 번 수행한다.
 */
public class Main {
    final static int UNION_OPERATION = 0;
    final static int CHECK_OPERATION = 1;
    static BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
    static StringBuilder out = new StringBuilder();
    static StringTokenizer st;
    static int NUM_OF_INITIAL_SETS;
    static int NUM_OF_OPERATIONS;
    static int[] parentOf;
    static int[] rankOf;

    static void initSets() {
        // 집합 {0}부터 {n}까지, 자신의 최상위 부모를 의미함
        parentOf = new int[NUM_OF_INITIAL_SETS + 1];
        // 어떤 집합에 포함되기 전까지는, 항상 본인이 해당 집합의 최상위 부모임
        for (int i = 0; i <= NUM_OF_INITIAL_SETS; i++) {
            parentOf[i] = i;
        }

        // 집합 {0}부터 {n}까지, 자신 아래 집합의 크기를 의미함 (subtree)
        rankOf = new int[NUM_OF_INITIAL_SETS + 1];
        // 어떤 집합에 포함되기 전까지는, subtree의 크기는 항상 1
        for (int i = 0; i <= NUM_OF_INITIAL_SETS; i++) {
            rankOf[i] = 1;
        }
    }

    // 본인이 포함되어 있는 집합의 최상위 부모 요소 번호를 찾아옴
    static int getTopParent(int num) {
        // 본인이 곧 부모이면, 최상위 요소임
        int parent = parentOf[num];
        if (parent == num) {
            return num;
        }

        // 만약 그렇지 않다면, 재귀적으로 최상위 부모를 찾아옴
        int topParent = getTopParent(parent);
        // 경로 압축 수행
        parentOf[parent] = topParent;
        // 최상위 부모 반환
        return topParent;
    }

    // 합집합 연산 수행
    static void doUnionOperation(int num1, int num2) {
        int topParent1 = getTopParent(num1);
        int topParent2 = getTopParent(num2);

        // 두 집합 중, 차수가 낮은 쪽이 차수가 높은 쪽으로 들어가야 함
        if (rankOf[topParent1] < rankOf[topParent2]) {
            // 병합 처리 및
            parentOf[topParent1] = topParent2;
            // 차수 1 증가
            rankOf[topParent1] = rankOf[topParent2] + 1;
        } else {
            parentOf[topParent2] = topParent1;
            rankOf[topParent2] = rankOf[topParent1] + 1;
        }
    }

    // 같은 집합에 있는지 확인
    static boolean isInSameSet(int num1, int num2) {
        // 대표자가 같으면 같은 집합에 속해 있음을 의미함
        int topParent1 = getTopParent(num1);
        int topParent2 = getTopParent(num2);
        return topParent1 == topParent2;
    }

    public static void main(String[] args) throws Exception {
        st = new StringTokenizer(in.readLine());
        // 초기 집합의 개수
        NUM_OF_INITIAL_SETS = Integer.parseInt(st.nextToken());
        // 연산 횟수
        NUM_OF_OPERATIONS = Integer.parseInt(st.nextToken());

        // 집합 초기화
        initSets();

        // 연산 수행
        for (int opCnt = 0; opCnt < NUM_OF_OPERATIONS; opCnt++) {
            st = new StringTokenizer(in.readLine());
            // 연산 및 피연산자 파싱
            int operation = st.nextToken().charAt(0) - '0';
            int num1 = Integer.parseInt(st.nextToken());
            int num2 = Integer.parseInt(st.nextToken());
            // 합집합
            if (operation == UNION_OPERATION) {
                doUnionOperation(num1, num2);
            }
            // 또는 같은 집합에 포함되어 있는지 확인
            else {
                boolean result = isInSameSet(num1, num2);
                // 같은 집합에 있으면 YES, 아니면 NO 출력
                if (result) {
                    out.append("YES").append('\n');
                } else {
                    out.append("NO").append('\n');
                }
            }
        }

        // 결과 출력
        System.out.println(out);
    }
}
