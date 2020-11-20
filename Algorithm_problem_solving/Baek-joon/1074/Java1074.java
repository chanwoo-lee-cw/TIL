import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

// https://www.acmicpc.net/problem/1074
public class Java1074 {
    private static int N, r ,c;

    public static void main(String[] args) throws IOException{
        BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(bf.readLine());

        N = Integer.parseInt(st.nextToken());
        r = Integer.parseInt(st.nextToken());
        c = Integer.parseInt(st.nextToken());

        int len = (int)Math.pow(2, N);

        System.out.println(Z(0, len, 0, len));
    }
    public static int Z(int xs, int xe, int ys, int ye) {
        int xmid = (xs + xe) / 2;
        int ymid = (ys + ye) / 2;
        int len = xmid - xs;

        if(Math.abs(xe-xs) <= 1)
            return 0;
        else if (r < ymid && c < xmid) {
            return Z(0, xmid, 0, ymid);
        }
        else if (r < ymid && c >= xmid) {
            return (int)Math.pow(len, 2) + Z(xmid, xe, 0, ymid);
        }
        else if (r >= ymid && c < xmid ) {
            return 2*(int)Math.pow(len, 2) + Z(0, xmid, ymid, ye);
        }
        else {
            return 3*(int)Math.pow(len, 2) + Z(xmid, xe, ymid, ye);
        }
    }
}