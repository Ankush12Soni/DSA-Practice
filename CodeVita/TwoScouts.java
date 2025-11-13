import java.util.*;

public class TwoScouts {

    static List<List<Integer>> adj;
    static int N;
    static int minTotalTowns = Integer.MAX_VALUE;

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        N = sc.nextInt();
        int M = sc.nextInt();

        adj = new ArrayList<>();
        for (int i = 0; i < N; i++) {
            adj.add(new ArrayList<>());
        }

        for (int i = 0; i < M; i++) {
            int u = sc.nextInt() - 1;
            int v = sc.nextInt() - 1;
            adj.get(u).add(v);
            adj.get(v).add(u);
        }

        int start1 = sc.nextInt() - 1;
        int start2 = sc.nextInt() - 1;
        int outpost = sc.nextInt() - 1;

        findPathsForScout1(start1, start2, outpost, new ArrayList<>(), new boolean[N]);

        if (minTotalTowns == Integer.MAX_VALUE) {
            System.out.println("Impossible");
        } else {
            System.out.println(minTotalTowns);
        }
    }

    private static void findPathsForScout1(int u, int start2, int outpost, List<Integer> currentPath, boolean[] visited) {
        visited[u] = true;
        currentPath.add(u);

        if (u == outpost) {
            findShortestPathForScout2(currentPath, start2, outpost);
        } else {
            for (int v : adj.get(u)) {
                if (!visited[v]) {
                    findPathsForScout1(v, start2, outpost, currentPath, visited);
                }
            }
        }

        currentPath.remove(currentPath.size() - 1);
        visited[u] = false;
    }

    private static void findShortestPathForScout2(List<Integer> path1, int start2, int outpost) {
        Set<Integer> blocked = new HashSet<>();
        for (int i = 0; i < path1.size() - 1; i++) {
            blocked.add(path1.get(i));
        }

        if (blocked.contains(start2)) {
            return;
        }

        Queue<List<Integer>> queue = new LinkedList<>();
        queue.add(Collections.singletonList(start2));

        Set<Integer> visitedInBfs = new HashSet<>();
        visitedInBfs.add(start2);

        while (!queue.isEmpty()) {
            List<Integer> currentPath = queue.poll();
            int lastNode = currentPath.get(currentPath.size() - 1);

            if (lastNode == outpost) {
                int totalTowns = path1.size() + currentPath.size() - 1;
                minTotalTowns = Math.min(minTotalTowns, totalTowns);
                return;
            }

            for (int neighbor : adj.get(lastNode)) {
                if (!blocked.contains(neighbor) && !visitedInBfs.contains(neighbor)) {
                    visitedInBfs.add(neighbor);
                    List<Integer> newPath = new ArrayList<>(currentPath);
                    newPath.add(neighbor);
                    queue.add(newPath);
                }
            }
        }
    }
}