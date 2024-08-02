/*
 * Copyright (c) Joseph Prichard 2023.
 */

package services.stats;

import services.game.GameResult;
import services.player.Player;

import java.util.List;

public interface IStatsService {

    StatsResult writeStats(GameResult result);

    Stats readStats(Player player);

    List<Stats> readTopStats();
}
