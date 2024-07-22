package BOJ;

/**
 * 에라토스테네스의 체 알고리즘을 응용
 *
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
    static HashSet<Integer> visited;

    public static void main(String[] args) throws IOException {

        // 1. 변수 초기화
        setInitVariable();

        ArrayList<Integer> firstVal = new ArrayList<>();
        firstVal.add(0);
        firstVal.add(0);
        firstVal.add(capacities.get(2));

        queue.add(firstVal);
        visited.add(from(firstVal));
        waterList.add(capacities.get(2));

        while (!queue.isEmpty()) {
            ArrayList<Integer> val = queue.poll();
            for (int i = 0; i < val.size(); i++) {
                for (int j = 0; j < val.size(); j++) {
                    if (i != j) {
                        ArrayList<Integer> nextVal = getNextVal(val, i, j);
//                        System.out.println("nextVal = " + nextVal);
                        if (!visited.contains(from(nextVal))) {
                            visited.add(from(nextVal));
                            queue.add(nextVal);
                            if (nextVal.get(0) == 0) waterList.add(nextVal.get(2));
                        }
                    }
                }
            }
        }

        Collections.sort(waterList);
//        System.out.println("waterList = " + waterList);
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
//        System.out.println("val = " + val);
//        System.out.println("i = " + i);
//        System.out.println("j = " + j);
//        System.out.println("diff = " + diff);

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
