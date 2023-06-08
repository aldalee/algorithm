package galaxybase;

import java.util.Scanner;

/**
 * 创邻科技笔试题
 * @author HuanyuLee
 * @date 2023/6/8
 */
public class BowlingGame {
    /**
     * 计算保龄球比赛的得分。
     * @param input 保龄球比赛的得分字符串
     *              由 X（全中）、/（补中）、数字和 -（未击倒任何瓶）组成。
     * @return 保龄球比赛的总得分。
     */
    private static int calculateScore(String input) {
        int score = 0;
        int ballIndex = 0;
        int[] balls = new int[22];
        // 将字符转换为数字，存入数组中
        for (int i = 0; i < input.length(); i++) {
            char c = input.charAt(i);
            if (c == 'X') {
                balls[ballIndex++] = 10;
            } else if (c == '/') {
                balls[ballIndex++] = 10 - balls[ballIndex - 1];
            } else if (c == '-') {
                balls[ballIndex++] = 0;
            } else {
                balls[ballIndex++] = Character.getNumericValue(c);
            }
        }

        ballIndex = 0;
        // 计算每一轮的得分，并将总得分相加
        for (int frame = 0; frame < 10; frame++) {
            if (balls[ballIndex] == 10) {
                score += 10 + balls[ballIndex + 1] + balls[ballIndex + 2];
                ballIndex++;
            } else if (balls[ballIndex] + balls[ballIndex + 1] == 10) {     // 补中
                score += 10 + balls[ballIndex + 2];
                ballIndex += 2;
            } else {    // 未击倒全部瓶或只击倒一部分瓶
                score += balls[ballIndex] + balls[ballIndex + 1];
                ballIndex += 2;
            }
        }
        return score;
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        while (sc.hasNextLine()) {
            String input = sc.nextLine();
            System.out.println(calculateScore(input));
        }
        sc.close();
    }
}
