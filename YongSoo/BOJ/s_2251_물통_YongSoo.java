package BOJ;

/**
 * BFS
 * 일반적인 BFS나, 여기서의 원소는 ({A의 물의 양}, {B의 물의 양}, {C의 물의 양})임
 * 방문처리를 위해 3차원 배열로 관리하는 것은 메모리 낭비가 심함 -> HashSet 사용을 고려

 * ArrayList<Integer>의 경우, equals 메서드가 잘 overriding 되어 있다.
 * 처음에는 visited 를 만들 때 ArrayList<Integer>를 int 형으로 바꾸는 로직을 생각했으나, 그럴 필요 없음!
 * (배열로 관리할 경우, equals 메서드는 Object class의 equals를 따라감. 즉, 추가적인 로직 필요)

 * 1. 변수 초기화
 * 2. BFS 탐색을 수행한다.
 *    2-1. 초기값 삽입
 *    2-2. BFS 탐색 수행. 탐색을 하며, A가 비어 있는 경우, 가능한 C의 물의 양을 모두 리스트(waterList)에 저장
 * 3. 정답 출력
 *    3-1. waterList 정렬
 *    3-2. waterList를 탐색하며 중복되지 않게 정답 출력
 */

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class s_2251_물통_YongSoo {
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static StringTokenizer st;
    static StringBuilder sb = new StringBuilder();
    static ArrayList<Integer> capacities;
    static ArrayDeque<ArrayList<Integer>> queue;
    static ArrayList<Integer> waterList;
    static HashSet<ArrayList<Integer>> visited;

    public static void main(String[] args) throws IOException {

        // 1. 변수 초기화
        setInitVariable();

        // 2. BFS 탐색 수행

        // 2-1. 초기값 삽입
        ArrayList<Integer> firstVal = new ArrayList<>();
        firstVal.add(0);
        firstVal.add(0);
        firstVal.add(capacities.get(2));

        queue.add(firstVal);
        visited.add(firstVal);
        waterList.add(capacities.get(2));

        // 2-2. 탐색 수행
        while (!queue.isEmpty()) {
            ArrayList<Integer> val = queue.poll();
            for (int i = 0; i < val.size(); i++) {
                for (int j = 0; j < val.size(); j++) {
                    if (i != j) {

                        // {i}번째 물통에서, {j}번째 물통으로 물을 모두 부은 후의 물의 양
                        ArrayList<Integer> nextVal = getNextVal(val, i, j);

                        // 새로운 물의 양일 경우 - queue에 추가
                        if (!visited.contains(nextVal)) {
                            visited.add(nextVal);
                            queue.add(nextVal);
                            if (nextVal.get(0) == 0) waterList.add(nextVal.get(2));
                        }
                    }
                }
            }
        }

        // 3. waterList를 정렬한 후, 중복되지 않게 정답을 출력한다
        Collections.sort(waterList);
        int prevVal = -1;
        for (int water: waterList) {
            if (prevVal != water) {
                sb.append(water).append(" ");
                prevVal = water;
            }
        }

        System.out.println(sb);
    }

    private static ArrayList<Integer> getNextVal(ArrayList<Integer> val, int i, int j) {
        ArrayList<Integer> nextVal = new ArrayList<>(val);
        int diff = Math.min(val.get(i), capacities.get(j) - val.get(j));

        nextVal.set(i, val.get(i) - diff);
        nextVal.set(j, val.get(j) + diff);

        return nextVal;

    }

    private static int from(ArrayList<Integer> waters) {
        int key = 0;
        for (int i = 0; i < waters.size(); i++) {
            key *= 201;
            key += waters.get(i);
        }

        return key;
    }

    private static void setInitVariable() throws IOException {
        st = new StringTokenizer(br.readLine().trim());
        capacities = new ArrayList<>();
        for (int cnt=0; cnt<3; cnt++) {
            capacities.add(Integer.parseInt(st.nextToken()));
        }

        queue = new ArrayDeque<>();
        waterList = new ArrayList<>();
        visited = new HashSet<>();
    }


}
