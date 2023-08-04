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

        for(int i = 0 ; i < K; i++){
            tokenizer = new StringTokenizer(reader.readLine());
            int startIndex = Integer.parseInt(tokenizer.nextToken());
            int endIndex = Integer.parseInt(tokenizer.nextToken());

        }

        reader.close();
    }
}
