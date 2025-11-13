package CodeVita;

import java.util.*;
import java.util.regex.*;

public class BrickWallPathfinder {

    static class Brick {
        char type; // R, G, S, D
        Set<Integer> neighbors = new HashSet<>();
    }

    static int N;
    static int[][] brickOfCell;
    static Brick[] bricks;
    static int startBrick = -1, destBrick = -1;

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        N = Integer.parseInt(sc.nextLine().trim());
        String[] rows = new String[N];
        for (int i = 0; i < N; i++) rows[i] = sc.nextLine().trim();

        parseWall(rows);
        buildGraph();
        System.out.println(findMinBreaks());
    }

    static void parseWall(String[] rows) {
        brickOfCell = new int[N][N];
        List<Brick> brickList = new ArrayList<>();
        int brickId = 0;

        for (int r = 0; r < N; r++) {
            String line = rows[r];
            Matcher m = Pattern.compile("(\\d+)([RGSD])").matcher(line);
            int col = 0;
            while (m.find()) {
                int count = Integer.parseInt(m.group(1));
                char type = m.group(2).charAt(0);

                Brick b = new Brick();
                b.type = type;
                brickList.add(b);

                for (int k = 0; k < count; k++) {
                    if (col >= N) break;
                    brickOfCell[r][col] = brickId;
                    col++;
                }

                if (type == 'S') startBrick = brickId;
                if (type == 'D') destBrick = brickId;
                brickId++;
            }
        }
        bricks = brickList.toArray(new Brick[0]);
    }

    static void buildGraph() {
        int[] dr = {1, -1, 0, 0};
        int[] dc = {0, 0, 1, -1};

        for (int r = 0; r < N; r++) {
            for (int c = 0; c < N; c++) {
                int u = brickOfCell[r][c];
                for (int k = 0; k < 4; k++) {
                    int nr = r + dr[k], nc = c + dc[k];
                    if (nr >= 0 && nr < N && nc >= 0 && nc < N) {
                        int v = brickOfCell[nr][nc];
                        if (v != u) {
                            bricks[u].neighbors.add(v);
                        }
                    }
                }
            }
        }
    }

    static int findMinBreaks() {
        if (startBrick == -1 || destBrick == -1) return -1;
        if (bricks[startBrick].type == 'R' || bricks[destBrick].type == 'R') return -1;

        int INF = Integer.MAX_VALUE;
        int[] dist = new int[bricks.length];
        Arrays.fill(dist, INF);

        Deque<Integer> deque = new ArrayDeque<>();

        dist[startBrick] = 0;
        deque.addFirst(startBrick);

        while (!deque.isEmpty()) {
            int u = deque.pollFirst();

            if (u == destBrick) {
                return dist[u];
            }

            for (int v : bricks[u].neighbors) {
                char neighborType = bricks[v].type;
                if (neighborType == 'R') continue;

                int edgeWeight = (neighborType == 'G') ? 1 : 0;
                int newCost = dist[u] + edgeWeight;

                if (newCost < dist[v]) {
                    dist[v] = newCost;
                    if (edgeWeight == 0) {
                        deque.addFirst(v);
                    } else {
                        deque.addLast(v);
                    }
                }
            }
        }
        return -1;
    }
}