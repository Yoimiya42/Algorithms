/*
 * 
 Exercise 4 – Idle Times
    Consider a machine that needs to process n jobs. Design and implement an algorithm that, given the list of n jobs with their start and end times, determines the largest interval where the machine is idle, and the largest interval where the machine is not idle
 */

import java.util.Random;


public class SP4_4_IdleTime {
    public static void main(String[] args)
    {
        Random random = new Random();
        int jobNums = 12;
        int intervalHigh = 200;
        int max_duration = 25;

        Job[] jobList = new Job[jobNums];
        for(int i = 0; i< jobNums; i++)
        {
            int start = random.nextInt(intervalHigh + 1);
            int end = start + 2 + random.nextInt(max_duration + 1);
            jobList[i] = new Job(start, end);
        }

        Machine m = new Machine();

        m.statWork(jobList);
    }
}

class Job implements Comparable<Job>
{
    int start;
    int end;

    public Job(int start, int end)
    {
        this.start = start;
        this.end = end;
    }

    public int duration(){
        return end - start;
    }

    @Override
    public int compareTo(Job job)
    {
        return Integer.compare(this.start, job.start);
    }

    public boolean isContinue(Job job){
        return this.end >= job.start;
    }

    @Override
    public String toString()
    {
        return String.format("%d - %d\n", start, end);
    }
}

class Machine{

    public void merge(Job[] jobs, int left, int mid ,int right)
    {
        Job[] temp = new Job[right - left + 1];
        int l = left;
        int r = mid + 1;
        int t = 0;

        while(l <= mid && r <= right)
        {
            if(jobs[l].compareTo(jobs[r]) == -1)
                temp[t++] = jobs[l++]; 
            else
                temp[t++] = jobs[r++];
        }

        while(l <= mid)
            temp[t++] = jobs[l++];

        while(r <= right)
            temp[t++] = jobs[r++];

        System.arraycopy(temp, 0, jobs, left, temp.length);
    }

    public void mergeSort(Job[] jobList, int left, int right)
    {
        if(left >= right)
            return;

        int mid = left + (right - left) / 2;

        mergeSort(jobList, left, mid);
        mergeSort(jobList, mid + 1, right);
        merge(jobList, left, mid, right);
    }


    public void reset(int[] record)
    {
        record[0] = Integer.MAX_VALUE;
        record[1] = Integer.MIN_VALUE;
    }

    public void statWork(Job[] jobs)
    {
        mergeSort(jobs, 0, jobs.length - 1);

        int max_working_interval[] = {0, 0};
        int max_working = Integer.MIN_VALUE;
        int working[] = {jobs[0].start, jobs[0].end};

        int min_idle_interval[] = {0, 0};
        int min_idle = Integer.MAX_VALUE;
        int idle[] = {jobs[0].end, jobs[0].start};

        for(int i = 1; i < jobs.length; i++)
        {
            if(jobs[i - 1].isContinue(jobs[i]) || jobs[i].start <= working[1])
            {
                working[0] = Math.min(working[0], jobs[i-1].start);
                working[1] = Math.max(working[1], jobs[i].end);
                if(working[1] - working[0] > max_working)
                {
                    max_working = working[1] - working[0];
                    max_working_interval[0] = working[0];
                    max_working_interval[1] = working[1];
                }
            }else{
                reset(working);
                idle[0] = jobs[i-1].end;
                idle[1] = jobs[i].start;
                if(idle[1] - idle[0] < min_idle)
                {
                    min_idle = idle[1] - idle[0];
                    min_idle_interval[0] = idle[0];
                    min_idle_interval[1] = idle[1];
                }
            }
        }

        System.out.printf("Max Working Interval: %d - %d, Duration: %d\n", max_working_interval[0], max_working_interval[1], max_working);
        System.out.printf("Max Idle Interval: %d - %d, Duration: %d\n", min_idle_interval[0], min_idle_interval[1], min_idle);
    }
}
