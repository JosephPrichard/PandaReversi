/*
 * Copyright (c) Joseph Prichard 2023.
 */

package services.game;

import services.player.Player;

public class GameResult {

    private final Player winner;
    private final Player loser;
    private final boolean isDraw;
    private float eloOne = 0f;
    private float eloTwo = 0f;
    private float eloDiffOne = 0f;
    private float eloDiffTwo = 0f;

    public static GameResult Draw(Player playerOne, Player playerTwo) {
        return new GameResult(playerOne, playerTwo, true);
    }

    public static GameResult WinLoss(Player winner, Player loser) {
        return new GameResult(winner, loser, false);
    }

    public GameResult(Player winner, Player loser, boolean isDraw) {
        this.winner = winner;
        this.loser = loser;
        this.isDraw = isDraw;
    }

    public boolean isDraw() {
        return isDraw;
    }

    public Player getWinner() {
        return winner;
    }

    public Player getLoser() {
        return loser;
    }

    public void setElo(float eloWinner, float eloLoser) {
        this.eloOne = eloWinner;
        this.eloTwo = eloLoser;
    }

    public float getWinnerElo() {
        return eloOne;
    }

    public float getLoserElo() {
        return eloTwo;
    }

    public float getWinnerEloDiff() {
        return eloDiffOne;
    }

    public float getLoserEloDiff() {
        return eloDiffTwo;
    }

    public void setEloDiff(float diffWinner, float diffLoser) {
        this.eloDiffOne = diffWinner;
        this.eloDiffTwo = diffLoser;
    }

    private static String formatElo(float elo) {
        return elo >= 0 ? "+" + elo : Float.toString(elo);
    }

    public String formatWinnerDiffElo() {
        return formatElo(eloDiffOne);
    }

    public String formatLoserDiffElo() {
        return formatElo(eloDiffTwo);
    }

    @Override
    public String toString() {
        return "GameResult{" +
            "winner=" + winner +
            ", loser=" + loser +
            ", isDraw=" + isDraw +
            ", eloOne=" + eloOne +
            ", eloTwo=" + eloTwo +
            ", eloDiffOne=" + eloDiffOne +
            ", eloDiffTwo=" + eloDiffTwo +
            '}';
    }
}
