package Softeer.LV3.성적_평가;

import java.util.*;
import java.io.*;


public class Main {

    private static int N;

    public static void main(String args[]) throws IOException{
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));

        StringBuilder builder = new StringBuilder();
        N = Integer.parseInt(reader.readLine());

        Map<Integer, Integer> competitionTotal = new HashMap<>();
        int size = 0;
        for(int i = 0; i < 3; i++){
            StringTokenizer tokenizer = new StringTokenizer(reader.readLine());
            Map<Integer, Integer> inCompetition = new HashMap<>();

            size = tokenizer.countTokens();

            for(int k = 0; k < size; k++){
                int score = Integer.parseInt(tokenizer.nextToken());
                competitionTotal.putIfAbsent(k, 0);
                competitionTotal.put(k, competitionTotal.get(k) + score);

                inCompetition.put(k, score);
            }

            List<Integer> list = new ArrayList<>(inCompetition.keySet());

            Collections.sort(list, (c1, c2)->(inCompetition.get(c2).compareTo(inCompetition.get(c1))));

            int[] rank = new int[size + 1];
            int beforeScore = 0;
            int count = 1;
            int level = 1;
            for(int k = 0; k < size; k++){
                if(beforeScore > inCompetition.get(list.get(k))){
                    level += count;
                    count = 1;
                }else if(beforeScore == inCompetition.get(list.get(k))){
                    count += 1;
                }

                // System.out.println("i : " + k +  " list : " + list.get(k) + " level : " + level + " Competiton : " + inCompetition.get(k));
                rank[list.get(k)] = level;
                beforeScore = inCompetition.get(list.get(k));
            }

            for(int inRank = 0; inRank < size; inRank++){
                builder.append(rank[inRank]);
                if(inRank == size - 1) builder.append("\n");
                else builder.append(" ");
            }
        }


        List<Integer> list2 = new ArrayList<>(competitionTotal.keySet());

        Collections.sort(list2, (c1, c2) -> (competitionTotal.get(c2).compareTo(competitionTotal.get(c1))));

        int[] rank2 = new int[size + 1];
        int beforeScore = 0;
        int count = 1;
        int level = 1;
        for(int k = 0; k < size; k++){
            if(beforeScore > competitionTotal.get(list2.get(k))){
                level += count;
                count = 1;
            }else if(beforeScore == competitionTotal.get(list2.get(k))){
                count += 1;
            }

            rank2[list2.get(k)] = level;
            beforeScore = competitionTotal.get(list2.get(k));
        }

        for(int inRank = 0; inRank < size; inRank++){
            builder.append(rank2[inRank]);
            if(inRank == size - 1) builder.append("\n");
            else builder.append(" ");
        }

        System.out.print(builder);

        reader.close();
    }
}