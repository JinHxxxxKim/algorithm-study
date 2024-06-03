import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;

/**
 * queue를 이용
 * 주어진 연산을 거꾸로 적용시키면, 카드의 초기 순서를 얻을 수 있다.
 */

public class s_1835_카드_YongSoo {
    static BufferedReader br;
    static StringBuilder sb;

    static int N;

    // 카드의 순서를 저장하는 변수
    static ArrayDeque<Integer> queue;

    public static void main(String[] args) throws IOException {
        // 1. 변수 초기화
        setInitVariable();

        // 2. 주어진 연산을 거꾸로 적용
        // {id}가 적힌 카드를 옮김
        for (int id=N; id>0; id--) {

            // 책상위에 올려놓기 전, 가장 앞에는 {id}번 카드가 있어야 한다
            queue.addFirst(id);

            // 가장 뒤에 있는 카드를 가장 앞으로 옮긴다({id}번 반복)
            for (int cnt=0; cnt<id; cnt++) {
                Integer lastId = queue.removeLast();
                queue.addFirst(lastId);
            }
        }

        // 3. 정답 출력
        for (int id: queue) {
            sb.append(id).append(" ");
        }

        System.out.println(sb);

    }

    // 문제에서 주어진 변수를 초기화
    private static void setInitVariable() throws IOException{
        br = new BufferedReader(new InputStreamReader(System.in));
        sb = new StringBuilder();
        N = Integer.parseInt(br.readLine().trim());

        queue = new ArrayDeque<>();
    }
}
