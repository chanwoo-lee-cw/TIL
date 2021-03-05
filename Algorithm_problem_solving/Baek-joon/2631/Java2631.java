import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

// https://www.acmicpc.net/problem/2631
public class Main {
    public static void main(String[] args) {
        Childs child;
        try {
            BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
            child = new Childs(bf);
            System.out.println(child.getMinChildMove());
            bf.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}

class Childs {
    private int n;          // 아이들의 인원
    private int[] line;     // 아이들이 서 있는 순서

    Childs(BufferedReader bf) throws IOException{
        this.n = Integer.parseInt(bf.readLine());
        line = new int[n];
        for (int i=0;i<n;i++) {
            line[i] = Integer.parseInt(bf.readLine());
        }
    }

    public int lowerBound(int line[], int s, int e, int search) {
        int m = 0;
        while (e - s > 0) {
            m = (e + s) / 2;
            if (line[m] < search)
                s = m + 1;
            else
                e = m;
        }
        return (e + s) / 2 + 1;
    }

    /*
    가장 긴 부분 분열의 길
     */
    private int lis(int n) {
        int[] lcsList = new int[n];
        int lcsLen = 1;
        lcsList[0] = line[0];

        for (int i = 1; i < n; i++) {
            if (line[i] < lcsList[lcsLen - 1]) {
                lcsList[lowerBound(lcsList, 0, lcsLen, line[i])-1] = line[i];
            } else if (line[i] > lcsList[lcsLen - 1]) {
                lcsList[lcsLen] = line[i];
                lcsLen += 1;
            }
        }
        return lcsLen;
    }

    /*
    가장 긴 부분 분열을 구해서 나머지는 전부 움직이면 된다.
     */
    public int getMinChildMove() {
        int answer = n;
        return answer - lis(n);
    }

}