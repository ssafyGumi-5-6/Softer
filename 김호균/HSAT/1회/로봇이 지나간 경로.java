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

                    // 처음 발견한 #이 아래와 오른쪽 연결밖에 없다.
                    // 아래로 연결되면 ^ 이 방향으로 도착이므로 거꾸로 진행 좌회전이 우회전 우회전이 좌회전
                    // 오른쪽 연결이면 > 이 방향으로 시작이므로 정방향 진행

                    if(i + 1 < H && board[i + 1][j] == '#') {
                        // 아래 역방향 진행, 출발지점 찾는 과정
                        if(j + 1 < W && board[i][j + 1] == '#') {
                            // 정방향 진행, 도착지점 찾는 과정 추가
                            findStartInit('>', sb, i, j);
                            findEnd('>', sb, i, j);
                        } else {
                            findStart('^', sb, i, j);
                        }

                    } else if(j + 1 < W && board[i][j + 1] == '#') {
                        a = i + 1;
                        b = j + 1;
                        dir = '>';
                        findEnd('>', sb, i, j);
                    }
                }
            }
        }

        System.out.println(a + " " + b);
        System.out.println(dir);
        System.out.println(sb);
        sc.close();
    }

    private static char findStartInit(char nextDir, StringBuilder command, int y, int x) {

        board[y][x] = '.';
        char previousDir = 'x';
        char currentDir = 'x';

        int ny = y + 2;
        int nx = x;

        currentDir = '^';


        findStart(currentDir, command, ny, nx);
    

        command.append('A');
        if(nextDir == '^') {
            if(currentDir == '>') {
                command.append('L');
            } else if(currentDir == '<') {
                command.append('R');
            }
        } else if(nextDir == 'v') {
            if(currentDir == '>') {
                command.append('R');
            } else if(currentDir == '<') {
                command.append('L');
            }
        } else if(nextDir == '<') {
            if(currentDir == '^') {
                command.append('L');
            } else if(currentDir == 'v') {
                command.append('R');
            }
        } else if(nextDir == '>') {
            if(currentDir == '^') {
                command.append('R');
            } else if(currentDir == 'v') {
                command.append('L');
            }
        }

        return currentDir;
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
        if(nextDir == '^') {
            if(currentDir == '>') {
                command.append('L');
            } else if(currentDir == '<') {
                command.append('R');
            }
        } else if(nextDir == 'v') {
            if(currentDir == '>') {
                command.append('R');
            } else if(currentDir == '<') {
                command.append('L');
            }
        } else if(nextDir == '<') {
            if(currentDir == '^') {
                command.append('L');
            } else if(currentDir == 'v') {
                command.append('R');
            }
        } else if(nextDir == '>') {
            if(currentDir == '^') {
                command.append('R');
            } else if(currentDir == 'v') {
                command.append('L');
            }
        }

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

            if(nextDir == '^') {
                if(currentDir == '>') {
                    command.append('L');
                } else if(currentDir == '<') {
                    command.append('R');
                }
            } else if(nextDir == 'v') {
                if(currentDir == '>') {
                    command.append('R');
                } else if(currentDir == '<') {
                    command.append('L');
                }
            } else if(nextDir == '<') {
                if(currentDir == '^') {
                    command.append('L');
                } else if(currentDir == 'v') {
                    command.append('R');
                }
            } else if(nextDir == '>') {
                if(currentDir == '^') {
                    command.append('R');
                } else if(currentDir == 'v') {
                    command.append('L');
                }
            }

            command.append('A');
            findEnd(nextDir, command, ny, nx);
        }
    }
}
