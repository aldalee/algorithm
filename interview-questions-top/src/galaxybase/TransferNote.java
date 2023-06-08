package galaxybase;

import java.util.Scanner;

/**
 * 创邻科技笔试题
 * @author HuanyuLee
 * @date 2023/6/8
 */
public class TransferNote {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int month = scanner.nextInt();
        int day = scanner.nextInt();
        scanner.nextLine();
        String message = scanner.nextLine();
        String[] groups = new String[]{"ABCDEFGHI", "JKLMNOPQR", "STUVWXYZ "};
        // 根据月份移动组
        for (int i = 0; i < (month - 1) % 3; i++) {
            String firstGroup = groups[0];
            groups[0] = groups[1];
            groups[1] = groups[2];
            groups[2] = firstGroup;
        }
        // 根据日期移动组内字符
        for (int i = 0; i < groups.length; i++) {
            groups[i] = groups[i].substring((day - 1) % 9) + groups[i].substring(0, (day - 1) % 9);
        }
        StringBuilder encodedMessage = new StringBuilder();
        for (int i = 0; i < message.length(); i++) {
            char c = message.charAt(i);
            int groupIndex = -1;
            int charIndex = -1;
            for (int j = 0; j < groups.length; j++) {
                charIndex = groups[j].indexOf(c);
                if (charIndex != -1) {
                    groupIndex = j + 1;
                    break;
                }
            }
            if (i != 0) {
                encodedMessage.append(" ");
            }
            encodedMessage.append(groupIndex).append(charIndex + 1);
        }
        System.out.println(encodedMessage);
    }
}
