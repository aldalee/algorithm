package algorithm.greedy;

import org.junit.Test;

import java.util.Arrays;

import static algorithm.greedy.MostScheduledMeetingRooms.bestArrange;
import static algorithm.greedy.MostScheduledMeetingRooms.Meeting;
import static algorithm.greedy.MostScheduledMeetingRooms.bruteForceEnumerate;
import static org.junit.Assert.fail;

public class MostScheduledMeetingRoomsTest {
    private static Meeting[] generateMeetings(int meetingSize, int timeMax) {
        Meeting[] meetings = new Meeting[(int) (Math.random() * (meetingSize + 1))];
        for (int i = 0; i < meetings.length; i++) {
            int r1 = (int) (Math.random() * (timeMax + 1));
            int r2 = (int) (Math.random() * (timeMax + 1));
            if (r1 == r2) {
                meetings[i] = new MostScheduledMeetingRooms.Meeting(r1, r1 + 1);
            } else {
                meetings[i] = new MostScheduledMeetingRooms.Meeting(Math.min(r1, r2), Math.max(r1, r2));
            }
        }
        return meetings;
    }

    @Test
    public void testBestArrange() {
        int meetingSize = 12;
        int timeMax = 20;
        int timeTimes = 1000000;
        for (int i = 0; i < timeTimes; i++) {
            Meeting[] meetings = generateMeetings(meetingSize, timeMax);
            int expected = bruteForceEnumerate(meetings);
            int actual = bestArrange(meetings);
            if (actual != expected) {
                System.out.println("Meetings = " + Arrays.toString(meetings));
                System.out.println("Expected = " + expected);
                System.out.println("Actual = " + actual);
                fail();
                break;
            }
        }
        System.out.println("Accepted!");
    }
}
