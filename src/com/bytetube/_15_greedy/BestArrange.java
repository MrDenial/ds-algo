package com.bytetube._15_greedy;

import java.util.Arrays;
import java.util.Comparator;

public class BestArrange {

    private static class Meeting{
        public int start;
        public int end;

        public Meeting(int start, int end) {
            this.start = start;
            this.end = end;
        }
    }

    private static class MeetingComparator implements Comparator<Meeting> {
        @Override
        public int compare(Meeting m1, Meeting m2) {
            return m1.end-m2.end;
        }
    }


    public static int bestArrange(Meeting[] meetings,int current){
        Arrays.sort(meetings,new MeetingComparator());
        int count = 0;
        for (int i = 0; i < meetings.length; i++) {
            if (current <= meetings[i].start) {
                count++;
                current = meetings[i].end;
            }
        }

        return count;

    }


}
