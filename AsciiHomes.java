import java.util.*;

/*single line input containing space separated specs - length height direction
Direction : U,D,L,R,H(H treated as U))
Approach : Parse specs from input
		   Buiold an Upright "U" -  2D char array house with base on bottom row
           Transform canvas if asked 
           compose canvas side by side alligning base
           print the composed canvas*/

public class AsciiHomes {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        if (!sc.hasNextLine()) return;
        String line = sc.nextLine().trim();
        if (line.isEmpty()) return;

        String[] specs = line.split("\\s+");
        List<char[][]> canvases = new ArrayList<>();
        List<Integer> baseRows = new ArrayList<>();

        for (String s : specs) {
            Spec sp = parseSpec(s);
            char[][] c = buildHouse(sp.length, sp.height, sp.dir);
            canvases.add(c);
            baseRows.add(c.length - 1);
        }

        char[][] composed = compose(canvases, baseRows);
        printTrimmed(composed);
    }

    private static class Spec {
        int length, height;
        char dir;
        Spec(int l, int h, char d) { length = l; height = h; dir = d; }
    }

    private static Spec parseSpec(String s) {
        s = s.trim();
        int x = s.indexOf('x');
        int len = Integer.parseInt(s.substring(0, x));
        char dir = Character.toUpperCase(s.charAt(s.length() - 1));
        int height = Integer.parseInt(s.substring(x + 1, s.length() - 1));
        if (dir == 'H') dir = 'U';
        return new Spec(len, height, dir);
    }

    private static char[][] buildHouse(int width, int height, char dir) {
        char[][] up = buildU(width, height);
        if (dir == 'U') return up;
        if (dir == 'D') return flipVertical(up);
        if (dir == 'L') return rotateLeft(up);
        if (dir == 'R') return rotateRight(up);
        return up;
    }

    private static char[][] buildU(int width, int height) {
        int roofH = height;
        int roofSpan = 2 * roofH;
        int totW = Math.max(roofSpan, width);

        int baseLeft = (totW - width) / 2;
        int baseRight = baseLeft + width - 1;

        int totalRows = roofH + height + 1;
        char[][] a = new char[totalRows][totW];
        for (int r = 0; r < totalRows; r++) Arrays.fill(a[r], ' ');

        int apex = totW / 2;
        for (int r = 0; r < roofH; r++) {
            int left = apex - r;
            int right = apex + r;
            if (left >= 0) a[r][left] = '/';
            if (right < totW) a[r][right] = '\\';
        }

        for (int r = roofH; r < roofH + height; r++) {
            a[r][baseLeft] = '@';
            a[r][baseRight] = '&';
        }

        int baseRow = roofH + height;
        for (int c = baseLeft; c <= baseRight; c++) a[baseRow][c] = '#';

        return a;
    }

    private static char[][] flipVertical(char[][] m) {
        int h = m.length, w = m[0].length;
        char[][] b = new char[h][w];
        for (int i = 0; i < h; i++) b[i] = Arrays.copyOf(m[h - 1 - i], w);
        return b;
    }

    private static char[][] rotateRight(char[][] m) {
        int h = m.length, w = m[0].length;
        char[][] b = new char[w][h];
        for (int i = 0; i < w; i++) Arrays.fill(b[i], ' ');
        for (int r = 0; r < h; r++)
            for (int c = 0; c < w; c++)
                b[c][h - 1 - r] = rotateCharFor90(m[r][c]);
        return b;
    }

    private static char[][] rotateLeft(char[][] m) {
        int h = m.length, w = m[0].length;
        char[][] b = new char[w][h];
        for (int i = 0; i < w; i++) Arrays.fill(b[i], ' ');
        for (int r = 0; r < h; r++)
            for (int c = 0; c < w; c++)
                b[w - 1 - c][r] = rotateCharFor90(m[r][c]);
        return b;
    }

    private static char rotateCharFor90(char ch) {
        if (ch == '/') return '\\';
        if (ch == '\\') return '/';
        return ch;
    }

    private static char[][] compose(List<char[][]> canvases, List<Integer> baseRows) {
        int n = canvases.size();
        int maxAbove = 0, maxBelow = 0;

        for (int i = 0; i < n; i++) {
            char[][] c = canvases.get(i);
            int h = c.length;
            int base = baseRows.get(i);
            int above = base;
            int below = h - 1 - base;
            if (above > maxAbove) maxAbove = above;
            if (below > maxBelow) maxBelow = below;
        }

        int totalRows = maxAbove + 1 + maxBelow;
        int totalWidth = 0;

        for (char[][] c : canvases) totalWidth += c[0].length;

        char[][] out = new char[totalRows][totalWidth];
        for (char[] row : out) Arrays.fill(row, ' ');

        int curCol = 0;
        for (int i = 0; i < n; i++) {
            char[][] c = canvases.get(i);
            int h = c.length, w = c[0].length;
            int base = baseRows.get(i);
            int offset = maxAbove - base;

            for (int r = 0; r < h; r++) {
                int outR = r + offset;
                for (int cc = 0; cc < w; cc++) {
                    char ch = c[r][cc];
                    if (ch != ' ') out[outR][curCol + cc] = ch;
                }
            }
            curCol += w;
        }
        return out;
    }

    private static void printTrimmed(char[][] m) {
        for (char[] row : m) {
            int last = row.length - 1;
            while (last >= 0 && row[last] == ' ') last--;
            if (last < 0) System.out.println();
            else System.out.println(new String(row, 0, last + 1));
        }
    }
}
