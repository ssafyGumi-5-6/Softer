package Softeer.LV3.스마트_물류;

import java.util.*;
import java.io.*;


public class Solution
{
    private static int N, K;
    private static int answer;
    private static char[] distribution;
    private static boolean[] visited;

    private static int leftFoundH(int index){
        int findHIndex = -1;

        for(int i = index - 1; i >= index - K && i >= 0; i--){
            if(distribution[i] == 'H' && !visited[i]) findHIndex = i;
        }

        return findHIndex;
    }

    private static int rightFoundH(int index){
        for(int i = index + 1; i <= index + K && i < N; i++){
            if(distribution[i] == 'H' && !visited[i]) return i;
        }
        return -1;
    }

    public static void main(String args[]) throws IOException
    {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer tokenizer = new StringTokenizer(reader.readLine());

        N = Integer.parseInt(tokenizer.nextToken());
        K = Integer.parseInt(tokenizer.nextToken());
        visited = new boolean[N];


        distribution = reader.readLine().toCharArray();

        for(int index = 0; index < distribution.length; index++){
            // 만약 P라면 체크
            if(distribution[index] == 'P'){
                // System.out.println("index : " + index);
                // (1) 왼쪽 탐색
                int leftFoundIndex = leftFoundH(index);
                if(leftFoundIndex == -1){
                    int rightFoundIndex = rightFoundH(index);

                    if(rightFoundIndex != -1){
                        // System.out.println("rightFoundIndex : " + rightFoundIndex);
                        visited[rightFoundIndex] = true;
                        answer += 1;
                    }
                }else{
                    // System.out.println("leftFoundIndex : " + leftFoundIndex);
                    visited[leftFoundIndex] = true;
                    answer += 1;
                }


            }
        }

        // System.out.println(Arrays.toString(visited));
        System.out.println(answer);


        reader.close();
    }
}
