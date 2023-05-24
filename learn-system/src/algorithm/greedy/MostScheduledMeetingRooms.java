package algorithm.greedy;

import java.util.Arrays;
import java.util.Comparator;

/**
 * 最多被安排的会议次数
 * 一些项目要占用一个会议室宣讲，会议室不能同时容纳两个项目的宣讲。
 * 给你每一个项目开始的时间和结束的时间，你来安排宣讲的日程，
 * 要求会议室进行的宣讲的场次最多，返回最多的宣讲场次。
 * @author HuanyuLee
 * @date 2023/5/24
 */
public class MostScheduledMeetingRooms {
    static class Meeting {
        int start;
        int end;

        public Meeting(int start, int end) {
            this.start = start;
            this.end = end;
        }
    }

    /**
     * 暴力枚举
     */
    public static int bruteForceEnumerate(Meeting[] meetings) {
        if (meetings == null || meetings.length == 0) {
            return 0;
        }
        return process(meetings, 0, 0);
    }

    /**
     * 暴力递归，枚举所以可能，找到最优解
     * @param meetings 剩下的会议
     * @param done     已经安排的会议
     * @param timeLine 当前的时间点
     * @return 能安排的最多会议数量
     */
    private static int process(Meeting[] meetings, int done, int timeLine) {
        if (meetings.length == 0) {
            return done;
        }
        int ans = done;
        // 逐一安排所有会议，进行尝试
        for (int i = 0; i < meetings.length; i++) {
            if (meetings[i].start >= timeLine) {
                var next = copyButExcept(meetings, i);
                ans = Math.max(ans, process(next, done + 1, meetings[i].end));
            }
        }
        return ans;
    }

    private static Meeting[] copyButExcept(Meeting[] meetings, int i) {
        Meeting[] ans = new Meeting[meetings.length - 1];
        int idx = 0;
        for (int k = 0; k < meetings.length; k++) {
            if (k != i) {
                ans[idx++] = meetings[k];
            }
        }
        return ans;
    }

    /**
     * 贪心策略
     */
    public static int bestArrange(Meeting[] meetings) {
        // 按照会议的结束时间进行升序排序
        Arrays.sort(meetings, Comparator.comparingInt(o -> o.end));
        var timeLine = 0;
        var ans = 0;
        for (var meeting : meetings) {
            if (timeLine <= meeting.start) {
                ans++;
                timeLine = meeting.end;
            }
        }
        return ans;
    }
}
