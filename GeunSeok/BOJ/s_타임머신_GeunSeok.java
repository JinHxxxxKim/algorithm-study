import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;

/**
 * BOJ_11657_타임머신
 * @author parkrootseok
 *
 * - N개의 도시가 존재
 * - M개의 버스가 존재
 *   - 버스의 정보는 도착지, 출발지, 걸리는 시간
 *   - 걸리는 시간이 양수가 아닌 경우도 존재
 * - 1번 도시에서 나머지 도시로 갈 수 있는 최소 시간을 구해라
 *
 * 1. 도시와 버스 개수 입력
 * 2. 버스에 대한 정보 입력
 * 3. 벨만포드 알고리즘을 통해 각 도시로 가는 최소 시간을 계산
 */
public class s_타임머신_GeunSeok {

	public static class City {

		private int name;
		private long cost;

		public City(int name) {
			this.name = name;
			this.cost = Integer.MAX_VALUE;
		}

	}

	public static class Bus {

		private int origin;
		private int dest;
		private int time;

		public Bus(int origin, int dest, int time) {
			this.origin = origin;
			this.dest = dest;
			this.time = time;
		}

	}

	public static class Graph {

		private City[] cities;
		private Bus[] buses;

		public Graph() {
			cities = new City[cityNumber + 1];
			buses = new Bus[busNumber];
		}

	}

	public static BufferedReader br;
	public static BufferedWriter bw;
	public static StringBuilder sb;
	public static String[] inputs;

	public static int cityNumber;
	public static int busNumber;
	public static Graph graph;
	public static boolean isImpossible;

	public static void main(String[] args) throws IOException {

		br = new BufferedReader(new InputStreamReader(System.in));
		bw = new BufferedWriter(new OutputStreamWriter(System.out));
		sb = new StringBuilder();

		// 1. 도시와 버스 개수 입력
		inputs = br.readLine().trim().split(" ");
		cityNumber = Integer.parseInt(inputs[0]);
		busNumber = Integer.parseInt(inputs[1]);

		// 2. 버스에 대한 정보 입력
		graph = new Graph();
		for (int curCityNumber = 1; curCityNumber <= cityNumber; curCityNumber++) {
			graph.cities[curCityNumber] = new City(curCityNumber);
		}

		for (int curBusNumber = 0; curBusNumber < busNumber; curBusNumber++) {

			inputs = br.readLine().trim().split(" ");

			int origin = Integer.parseInt(inputs[0]);
			int dest = Integer.parseInt(inputs[1]);
			int time = Integer.parseInt(inputs[2]);

			graph.buses[curBusNumber] = new Bus(origin, dest, time);

		}

		// 3. 벨만포드 알고리즘을 통해 각 도시로 가는 최소 시간을 계산
		isImpossible = false;
		bellmanford();

		if (isImpossible) {
			sb.append(-1).append("\n");
		}

		else {
			for (int curCityNumber = 2; curCityNumber <= cityNumber; curCityNumber++) {

				long cost = graph.cities[curCityNumber].cost;

				if (cost != Integer.MAX_VALUE) {
					sb.append(cost).append("\n");
				} else {
					sb.append(-1).append("\n");
				}

			}
		}

		bw.write(sb.toString());
		bw.close();

	}

	public static void bellmanford() {

		// 시작점은 비용을 0으로 초기화
		graph.cities[1].cost = 0;

		// 음수 사이클 발생 여부를 확인하기 위해 {cityNumber+1}번 수행
		for (int curCityNumber = 0; curCityNumber <= cityNumber; curCityNumber++) {

			for (int curBusNumber = 0; curBusNumber < busNumber; curBusNumber++) {

				Bus bus = graph.buses[curBusNumber];
				int origin = bus.origin;
				int dest = bus.dest;
				int time = bus.time;

				if (graph.cities[origin].cost == Integer.MAX_VALUE) {
					continue;
				}

				// 도착지의 현재 비용보다 더 작다면 비용을 갱신
				if (graph.cities[dest].cost > graph.cities[origin].cost + time) {
					graph.cities[dest].cost = graph.cities[origin].cost + time;

					// 만약, 한번 더 수행한 결과에서 갱신되면 음수 사이클 발생
					if (curCityNumber == cityNumber) {
						isImpossible = true;
						return;
					}

				}

			}

		}

	}

}
