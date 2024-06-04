/**
 * PGS_택배배달및수거하기
 * @author parkrootseok
 *
 * - 집
 *  - 일렬로 n개 나열
 *  - i번째 위치한 집은 i만큼 떨어져 있음
 *
 * - 배달
 *  - 배달과 동시에 사용한 상자를 수거
 *
 * - 트럭
 *  - 실을 수 있는 갯수는 제한(입력으로 주어짐)
 *
 * 1. 현재 집에 배달, 수거할 택배 개수 구하기
 * 2. 배달 및 수거 실시
 **/
public class s_택배배달과수거하기_GeunSeok {

	public static long solution(int cap, int n, int[] deliveries, int[] pickups) {

		long answer = 0;
		int deliveryCount = 0;
		int pickCount = 0;
		for (int position = n; position >= 1; position--) {

			// 1. 현재 집에 배달 및 수거할 택배의 갯수를 측정
			deliveryCount += deliveries[position - 1];
			pickCount += pickups[position - 1];

			// 2. 배달 및 수거 실시
			while (deliveryCount > 0 || pickCount > 0) {

				// 배달
				deliveryCount -= cap;

				// 수거
				pickCount -= cap;

				// 거리 기록
				answer += (position * 2);

			}

		}

		return answer;

	}

}