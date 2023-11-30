/*
 * Copyright (c) Joseph Prichard 2023.
 */

package services;

import java.util.*;
import java.util.concurrent.*;
import java.util.logging.Logger;

import static utils.Logger.LOGGER;

public class ChallengeService
{
    private final Map<Challenge, ScheduledFuture<?>> challenges = new ConcurrentHashMap<>();
    private final ScheduledExecutorService scheduler = Executors.newScheduledThreadPool(4);

    public void createChallenge(Challenge challenge, Runnable onExpiry) {
        var challenged = challenge.getChallenged();
        var challenger = challenge.getChallenger();

        var future = scheduler.schedule(() -> {
            onExpiry.run();
            challenges.remove(new Challenge(challenged, challenger));
            LOGGER.info("Challenge expired " + challenged.getId() + " " + challenger.getId());
        }, 30, TimeUnit.SECONDS);

        challenges.put(new Challenge(challenged, challenger), future);
    }

    public boolean acceptChallenge(Challenge challenge) {
        var challenged = challenge.getChallenged();
        var challenger = challenge.getChallenger();

        var future = challenges.remove(new Challenge(challenged, challenger));
        if (future != null) {
            future.cancel(false);
            return true;
        }
        return false;
    }
}