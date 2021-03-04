import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {
    public static void main(String[] args) {
        ButtonPush button;
        try {
            BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
            button = new ButtonPush();
            button.kPushCntAB(Integer.parseInt(bf.readLine()));
            bf.close();
            button.printCntAB();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
class ButtonPush {
    private int cntA;   // A의 갯수
    private int cntB;   // B의 갯수

    public ButtonPush() {
        this.cntA = 1;
        this.cntB = 0;
    }

    /*
    K번 버튼을 누른다.
    */
    public void kPushCntAB(int k) {
        int currA;
        int currB;

        for (int i=1;i<=k;i++) {
            currB = cntA + cntB;
            currA = cntB;

            cntB = currB;
            cntA = currA;
        }
    }

    /*
    A,B의 갯수를 출력한다.
    */
    public void printCntAB() {
        System.out.printf("%d %d", cntA, cntB);
    }
}