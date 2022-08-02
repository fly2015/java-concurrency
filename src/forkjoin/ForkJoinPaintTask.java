/*
 * ForkJoinPaintTask.java
 *
 * Copyright by CRIF AG
 * Zürich
 * All rights reserved.
 */
package forkjoin;

/**
 * 
 *
 * @author nhqhien
 * @version $Revision:  $
 */
public class ForkJoinPaintTask
{
    /*
     * compute() {
     * if(isFenceSectionSmall()) { // is it a manageable amount of work?
     * paintFenceSection(); // do the task
     * } else { // task too big, split it
     * ForkJoinPaintTask leftHalf = getLeftHalfOfFence();
     * leftHalf.fork(); // queue left half of task
     * ForkJoinPaintTask rightHalf = getRightHalfOfFence();
     * rightHalf.compute(); // work on right half of task
     * leftHalf.join(); // wait for queued task to be complete
     * }
     * }
     */
}



/*
 * Changes:
 * $Log: $
 */