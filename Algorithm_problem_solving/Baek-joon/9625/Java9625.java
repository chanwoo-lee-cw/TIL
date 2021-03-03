import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {
    public static void main(String[] args) {
        ButtonPush button;
        try {
            BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
            button = new ButtonPush(bf);
            bf.close();

            button.kPushCntAB();
            button.printCntAB();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
class ButtonPush {
    private int k;
    private int cntA;
    private int cntB;

    public ButtonPush(BufferedReader bf) throws IOException {
        this.k = Integer.parseInt(bf.readLine());
        this.cntA = 1;
        this.cntB = 0;
    }

    public void kPushCntAB() {
        int currA;
        int currB;

        for (int i=1;i<=k;i++) {
            currB = cntA + cntB;
            currA = cntB;

            cntB = currB;
            cntA = currA;
        }
    }

    public void printCntAB() {
        System.out.printf("%d %d", cntA, cntB);
    }
}