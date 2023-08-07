import java.util.*;
import java.io.*;


public class Main
{
    static int H, W, a, b;
    static char dir;
    static char[][] board;

    public static void main(String args[])
    {
        Scanner sc = new Scanner(System.in);
        StringBuilder sb = new StringBuilder();

        H = sc.nextInt();
        W = sc.nextInt();
        sc.nextLine();

        board = new char[H][W];

        a = -1;
        b = -1;
        dir = 'x';

        for(int i = 0; i < H; ++i) {
            board[i] = sc.nextLine().toCharArray();
        }

        for(int i = 0; i < H; ++i) {
            for(int j = 0; j < W; ++j) {
                if(board[i][j] == '#') {

                    if(i + 1 < H && board[i + 1][j] == '#') {
                        if(j + 1 < W && board[i][j + 1] == '#') {
                            // 출발지점 찾는 과정, 도착지점 찾는 과정 추가
                            findStartInit('>', sb, i, j);
                            findEnd('>', sb, i, j);
                        } else {
                            // 출발지점 찾는 과정
                            findStartInit('^', sb, i, j);
                        }
                    } else if(j + 1 < W && board[i][j + 1] == '#') {
                        // 현재 위치가 시작지점이므로 도착지점만 찾으면 된다.
                        a = i + 1;
                        b = j + 1;
                        dir = '>';
                        findEnd('>', sb, i, j);
                    }

                    break;
                }
            }
        }

        System.out.println(a + " " + b);
        System.out.println(dir);
        System.out.println(sb);
        sc.close();
    }

    private static void findStartInit(char nextDir, StringBuilder command, int y, int x) {

        board[y][x] = '.';
        char currentDir = '^';

        findStart(currentDir, command, y + 2, x);

        command.append('A');
        command.append(selectDir(nextDir, currentDir));
    }
    private static char findStart(char nextDir, StringBuilder command, int y, int x) {
        int[] dy = {-1, 1, 0, 0};
        int[] dx = {0, 0, 1, -1};

        board[y][x] = '.';
        char previousDir = 'x';
        char currentDir = 'x';
        for(int d = 0; d < 4; ++d) {

            int ny = y + dy[d];
            int nx = x + dx[d];

            if(ny < 0 || nx < 0 || ny >= H || nx >= W || board[ny][nx] == '.') {
                continue;
            }

            ny = ny + dy[d];
            nx = nx + dx[d];

            if(ny < 0 || nx < 0 || ny >= H || nx >= W || board[ny][nx] == '.') {
                continue;
            }

            switch(d) {
                case 0:
                    currentDir = 'v';
                    break;
                case 1:
                    currentDir = '^';
                    break;
                case 2:
                    currentDir = '<';
                    break;
                case 3:
                    currentDir = '>';
                    break;
                default:
                    break;
            }

            previousDir = findStart(currentDir, command, ny, nx);
        }

        if(previousDir == 'x') {
            dir = nextDir;
            a = y + 1;
            b = x + 1;

            return nextDir;
        }

        command.append('A');
        command.append(selectDir(nextDir, currentDir));

        return currentDir;
    }

    private static void findEnd(char currentDir, StringBuilder command, int y, int x) {
        int[] dy = {-1, 1, 0, 0};
        int[] dx = {0, 0, 1, -1};

        board[y][x] = '.';

        char nextDir = 'x';
        for(int d = 0; d < 4; ++d) {

            int ny = y + dy[d];
            int nx = x + dx[d];

            if(ny < 0 || nx < 0 || ny >= H || nx >= W || board[ny][nx] == '.') {
                continue;
            }

            ny = ny + dy[d];
            nx = nx + dx[d];

            if(ny < 0 || nx < 0 || ny >= H || nx >= W || board[ny][nx] == '.') {
                continue;
            }

            switch(d) {
                case 0:
                    nextDir = '^';
                    break;
                case 1:
                    nextDir = 'v';
                    break;
                case 2:
                    nextDir = '>';
                    break;
                case 3:
                    nextDir = '<';
                    break;
                default:
                    break;
            }

            command.append(selectDir(nextDir, currentDir));
            command.append('A');

            findEnd(nextDir, command, ny, nx);
        }
    }

    private static String selectDir(char nextDir, char currentDir) {
        if(nextDir == '^') {
            if(currentDir == '>') {
                return "L";
            } else if(currentDir == '<') {
                return "R";
            }
        } else if(nextDir == 'v') {
            if(currentDir == '>') {
                return "R";
            } else if(currentDir == '<') {
                return "L";
            }
        } else if(nextDir == '<') {
            if(currentDir == '^') {
                return "L";
            } else if(currentDir == 'v') {
                return "R";
            }
        } else if(nextDir == '>') {
            if(currentDir == '^') {
                return "R";
            } else if(currentDir == 'v') {
                return "L";
            }
        }

        return "";
    }
}


