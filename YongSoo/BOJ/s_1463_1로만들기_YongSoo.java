package BOJ;


import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;

/**
 * BFS 연습문제
 */

public class s_1463_1로만들기_YongSoo {
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static int N;
    static boolean[] visited;
    static ArrayDeque<Node> queue;
    static int answer;
    static class Node {
        int val;
        int cnt;

        public Node(int val, int cnt) {
            this.val = val;
            this.cnt = cnt;
        }
    }

    public static void main(String[] args) throws IOException {

        // 1. 변수 초기화
        setInitVariable();

        // 2. BFS 탐색을 하며 정답을 업데이트
        solve();

        // 3. 정답 출력
        System.out.println(answer);
    }

    // BFS 탐색을 하며 정답을 업데이트
    private static void solve() {
        // N == 1이라면 탐색 불필요
        if (N != 1) {

            // queue에 초기값 삽입
            visited[N] = true;
            queue.add(new Node(N, 0));

            // BFS 탐색
            while (!queue.isEmpty()) {
                Node node = queue.poll();
                int val = node.val;
                int cnt = node.cnt;

                // 연산이 가능한 세 가지 경우에 대해 가능한지를 체크하고, 가능하다면 queue에 새로운 원소를 넣는다
                // 만일 연산을 하던 도중 1이 나온다면 탐색 종료
                if(
                    checkAndAddNode(val%3==0, val/3, cnt+1) ||
                    checkAndAddNode(val%2 == 0, val/2, cnt+1) ||
                    checkAndAddNode(true, val-1, cnt+1)
                ) {
                    return;
                }
            }
        }
    }

    // condition이 만족될 경우에 
    private static boolean checkAndAddNode(boolean condition, int newVal, int newCnt) {
        if (condition && !visited[newVal]) {
            if (newVal == 1) {
                answer = newCnt;
                return true;
            }

            visited[newVal] = true;
            queue.add(new Node(newVal, newCnt));
        }

        return false;
    }


    private static void setInitVariable() throws IOException {
        N = Integer.parseInt(br.readLine().trim());

        visited = new boolean[N+1];
        queue = new ArrayDeque<>();

        answer = 0;
    }


}
