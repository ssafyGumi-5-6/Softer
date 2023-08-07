package Softeer.LV3.플레이페어_암호;

import java.util.*;
import java.io.*;
import java.util.stream.Collectors;


public class Main
{
    private static String message;
    private static String key;

    private static int swap(int a, int b){
        return a;
    }

    private static char[] encryptTwoChars(char first, char second, String table){
        int firstIndex = table.indexOf(first);
        int secondIndex = table.indexOf(second);

        int firstRow = firstIndex / 5;
        int firstCol = firstIndex % 5;

        int secondRow = secondIndex / 5;
        int secondCol = secondIndex % 5;


        if(firstRow == secondRow){
            // (1) 행 위치가 같다면 열 위치 한 칸 이동
            firstCol = (firstCol + 1) % 5;
            secondCol = (secondCol + 1) % 5;
        }else if(firstCol == secondCol){
            // (2) 열 위치가 같다면 행 위치 한 칸 이동
            firstRow = (firstRow + 1) % 5;
            secondRow = (secondRow + 1) % 5;
        }else {
            // (3) 1, 2 경우를 만족하지 않는다면
            // (y, x) -> (y, x2), (y2, x2) -> (y2, x) 교환된 위치에 적힌 글자가 암호화
            // x 위치 교환
            secondCol = swap(firstCol, firstCol = secondCol);
        }

        char[] result = {
                table.charAt(firstRow * 5 + firstCol % 5),
                table.charAt(secondRow * 5 + secondCol % 5)
        };

        return result;
    }

    public static void main(String args[]) throws IOException
    {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        message = reader.readLine();
        key = reader.readLine();

        // (1) Key
        Set<Character> set = key.chars().mapToObj(c -> (char)c).collect(Collectors.toCollection(LinkedHashSet::new));


        int alphabetIndex = 0;
        for(int i = 0; i < 26 && set.size() < 25; i++){
            char c = (char)('A' + i);
            if(c == 'J' || set.contains(c)) continue;
            set.add(c);
        }

        // (2) Message
        Queue<Character> queue = new LinkedList<>();

        for(int i = 0; i < message.length(); i += 2){
            char first = message.charAt(i);

            if(i + 1 >= message.length()){
                // 마지막 인덱스가 홀수 개 일 때
                queue.add(first);
                queue.add('X');
                break;
            }

            char second = message.charAt(i + 1);

            if(first != second){
                queue.add(first);
                queue.add(second);
            }else if(first == 'X' && second == 'X'){
                // 마지막 위치 이전에 XX가 나왔을 때
                queue.add(first);
                queue.add('Q');
                i -= 1;
            }else{
                // AA, BB, CC 등 일 때 같은데 X가 아니라면
                queue.add(first);
                queue.add('X');
                i -= 1;
            }
        }

        // (3)
        StringBuilder answer = new StringBuilder();

        // Set -> list을 2차원 배열로 변경
        List<Character> list = new ArrayList<>(set);
        // char[][] table2 = new char[5][5];
        String table = set.stream().map(String::valueOf).collect(Collectors.joining());

        // for(int i = 0; i < 25; i++){
        //     int row = i / 5;
        //     int col = i % 5;

        //     table2[row][col] = list.get(i);
        // }

        // System.out.println(Arrays.deepToString(table2));


        while(queue.size() > 0){
            char first = queue.poll();
            char second = queue.poll();
            answer.append(encryptTwoChars(first, second, table));
        }

        System.out.println(answer);

        reader.close();
    }
}