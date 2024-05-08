import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Arrays;
import java.util.StringTokenizer;


/**
 * 정렬 + dp
 *    - boxes 배열을 정렬할 경우 순서가 뒤바뀌게 되므로, 처음에 Box 객체를 생성할 때 id라는 속성을 두어, 원래 순서를 저장하게 한다.
 * 1. 변수 초기화
 * 	  - Box를 담는 배열 boxes를 만든다. Box는 박스 번호(id), 밑면의 넓이(area), 높이(height), 무게(weight)를 가진다.
 *    - 메모리제이션 배열 maxHeights를 만든다. maxHeights[id]는 {id}번째 박스를 제일 아래로 두었을 때, 쌓을 수 있는 상자의 최대 높이를 나타낸다.
 * 2. boxes 배열을 밑면의 넓이가 작은 순으로 정렬한다.
 * 3. boxes를 돌면서 maxHeights를 업데이트한다.
 *    - 정렬된 기준으로 {idx}번째 박스를 본다고 가정하자.
 *      maxHeights[idx]를 구하기 위해서, 이 박스 바로 위에 어떤 박스가 오는 것이 최적인지를 탐색한다.
 *    - 이미 밑면의 넓이가 작은 순으로 정렬되어 있으므로, 더 넓이가 작은 박스들만 (무게도 더 작은지) 보면 된다.
 *      {subIdx}번째 박스의 무게가 더 작다고 가정하자. 이 때, maxHeights[idx] = Max(maxHeights[idx], height + maxHeights[subIdx])로 업데이트한다.
 * 4. 정답 출력
 *    - answer를 기준으로, 역방향 탐색한다.
 *    - 가장 높이 쌓기 위해 필요한 상자들을 스택에 저장해둔 뒤, 순서대로 출력한다.
 */

public class s_2655_가장높은탑쌓기_YongSoo {

    static BufferedReader br;
    static StringTokenizer st;
    static StringBuilder sb;

    static int N;

    static Box[] boxes;

    static int[] maxHeights;
    static int maxHeight;

    static class Box {
        int id;
        int area;
        int height;
        int weight;

        public Box(int id, int area, int height, int weight) {
            this.id = id;
            this.area = area;
            this.height = height;
            this.weight = weight;
        }
    }

    public static void main(String[] args) throws IOException {

        // 1. 변수 초기화
        setInitVariable();

        // 2. 박스를 밑면의 넓이 기준 정렬
        Arrays.sort(boxes, (b1, b2) -> {
            return Integer.compare(b1.area, b2.area);
        });

        // maxHeights[curIdx]를 업데이트
        for (int curIdx=0; curIdx<N; curIdx++) {
            Box box = boxes[curIdx];
            // 상자 하나만 올렸을 때를, 초기의 maxHeights 값으로 잡는다.
            maxHeights[curIdx] = box.height;
            maxHeight = Math.max(maxHeight, maxHeights[curIdx]);

            // {curIdx}번째 박스 바로 위에 올릴 수 있는 박스가 있는지 체크
            // 밑면의 넓이가 더 작아야 하므로, curIdx 이전 박스들만 보면 된다.
            for (int prevIdx=0; prevIdx<curIdx; prevIdx++) {
                Box prevBox = boxes[prevIdx];

                // 무게가 더 작은 경우 : 올릴 수 있음
                if (prevBox.weight < box.weight) {
                    maxHeights[curIdx] = Math.max(maxHeights[curIdx], maxHeights[prevIdx] + box.height);
                    maxHeight = Math.max(maxHeight, maxHeights[curIdx]);
                }
            }
        }

        // 정답을 출력하기 위해 빈 스택 생성
        ArrayDeque<Integer> stack = new ArrayDeque<>();

        int targetVal = maxHeight;

        int idx = N-1;
        while (targetVal > 0) {
            // maxHeights[idx]값이 targetVal이다 -> 이 박스를 포함시켜야 한다.
            if (maxHeights[idx] == targetVal) {
                stack.addLast(boxes[idx].id);

                // 박스를 포함시켰으므로 이 박스의 높이만큼 targetVal이 감소한다.
                targetVal -= boxes[idx].height;
            }

            idx--;
        }

        // 전체 상자의 개수 출력
        sb.append(stack.size()).append("\n");

        // 박스의 크기가 작은 순으로 출력이므로, stack에서 꺼내며 답을 출력하면 된다.
        while (!stack.isEmpty()) {
            sb.append(stack.removeLast()).append("\n");
        }
        System.out.println(sb);
    }



    private static void setInitVariable() throws IOException {
        br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine().trim());
        sb = new StringBuilder();

        boxes = new Box[N];

        for (int idx=0; idx<N; idx++) {
            st = new StringTokenizer(br.readLine().trim());

            int area = Integer.parseInt(st.nextToken());
            int height = Integer.parseInt(st.nextToken());
            int weight = Integer.parseInt(st.nextToken());

            boxes[idx] = new Box(idx+1, area, height, weight);
        }

        maxHeights = new int[N];
        maxHeight = 0;
    }
}
