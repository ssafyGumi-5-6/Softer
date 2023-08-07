package Softeer.LV3.우물_안_개구리;


import java.util.*;
import java.io.*;


public class Solution
{

    private static int N, M;
    private static int[] weight;
    private static int[] whoisBestMember;
    public static void main(String args[]) throws IOException
    {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer tokenizer = new StringTokenizer(reader.readLine());
        N = Integer.parseInt(tokenizer.nextToken());
        M = Integer.parseInt(tokenizer.nextToken());

        weight = new int[N];
        whoisBestMember = new int[N];
        tokenizer = new StringTokenizer(reader.readLine());
        List<Integer> list = new ArrayList<>();
        while(tokenizer.hasMoreTokens()) list.add(Integer.parseInt(tokenizer.nextToken()));
        weight = list.stream().mapToInt(Integer::intValue).toArray();

        for(int i = 0; i < N; i++) whoisBestMember[i] = 1;

        for(int i = 0; i < M; i++){
            tokenizer = new StringTokenizer(reader.readLine());
            int leftIndex = Integer.parseInt(tokenizer.nextToken());
            int rightIndex = Integer.parseInt(tokenizer.nextToken());

            if(weight[leftIndex - 1] > weight[rightIndex - 1]){
                whoisBestMember[rightIndex - 1] = 0;
            }else if(weight[leftIndex - 1] < weight[rightIndex - 1]){
                whoisBestMember[leftIndex - 1] = 0;
            }else{
                // 같을 경우 둘다 -1
                whoisBestMember[leftIndex - 1] = 0;
                whoisBestMember[rightIndex - 1] = 0;
            }
        }

        int answer = 0;
        for(int peopleIndex = 0; peopleIndex < N; peopleIndex++){
            if(whoisBestMember[peopleIndex] == 1) answer += 1;
        }

        System.out.println(answer);

        reader.close();
    }
}