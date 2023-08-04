package Softeer.LV3.성적_평균;

import java.util.*;
import java.io.*;

public class Solution {

    private static int N, K;
    private static int[] score;
    private static int[] dp;

    public static void main(String[] args) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder builder = new StringBuilder();
        StringTokenizer tokenizer = new StringTokenizer(reader.readLine());
        N = Integer.parseInt(tokenizer.nextToken());
        K = Integer.parseInt(tokenizer.nextToken());

        score = new int[N];
        dp = new int[N];

        tokenizer = new StringTokenizer(reader.readLine());
        List<Integer> list = new ArrayList<>();
        while(tokenizer.hasMoreTokens()) list.add(Integer.parseInt(tokenizer.nextToken()));
        score = list.stream().mapToInt(Integer::intValue).toArray();

        dp[0] = score[0];
        for(int i = 1 ; i < N; i++){
            dp[i] = dp[i - 1] + score[i];
        }

        for(int i = 0 ; i < K; i++){
            tokenizer = new StringTokenizer(reader.readLine());
            int startIndex = Integer.parseInt(tokenizer.nextToken()) - 1;
            int endIndex = Integer.parseInt(tokenizer.nextToken()) - 1;

            double share = (double)(dp[endIndex] - dp[startIndex] + score[startIndex]) / (endIndex - startIndex + 1) * 100;
            double answer = Math.ceil(share) / 100;
            builder.append(String.format("%.2f",answer)).append("\n");
        }
        System.out.print(builder);

        reader.close();
    }
}
