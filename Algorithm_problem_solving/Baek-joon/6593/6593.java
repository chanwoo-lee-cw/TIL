import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {
    public static void main(String[] args) {
        BuildingEscape buildingEscape;
        StringBuilder stringBuilder = new StringBuilder();
        try (BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));) {
            while (true) {
                StringTokenizer stringTokenizer = new StringTokenizer(bufferedReader.readLine());
                int floor = Integer.parseInt(stringTokenizer.nextToken());
                int row = Integer.parseInt(stringTokenizer.nextToken());
                int col = Integer.parseInt(stringTokenizer.nextToken());
                if (floor == 0 && row == 0 && col == 0) {
                    break;
                }

                Area[][][] building = new Area[floor][row][col];
                Position start = null;

                for (int i = 0; i < floor; i++) {
                    for (int j = 0; j < row; j++) {
                        String rowInput = bufferedReader.readLine();
                        for (int k = 0; k < col; k++) {
                            building[i][j][k] = Area.getArea(rowInput.charAt(k));
                            if (building[i][j][k] == Area.START) {
                                start = new Position(i, j, k);
                            }
                        }
                    }
                    bufferedReader.readLine();
                }
                buildingEscape = new BuildingEscape(floor, row, col, building, start);
                stringBuilder.append(
                        buildingEscape.isCanEscape()
                ).append("\n");
            }
        } catch (IOException exception) {
            exception.printStackTrace();
        } catch (Exception exception) {
            exception.printStackTrace();
        }
        System.out.println(stringBuilder.toString());

    }
}


class BuildingEscape {
    private int floor, row, col;
    private Area[][][] building;
    private Position start;

    /**
     * 현재 위치에서 움직일 수 있는 방향
     */
    private final ArrayList<Position> path = new ArrayList<>(List.of(
            new Position(-1, 0, 0),
            new Position(1, 0, 0),
            new Position(0, -1, 0),
            new Position(0, 1, 0),
            new Position(0, 0, -1),
            new Position(0, 0, 1)
    ));

    public BuildingEscape(int floor, int row, int col, Area[][][] building, Position start) {
        this.floor = floor;
        this.row = row;
        this.col = col;
        this.building = building;
        this.start = start;
    }

    /**
     * 시작 위치에서 탈출이 가능한지를 판별한다.
     *
     * @return - 탈출 불가능: "Trapped!"
     * - 탈출 가능: "Escaped in %d minute(s)."
     */
    public String isCanEscape() {
        int escapeRoute = searchEscapeRoute();
        if (escapeRoute == -1) {
            return "Trapped!";
        } else {
            return String.format("Escaped in %d minute(s).", escapeRoute);
        }
    }


    /**
     * 탈출할 수 있는지를 bfs 방식으로 최단 거리를 찾는다
     *
     * @return - 탈출 가능할 때 : 걸리는 초
     * - 불가능할 때 : -1
     */
    private int searchEscapeRoute() {
        Queue<CurrPosition> queue = new LinkedList<>();
        boolean[][][] visited = new boolean[floor][row][col];
        insertQueue(queue, visited, new CurrPosition(start, 0));

        while (!queue.isEmpty()) {
            CurrPosition curr = queue.poll();
            for (Position next : path) {
                CurrPosition nextPosition = new CurrPosition(
                        curr.getFloor() + next.getFloor(),
                        curr.getRow() + next.getRow(),
                        curr.getCol() + next.getCol(),
                        curr.getMoveCnt() + 1
                );
                if (unlessValidateVisit(visited, nextPosition)) {
                    continue;
                }
                if (building[nextPosition.getFloor()][nextPosition.getRow()][nextPosition.getCol()] == Area.ESCAPE) {
                    return nextPosition.getMoveCnt();
                }
                insertQueue(queue, visited, nextPosition);
            }
        }
        return -1;
    }

    /**
     * 이미 방문한 지역인지 or 접근할 수 없는 지역인지 체크
     */
    private boolean unlessValidateVisit(
            boolean[][][] visited,
            CurrPosition nextPosition
    ) {
        if (nextPosition.getFloor() < 0 || nextPosition.getFloor() >= floor
                || nextPosition.getRow() < 0 || nextPosition.getRow() >= row
                || nextPosition.getCol() < 0 || nextPosition.getCol() >= col
        ) {
            return true;
        } else if (visited[nextPosition.getFloor()][nextPosition.getRow()][nextPosition.getCol()]) {
            return true;
        } else if (building[nextPosition.getFloor()][nextPosition.getRow()][nextPosition.getCol()] == Area.WALL) {
            return true;
        }
        return false;
    }

    /**
     * BFS 큐에 다음 위치를 집어 넣고, Visited에 체크한다.
     *
     * @param queue        bfs 큐
     * @param visited      방문 여부
     * @param nextPosition 현재 위치
     */
    private void insertQueue(
            Queue<CurrPosition> queue,
            boolean[][][] visited,
            CurrPosition nextPosition
    ) {
        queue.add(nextPosition);
        visited[nextPosition.getFloor()][nextPosition.getRow()][nextPosition.getCol()] = true;
    }

}

class Position {
    private int floor;
    private int row;
    private int col;

    public Position(int floor, int row, int col) {
        this.floor = floor;
        this.row = row;
        this.col = col;
    }

    public int getFloor() {
        return floor;
    }

    public int getRow() {
        return row;
    }

    public int getCol() {
        return col;
    }
}

class CurrPosition extends Position {
    private int moveCnt;

    public CurrPosition(Position position, int moveCnt) {
        super(position.getFloor(), position.getRow(), position.getCol());
        this.moveCnt = moveCnt;
    }

    public CurrPosition(int floor, int row, int col, int moveCnt) {
        super(floor, row, col);
        this.moveCnt = moveCnt;
    }

    public int getMoveCnt() {
        return moveCnt;
    }
}


enum Area {
    START('S'),
    EMPTY('.'),
    WALL('#'),
    ESCAPE('E');

    private char value;

    Area(char value) {
        this.value = value;
    }


    public char getValue() {
        return value;
    }

    public static Area getArea(char value) {
        for (Area area : values()) {
            if (area.getValue() == value) {
                return area;
            }
        }
        throw new IllegalArgumentException("Unknown area: " + value);
    }
}