package Softeer.LV3.로봇이_지나간_경로;

import java.util.*;
import java.io.*;


public class Solution3
{

    private static int H, W;
    private static char[][] gridPlate;
    private static int startX, startY;
    private static int[][] dxy = {{0, -1}, {1, 0}, {0, 1}, {-1, 0}};
    private static int startDirectionInt;
    private static String startDirection;
    private static String command;

    private static boolean withinRange(int row, int col) {
        if(0 <= row && row < H && 0 <= col && col < W) return true;
        return false;
    }

    private static String indexChangeDirection(int d){
        // -1, 0
        if (d == 0)
            return "^";
        else if (d == 1)
            return ">";
        else if (d == 2)
            return "v";
        else
            return "<";
    }

    private static void findStartXYDFS(int curX, int curY, boolean[][] visited, char[][] gridPlate, int direction){
        // System.out.println("row : " + curY + " col : " + curX);
        int count = 0;
        for(int i = 0; i < 4; i++){
            int nx = dxy[i][0] + curX;
            int ny = dxy[i][1] + curY;

            if(!withinRange(ny, nx)) continue;
            if(visited[ny][nx] || gridPlate[ny][nx] != '#') continue;

            visited[ny][nx] = true;
            findStartXYDFS(nx, ny, visited, gridPlate, i);
            count++;

        }

        // 더 이상 갈 곳이 없다면
        if(count == 0){
            startX = curX;
            startY = curY;
            // System.out.println("direction : " + direction);
            startDirectionInt = (direction + 2) % 4;
            startDirection = indexChangeDirection((direction + 2) % 4);
        }
    }

    private static void findAnswerDFS(int curX, int curY, int curDirection, char[][] gridPlate, String result, boolean[][] visited){

        int count = 0;
        if(curX == 1 && curY == 7){
            for(int i = 0; i < H; i++) System.out.println(Arrays.toString(visited[i]));
        }
        for(int i = 0; i < 4; i++){
            int nx = dxy[i][0] + curX;
            int ny = dxy[i][1] + curY;

            // System.out.println("nx : " + nx + " ny : " + ny);
            if(!withinRange(ny, nx)) continue;
            if(visited[ny][nx] || gridPlate[ny][nx] != '#') continue;

            String curSumResult = "";
            // L, R, A
            if(i == (curDirection + 1) % 4){
                curSumResult = result + "R";
                nx = curX; ny = curY;
            }
            else if(i == (curDirection + 3) % 4){
                curSumResult = result + "L";
                nx = curX; ny = curY;
            }
            else if(i == curDirection) curSumResult = result + "A";

            // 만약 현재 x, y와 방향이 다르다면 i 와 curDirection이 다르다면 L OR R
            visited[ny][nx] = true;
            // 같은 방향이면 A
            if(curDirection == i){
                nx += dxy[i][0];
                ny += dxy[i][1];
                visited[ny][nx] = true;
            }
            // System.out.println("after nx, ny" + nx + " " + ny);
            findAnswerDFS(nx, ny, i, gridPlate, curSumResult, visited);
            count++;
        }

        if(count == 0){
            command = result;
        }
    }

    public static void main(String args[]) throws IOException
    {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer tokenizer = new StringTokenizer(reader.readLine());
        StringBuilder builder = new StringBuilder();
        H = Integer.parseInt(tokenizer.nextToken());
        W = Integer.parseInt(tokenizer.nextToken());

        boolean[][] visited = new boolean[H][W];

        gridPlate = new char[H][];
        for(int i = 0 ;i < H; i++){
            Arrays.fill(visited[i], false);
            gridPlate[i] = reader.readLine().toCharArray();
        }

        // (1) 시작 지점 찾기
        Loop1:
        for(int row = 0; row < H; row++){
            for(int col = 0; col < W; col++){
                if(gridPlate[row][col] == '#'){
                    findStartXYDFS(col, row, visited, gridPlate, -1);
                    break Loop1;
                }
            }
        }

        for(int i = 0 ;i < H; i++){
            Arrays.fill(visited[i], false);
        }

        // System.out.println("StartX : " + startX + " startY : " + startY + " direction : " + startDirectionInt);
        findAnswerDFS(startX, startY, startDirectionInt, gridPlate, new String(), visited);

        builder.append((startY + 1)).append(" ").append((startX + 1)).append("\n");
        builder.append(startDirection).append("\n");
        builder.append(command);

        System.out.println(builder);


        reader.close();
    }
}
