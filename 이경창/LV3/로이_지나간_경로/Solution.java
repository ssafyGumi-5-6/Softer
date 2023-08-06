package Softeer.LV3.로이_지나간_경로;

import java.util.*;
import java.io.*;


public class Solution
{

    private static int H, W;
    private static char[][] gridPlate;
    private static int startX, startY;
    private static int[][] dxy = {{0, -1}, {1, 0}, {0, 1}, {-1, 0}};


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

    private static int isStartPos(int y, int x, char[][] gridPlate){
        int count = 0;
        for(int i = 0; i < 4; i++){
            int nx = dxy[i][0] + x;
            int ny = dxy[i][1] + y;

            if(withinRange(ny, nx) && gridPlate[ny][nx] == '#') count++;
        }

        return count;
    }

    private static boolean isStraightDir(int y, int x, char[][] gridPlate, int dir){

        int nx = x + dxy[dir][0] * 2;
        int ny = y + dxy[dir][1] * 2;

        return 0 <= nx && 0 <= ny && nx < W  && ny < H && gridPlate[y + dxy[dir][1]][x + dxy[dir][0]] == '#' && gridPlate[y + dxy[dir][1] * 2][x + dxy[dir][0] * 2] == '#';
    }

    private static void moveStraight(int x, int y, char[][] gridPlate, int dir){
        for(int i = 0; i < 3; i++){
            gridPlate[y][x] = '.';
            x = x + dxy[dir][0];
            y = y + dxy[dir][1];
        }
    }

    private static void dfs(int x, int y, char[][] gridPlate, int dir, StringBuilder methodBuilder){
        int currentX = x + dxy[dir][0] * 2;
        int currentY = y + dxy[dir][1] * 2;
        moveStraight(x, y, gridPlate, dir);
        methodBuilder.append("A");

        // L
        int leftD = (dir + 3) % 4;
        if(isStraightDir(currentY, currentX, gridPlate, leftD)){
            methodBuilder.append("L");
            dfs(currentX, currentY, gridPlate, leftD, methodBuilder);
            return;
        }

        // R
        int rightD = (dir + 1) % 4;
        if(isStraightDir(currentY, currentX, gridPlate, rightD)){
            methodBuilder.append("R");
            dfs(currentX, currentY, gridPlate, rightD, methodBuilder);
            return;
        }

        // 직진
        if(isStraightDir(currentY, currentX, gridPlate, dir)){
            dfs(currentX, currentY, gridPlate, dir, methodBuilder);
            return;
        }
    }

    public static void main(String args[]) throws IOException
    {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer tokenizer = new StringTokenizer(reader.readLine());
        StringBuilder builder = new StringBuilder();
        H = Integer.parseInt(tokenizer.nextToken());
        W = Integer.parseInt(tokenizer.nextToken());

        gridPlate = new char[H][];
        for(int i = 0 ;i < H; i++){
            gridPlate[i] = reader.readLine().toCharArray();
        }

        // (1) 시작 지점 찾기
        Loop1:
        for(int i = 0; i < H; i++){
            for(int j = 0; j < W; j++){
                // 만약 현재 위치 값이 #이고, 상하좌우 탐지했을 때 #의 개수가 1개일 때
//                if(i == 0 && j == 7) System.out.println("grid : " + gridPlate[i][j] + " isStartPos : " + isStartPos(i, j, gridPlate));
                if(gridPlate[i][j] == '#' && isStartPos(i, j, gridPlate) == 1){
                    for(int d = 0; d < 4; d++){
                        if(!isStraightDir(i, j, gridPlate, d)) continue;

                        StringBuilder methodBuilder = new StringBuilder();
                        dfs(j, i, gridPlate, d, methodBuilder);
                        builder.append((i + 1)).append(" ").append((j + 1)).append("\n");
                        builder.append(indexChangeDirection(d)).append("\n");
                        builder.append(methodBuilder);
                        break Loop1;
                    }
                }
            }
        }

        System.out.println(builder);

        reader.close();
    }
}
