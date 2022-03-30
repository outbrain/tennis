package com.outbrain.tennis;

import java.util.Arrays;
import java.util.Collection;

public class TennisTest {

  private static final Collection<TennisScore> SCORES = Arrays.asList(
    new TennisScore( 0, 0, "Love-All" ),
    new TennisScore(1, 1, "Fifteen-All" ),
    new TennisScore(2, 2, "Thirty-All"),
    new TennisScore(3, 3, "Deuce"),
    new TennisScore(4, 4, "Deuce"),

    new TennisScore(1, 0, "Fifteen-Love"),
    new TennisScore(0, 1, "Love-Fifteen"),
    new TennisScore(2, 0, "Thirty-Love"),
    new TennisScore(0, 2, "Love-Thirty"),
    new TennisScore(3, 0, "Forty-Love"),
    new TennisScore(0, 3, "Love-Forty"),
    new TennisScore(4, 0, "Win for player1"),
    new TennisScore(0, 4, "Win for player2"),

    new TennisScore(2, 1, "Thirty-Fifteen"),
    new TennisScore(1, 2, "Fifteen-Thirty"),
    new TennisScore(3, 1, "Forty-Fifteen"),
    new TennisScore(1, 3, "Fifteen-Forty"),
    new TennisScore(4, 1, "Win for player1"),
    new TennisScore(1, 4, "Win for player2"),

    new TennisScore(3, 2, "Forty-Thirty"),
    new TennisScore(2, 3, "Thirty-Forty"),
    new TennisScore(4, 2, "Win for player1"),
    new TennisScore(2, 4, "Win for player2"),

    new TennisScore(4, 3, "Advantage player1"),
    new TennisScore(3, 4, "Advantage player2"),
    new TennisScore(5, 4, "Advantage player1"),
    new TennisScore(4, 5, "Advantage player2"),
    new TennisScore(15, 14, "Advantage player1"),
    new TennisScore(14, 15, "Advantage player2"),

    new TennisScore(6, 4, "Win for player1"),
    new TennisScore(4, 6, "Win for player2"),
    new TennisScore(16, 14, "Win for player1"),
    new TennisScore(14, 16, "Win for player2")
  );

  private int player1Score;
  private int player2Score;
  private String expectedScore;

  public static void main(String[] args) {
    for (TennisScore score : SCORES) {
      new TennisTest(score.player1Score, score.player2Score, score.expectedScore).checkAllScores(score);
    }
  }
  
  public TennisTest(int player1Score, int player2Score, String expectedScore) {
    this.player1Score = player1Score;
    this.player2Score = player2Score;
    this.expectedScore = expectedScore;
  }

  private void checkAllScores(final TennisScore score) {
    checkAllScores(score, new TennisGame1("player1", "player2"));
  }

  public void checkAllScores(TennisScore score, TennisGame game) {
    int highestScore = Math.max(this.player1Score, this.player2Score);
    for (int i = 0; i < highestScore; i++) {
      if (i < this.player1Score)
        game.wonPoint("player1");
      if (i < this.player2Score)
        game.wonPoint("player2");
    }
    if (expectedScore.equals(game.getScore())) {
      System.out.println(score + " SUCCESS!");
    } else {
      throw new RuntimeException(score + " but got: " + game.getScore());
    }
  }

  private static class TennisScore {
    private final int player1Score;
    private final int player2Score;
    private final String expectedScore;

    public TennisScore(int player1Score, int player2Score, String expectedScore) {
      this.player1Score = player1Score;
      this.player2Score = player2Score;
      this.expectedScore = expectedScore;
    }

    @Override
    public String toString() {
      return "" +
        "player1Score=" + player1Score +
        ", player2Score=" + player2Score +
        ", expectedScore='" + expectedScore +"'";
    }
  }

}