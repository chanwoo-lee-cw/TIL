import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {

    public static void main(String[] args) {
        int frameSize = 0;
        try {
            BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
            frameSize = Integer.parseInt(bf.readLine());
        } catch (IOException e) {
            return;
        }

        printWhelkFrame(frameSize);
    }

    /**
     * 셀의 크기가 frameSize인 골뱅이 프레임을 생성한다.
     *
     * @param frameSize 셀의 크기
     * @return 2차원 배열의 골뱅이 프레임
     */
    private static char[][] getWhelkFrame(int frameSize) {
        char[][] whelkFrame = new char[frameSize * 5][frameSize * 5];
        for (int i = 0; i < frameSize * 5; i++) {
            Arrays.fill(whelkFrame[i], '@');
        }
        // 가운데 빈 공간을 삭제한다. 빈 공간의 사이즈 frameSize -> frameSize*4
        for (int i = frameSize; i < frameSize * 4; i++) {
            Arrays.fill(whelkFrame[i], frameSize, frameSize * 4, ' ');
        }

        return whelkFrame;
    }

    /**
     * 셀의 크기가 frameSize인 골뱅이 프레임을 출력한다.
     *
     * @param frameSize 셀의 크기
     */
    private static void printWhelkFrame(int frameSize) {
        char[][] whelkFrame = getWhelkFrame(frameSize);
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < frameSize * 5; i++) {
            for (int j = 0; j < frameSize * 5; j++) {
                sb.append(whelkFrame[i][j]);
            }
            sb.append("\n");
        }
        System.out.println(sb);
    }
}