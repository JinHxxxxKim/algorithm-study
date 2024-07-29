import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.StringTokenizer;

/**
 * 1. 인접리스트 방식으로 트리 구현
 * 2. 타겟노드를 삭제
 * 	2-1. 타겟노드 삭제 시, 부모노드가 리프노드로 바뀔 수 있음
 * 3. dfs방식으로 탐색하다가 자식노드가 없다면 카운팅
 * 	3-1. 루트노드의 번호가 0이 아닐 수 있음
 *
 *
 *
 */

public class BOJ1068{
	static BufferedReader br;
	static StringTokenizer st;
	static int n, target, root, ans;
	static int[] parents;
	static ArrayList<Integer>[] tree;
	static boolean isTree=true;

	static void inputTestcase() throws IOException {
		br = new BufferedReader(new InputStreamReader(System.in));
		n = Integer.parseInt(br.readLine().trim());
		tree = new ArrayList[n];
		for(int idx=0; idx<n; idx++){
			tree[idx] = new ArrayList<>();
		}
		parents = new int[n];
		st = new StringTokenizer(br.readLine().trim());
		for(int idx=0; idx<n; idx++){
			parents[idx]=Integer.parseInt(st.nextToken());
			if(parents[idx]==-1){
				root=idx;
				continue;
			}
			tree[parents[idx]].add(idx);
		}
		target = Integer.parseInt(br.readLine().trim());
	}

	static void deleteNode(int node){
		if(node==root){isTree=false; return;}
		for(int idx=0; idx<tree[parents[node]].size(); idx++){
			if(tree[parents[node]].get(idx)==node){
				tree[parents[node]].remove(idx);
				return;
			}
		}
	}

	static void dfs(int nowNode){
		//현재노드가 리프노드라면
		if(tree[nowNode].size()==0){
			ans++;
			return;
		}
		//현재노드가 이너노드라면
		for(int idx=0; idx<tree[nowNode].size(); idx++){
			dfs(tree[nowNode].get(idx));
		}
	}

	public static void main(String[] args) throws IOException {
		//입력
		inputTestcase();
		//타겟노드 삭제
		deleteNode(target);
		//dfs탐색
		if(isTree==true){
			dfs(root);
		}
		//출력
		System.out.println(ans);
	}
}