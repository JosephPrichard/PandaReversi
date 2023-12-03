/*
 * Copyright (c) Joseph Prichard 2023.
 */

package services.game;

import othello.Move;
import othello.OthelloAgent;

import java.util.List;
import java.util.concurrent.ExecutorService;

import static utils.Logger.LOGGER;

public class AgentEvaluator implements GameEvaluator {
    private final ExecutorService executorService;

    public AgentEvaluator(ExecutorService executorService) {
        this.executorService = executorService;
    }

    public void findRankedMoves(EvalRequest<List<Move>> evalRequest) {
        executorService.submit(() -> {
            var agent = new OthelloAgent(evalRequest.getDepth());
            var moves = agent.findRankedMoves(evalRequest.getGame().board());
            evalRequest.onComplete(moves);
        });
        LOGGER.info("Started agent ranked moves calculation of depth " + evalRequest.getDepth());
    }

    public void findBestMove(EvalRequest<Move> evalRequest) {
        executorService.submit(() -> {
            var agent = new OthelloAgent(evalRequest.getDepth());
            var move = agent.findBestMove(evalRequest.getGame().board());
            evalRequest.onComplete(move);
        });
        LOGGER.info("Started agent best move calculation of depth " + evalRequest.getDepth());
    }
}