package PGS;

/**
 그리디
 한 번 이동할 때, 가장 멀리 떨어진 집부터 배달 & 수거를 하는 것이 효율적
 ex) deliveries = [1, 3, 5, 7], pickups = [2, 4, 6, 8], caps = 8일 때,
 첫 왕복 이후 deliveries = [1, 3, 4, 0], pickups = [2, 4, 6, 0]이 된다.
 * 위 예시의 경우, 실제로 배달을 수행할 때는 3번 집에 먼저 1개를 배달하고 4번 집에 7개를 배달할 것이다.
 그러나 알고리즘을 구현할 때는 일괄적으로 오른쪽부터 줄여나가는 것으로 해도 상관이 없다.

 1. 변수 설정
 - maxDeliveryIdx : deliveries[maxDeliveryIdx] != 0인 가장 큰 값
 - maxPickupIdx : pickups[maxPickupIdx] != 0인 가장 큰 값
 2. 가장 멀리 떨어진 집부터 배달 & 수거하며, maxDeliveryIdx와 maxPickupIdx 업데이트
 - 왕복 거리 = 2 * (Math.max(maxDeliveryIdx, maxPickupIdx) + 1)이다.
 3. 모든 배달과 수거를 마칠 때까지 2를 반복한 후, 정답 return
 */

class s_택배배달및수거하기_조용수 {
    public long solution(int cap, int n, int[] deliveries, int[] pickups) {
        // 1. 변수 설정
        int maxDeliveryIdx = -1;
        int maxPickupIdx = -1;

        for (int idx=n-1; idx>=0; idx--) {
            if (deliveries[idx] != 0) {
                maxDeliveryIdx = idx;
                break;
            }
        }

        for (int idx=n-1; idx>=0; idx--) {
            if (pickups[idx] != 0) {
                maxPickupIdx = idx;
                break;
            }
        }

        // long으로 설정해야 함..!
        long minDistance = 0;

        // 2. 가장 멀리 떨어진 집부터 배달 & 수거하며, maxDeliveryIdx와 maxPickupIdx 업데이트

        // 아직 배달 또는 수거해야 할 집이 있을 경우 반복
        while (maxDeliveryIdx >= 0 || maxPickupIdx >= 0) {
            // System.out.println("===========");
            // System.out.println(maxDeliveryIdx);
            // System.out.println(maxPickupIdx);

            int maxIdx = Math.max(maxDeliveryIdx, maxPickupIdx);

            // 현재 1번째 집이 0번 index에 있으므로, 1을 더해 주어야 함
            minDistance += 2 * (maxIdx + 1);

            // 2-1. maxDeliveryIdx 업데이트
            int boxNum = cap;
            while (boxNum > 0 && maxDeliveryIdx >= 0) {
                int curDeliveryBoxNum = Math.min(boxNum, deliveries[maxDeliveryIdx]);
                boxNum -= curDeliveryBoxNum;
                deliveries[maxDeliveryIdx] -= curDeliveryBoxNum;
                while (maxDeliveryIdx >= 0 && deliveries[maxDeliveryIdx] == 0) {
                    maxDeliveryIdx--;
                }
            }

            // 2-2. maxPickupIdx 업데이트
            boxNum = cap;
            while (boxNum > 0 && maxPickupIdx >= 0) {
                int curPickupBoxNum = Math.min(boxNum, pickups[maxPickupIdx]);
                boxNum -= curPickupBoxNum;
                pickups[maxPickupIdx] -= curPickupBoxNum;
                while (maxPickupIdx >= 0 && pickups[maxPickupIdx] == 0) {
                    maxPickupIdx--;
                }
            }
        }

        // 3. 정답 return
        return minDistance;
    }
}