import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;


/**
 * 선물을 받을 확률을 계산한다.
 */
class CommentGift {
    private final Long startComment;
    private final Long endComment;

    CommentGift(Long startComment, Long endComment) {
        this.startComment = startComment;
        this.endComment = endComment;
    }


    /**
     * number 이하의 제곱근의 갯수를 구하는 함수
     */
    private Long getDivisorOddNumber(Long number) {
        long pivot = 0;
        long div = 1;
        long result = 0;

        while (pivot <= number) {
            result += 1;
            pivot += div;
            div += 2;
        }

        return result;
    }


    /**
     * 두 수의 최대 공약수를 구하는 함수
     */
    private static Long gcd(Long a, Long b) {
        if (b > a) {
            b = a;
        }
        // else
        while (b != 0) {
            Long temp = b;
            b = a % b;
            a = temp;
        }
        return a;
    }

    /**
     * 찬미가 경품을 받을 확률을 리턴한다.
     */
    public String getGiftRate() {
        StringBuilder result = new StringBuilder();
        Long denom = endComment - startComment;
        Long rangeOdd = getDivisorOddNumber(endComment) - getDivisorOddNumber(startComment);
        if (rangeOdd == 0) {
            result.append(0);
        } else {
            Long gcdNumber = gcd(denom, rangeOdd);
            result.append(rangeOdd / gcdNumber);
            result.append("/");
            result.append(denom / gcdNumber);
        }
        return result.toString();
    }

}

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(bf.readLine());

        Long alpha = Long.parseLong(st.nextToken());
        Long beta = Long.parseLong(st.nextToken());

        CommentGift commentGift = new CommentGift(alpha, beta);
        System.out.println(commentGift.getGiftRate());
    }
}
