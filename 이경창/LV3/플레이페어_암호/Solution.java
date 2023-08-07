package Softeer.LV3.플레이페어_암호;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;
import java.util.stream.Collectors;

import java.util.*;
import java.io.*;
import java.util.stream.Collectors;


public class Solution
{
    private static String message;
    private static String key;
    // private static int[] alphabet;

    public static void main(String args[]) throws IOException
    {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        message = reader.readLine();
        key = reader.readLine();

        // (1) Key
        Set<Character> set = key.chars().mapToObj(c -> (char)c).collect(Collectors.toSet());

        int alphabetIndex = 0;
        for(int i = 0; i < 25 && set.size() < 25; i++){
            char c = (char)('a' + i);
            if(set.contains(c)) continue;
            set.add(c);
        }

        // (2) Message
        Queue<Character> queue = new LinkedList<>();
        int plus = 0;
        for(int i = 0; i < message.length() + plus; i += 2){
            char first = message.charAt(i);
            char second = message.charAt(i + 1);
            if(i + 1 >= message.length() + plus) break;

            if(first != second){
                queue.add(first);
                queue.add(second);
            }else if(first == 'X' && second == 'X' && i < (message.length() + plus) - 2){
                queue.add(first);
                queue.add('Q');
                i -= 1;
            }else{
                // 같은데 X가 아니라면
                queue.add(first);
                queue.add('X');
                i -= 1;
            }
        }

        if((message.length() + plus) % 2 == 1) queue.add('X');

        // (3)

        reader.close();
    }
}