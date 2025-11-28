import java.util.*;

public class Rubiverse {

    // --- Vector Class for 3D Operations ---
    static class Vec {
        int x, y, z;
        Vec(int x, int y, int z){ this.x=x; this.y=y; this.z=z; }
        
        // Vector addition
        Vec add(Vec o){ return new Vec(x+o.x, y+o.y, z+o.z); }
        // Scalar multiplication
        Vec mul(int s){ return new Vec(x*s, y*s, z*s); }
        // Dot product
        int dot(Vec o){ return x*o.x + y*o.y + z*o.z; }
        // Cross product (right-hand rule)
        Vec cross(Vec o){
            return new Vec(
                y*o.z - z*o.y,
                z*o.x - x*o.z,
                x*o.y - y*o.x
            );
        }
        // Unique string key for coordinate
        String key(){ return x + "," + y + "," + z; }
    }

    // Rotates vector v 90 degrees around axis. sign = +1 (CCW) or -1 (CW)
    static Vec rotate90(Vec v, Vec axis, int sign){
        // Rodrigues' rotation formula for 90 degrees:
        // v' = (v * cos(90)) + (axis x v * sin(90)) + (axis * (axis . v) * (1 - cos(90)))
        // Since cos(90)=0 and sin(90)=1:
        // v' = (axis x v * sign) + (axis * (axis . v))
        int d = axis.dot(v);
        Vec p1 = axis.mul(d);  // Component parallel to axis (axis . v) * axis
        Vec c = axis.cross(v); // Cross product (axis x v)
        Vec p2 = c.mul(sign);  // Component rotated 90 degrees
        return new Vec(p1.x + p2.x, p1.y + p2.y, p1.z + p2.z);
    }

    // --- Face Orientation Class ---
    class Ori { 
        Vec n, u, v; 
        // n: normal vector (outward)
        // u: 'right' direction vector on face
        // v: 'down' direction vector on face
    }

    public static void main(String[] args){
        Scanner sc = new Scanner(System.in);

        // Read the 4x4 net robustly (strip spaces, handle short lines)
        char[][] grid = new char[4][4];
        for (int i = 0; i < 4; i++) {
            String line = sc.nextLine();
            line = line.replace(" ", "").trim();
            // Ensure exactly 4 chars, padding with '.' if needed
            while (line.length() < 4) line += ".";
            line = line.substring(0, 4);
            for (int j = 0; j < 4; j++) grid[i][j] = line.charAt(j);
        }

        // Read the 24 sticker characters
        String facesChars = sc.nextLine().trim();
        // Read the query (three face labels)
        String query = sc.nextLine().trim();

        // 1. Collect face labels in scan order
        List<Character> faces = new ArrayList<Character>();
        Set<Character> seen = new LinkedHashSet<Character>();
        for (int r = 0; r < 4; r++) {
            for (int c = 0; c < 4; c++) {
                char ch = grid[r][c];
                if (ch >= 'A' && ch <= 'Z') {
                    seen.add(ch);
                }
            }
        }
        faces = new ArrayList<Character>(seen);


        // 2. Map face -> its single grid coordinate
        Map<Character, int[]> faceCoord = new HashMap<Character, int[]>();
        char[][] faceAt = new char[4][4]; // For quick lookup
        for (int r = 0; r < 4; r++) {
            for (int c = 0; c < 4; c++) {
                char ch = grid[r][c];
                if (ch >= 'A' && ch <= 'Z') {
                    faceCoord.put(ch, new int[]{r, c});
                    faceAt[r][c] = ch;
                }
            }
        }

        // 3. Assign sticker characters to faces in the discovered order
        Map<Character, char[]> stickers = new HashMap<Character, char[]>();
        int p = 0;
        for (char f : faces) {
            if (p + 4 > facesChars.length()) { // Safety check
                 // Handle case where sticker string is too short
                 return;
            }
            char[] arr = new char[4];
            for (int k = 0; k < 4; k++) {
                arr[k] = facesChars.charAt(p + k);
            }
            p += 4;
            stickers.put(f, arr);
        }

        // Check for 6 faces
        if (faces.size() != 6) {
             // Cannot form a cube, though the problem implies valid input
             System.out.println("");
             return;
        }
        
        // --- 4. BFS to Determine 3D Orientation (n, u, v) of all faces ---
        
        class Ori { Vec n, u, v; }
        Map<Character, Ori> orient = new HashMap<Character, Ori>();

        char start = faces.get(0);
        Ori o0 = new Ori();
        // Initial face is flat on the XY plane, looking 'up' the Z axis.
        o0.n = new Vec(0, 0, 1);
        o0.u = new Vec(1, 0, 0); // +X is 'right'
        o0.v = new Vec(0, 1, 0); // +Y is 'down' (matching grid coordinates)
        orient.put(start, o0);

        Queue<Character> q = new LinkedList<Character>();
        q.add(start);

        // neighbor steps: right, left, down, up (dx,dy)
        int[][] steps = {{1,0},{-1,0},{0,1},{0,-1}};

        while (!q.isEmpty()) {
            char cur = q.poll();
            Ori co = orient.get(cur);
            int[] rc = faceCoord.get(cur);
            if (rc == null) continue;
            int cr = rc[0], cc = rc[1];

            for (int i = 0; i < 4; i++) {
                int dx = steps[i][0], dy = steps[i][1];
                int nr = cr + dy;
                int nc = cc + dx;
                
                // Check bounds and if neighbor is another face
                if (nr < 0 || nr >= 4 || nc < 0 || nc >= 4) continue;
                char nf = faceAt[nr][nc];
                if (nf < 'A' || nf > 'Z') continue;
                if (orient.containsKey(nf)) continue; // Already processed

                // Determine rotation axis and sign
                Vec axis;
                int sign;
                
                // Rotation around:
                // Horizontal step (dx != 0): Axis is 'down' vector (co.v)
                if (dx != 0) {
                    axis = co.v;
                    sign = dx;    
                } 
                // Vertical step (dy != 0): Axis is 'right' vector (co.u)
                else {
                    axis = co.u;
                    sign = -dy;   
                }
                
                // Calculate new orientation vectors
                Ori no = new Ori();
                no.n = rotate90(co.n, axis, sign);
                no.u = rotate90(co.u, axis, sign);
                no.v = rotate90(co.v, axis, sign);
                orient.put(nf, no);
                q.add(nf);
            }
        }

        // --- 5. Map 3D Vertex Coordinates to Sticker Characters ---
        
        Map<Character, Map<String, Character>> vmap = new HashMap<Character, Map<String, Character>>();
        for (char f : faces) {
            Ori o = orient.get(f);
            if (o == null) {
                System.out.println("");
                return;
            }
            char[] sarr = stickers.get(f);
            Map<String, Character> m = new HashMap<String, Character>();
            int idx = 0;
            
            // Sticker array (sarr) order: 
            // 0: Top-Left  (v=-1, u=-1)
            // 1: Top-Right (v=-1, u=+1)
            // 2: Bottom-Left (v=+1, u=-1)
            // 3: Bottom-Right (v=+1, u=+1)
            
            // Loop over the 4 vertices of the face
            // Vertices are calculated as: n + u*hu + v*vr
            for (int vr = -1; vr <= 1; vr += 2) {      // vr: -1 (Top), +1 (Bottom)
                for (int hu = -1; hu <= 1; hu += 2) { // hu: -1 (Left), +1 (Right)
                    Vec pvec = o.n.add(o.u.mul(hu)).add(o.v.mul(vr));
                    m.put(pvec.key(), sarr[idx]);
                    idx++;
                }
            }
            vmap.put(f, m);
        }

        // --- 6. Find Common Vertex and Print Result ---
        
        if (query.length() != 3) {
             System.out.println("");
             return;
        }

        char f1 = query.charAt(0), f2 = query.charAt(1), f3 = query.charAt(2);
        Map<String, Character> m1 = vmap.get(f1);
        Map<String, Character> m2 = vmap.get(f2);
        Map<String, Character> m3 = vmap.get(f3);

        if (m1 == null || m2 == null || m3 == null) {
            System.out.println("");
            return;
        }

        String foundKey = null;
        for (String k : m1.keySet()) {
            // The three faces meet at a single shared vertex key
            if (m2.containsKey(k) && m3.containsKey(k)) {
                foundKey = k;
                break;
            }
        }

        if (foundKey == null) {
            System.out.println("");
            return;
        }

        // Output the stickers in the order of the query faces
        System.out.println("" + m1.get(foundKey) + m2.get(foundKey) + m3.get(foundKey));
    }
}