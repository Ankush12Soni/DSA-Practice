import java.util.Arrays;

public class maze_free_moves {
    public static void main(String[] args) {
        boolean [][] boards={
                {true,true,true},
                {true,true,true},
                {true,true,true}
        };
//        freeMoves("",boards,0,0);
        int[][]path = new int[boards.length][boards[0].length];
        freeMoveswithPathMatrix("",boards,0,0,path,1);
    }

//    static void freeMoves(String p , boolean [][] maze,int r,int c){
//        if(r==maze.length-1 && c== maze.length-1){
//            System.out.println(p);
//            return;
//        }
//        if(!maze[r][c]){
//            return;
//        }
//
//        //visited blocks
//        maze[r][c]=false;
//
//        if(r< maze.length-1){
//            freeMoves(p+'D',maze,r+1,c);
//        }
//        if(c<maze[0].length-1){
//            freeMoves(p+'R',maze,r,c+1);
//        }
//        if(r>0){
//            freeMoves(p+'U',maze,r-1,c);
//        }
//        if(c>0){
//            freeMoves(p+'L',maze,r,c-1);
//        }
//
//        //when function overs before backtracking removing changes made
//        maze[r][c]=true;
//    }

    static void freeMoveswithPathMatrix(String p , boolean [][] maze,int r,int c,int[][]path,int step){
        if(r==maze.length-1 && c== maze.length-1){
            path[r][c]=step;
            for(int[]arr:path){
            System.out.println(Arrays.toString(arr));
            }

            System.out.println(p);
            System.out.println();
            return;
        }
        if(!maze[r][c]){
            return;
        }

        //visited blocks
        maze[r][c]=false;
        path[r][c]=step;

        if(r< maze.length-1){
            freeMoveswithPathMatrix(p+'D',maze,r+1,c,path,step+1);
        }
        if(c<maze[0].length-1){
            freeMoveswithPathMatrix(p+'R',maze,r,c+1,path,step+1);
        }
        if(r>0){
            freeMoveswithPathMatrix(p+'U',maze,r-1,c,path,step+1);
        }
        if(c>0){
            freeMoveswithPathMatrix(p+'L',maze,r,c-1,path,step+1);
        }

        //when function overs before backtracking removing changes made
        maze[r][c]=true;
        path[r][c]=0;
    }
}
