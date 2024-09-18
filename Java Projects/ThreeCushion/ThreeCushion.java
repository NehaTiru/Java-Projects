package hw2;
/**
 * Three Cushions game
 */

import api.PlayerPosition;
import api.BallType;
import static api.PlayerPosition.*;
import static api.BallType.*;


/**
 * Class that models the game of three-cushion billiards.
 * @author neha2004
 *
 */
public class ThreeCushion {
	/**
	 * winner 
	 */

	private PlayerPosition lag_Winner;
	/**
	 * Looser
	 */
	private PlayerPosition not_lag_Winner;
	/**
	 * Number of points needed to win
	 */
	private int points_To_Win;
	/**
	 * player A score
	 */
	private static int player_A_Score;
	/**
	 * Player B score
	 */
	private static int player_B_Score;
	/**
	 * Bank shot 
	 */
	private static boolean bank_Shot;
	/**
	 * Game ends
	 */
	private static boolean game_Over;
	/**
	 * begining of innings
	 */
	private static boolean inning_Started;
	/**
	 * Shot started
	 */
	private static boolean shot_Started;
	/**
	 * 	Inning number

	 */
	private static int num_inning;
	/**
	 * Who is playing now
	 */
	private static PlayerPosition current_Inning_Player;
	/**
	 * Current ball
	 */
	private static BallType cue_Ball;
	/**
	 * Break shot
	 */
	private static boolean break_Shot;
	/**
	 * Self foul
	 */
	private static boolean self_Break;
	private static BallType struck_Ball;
	/**
	 * Inning start
	 * 
	 */
	
	private boolean break_Decision;
	/**
	 * Winners cue ball
	 * 
	 */
	private BallType lag_Winner_CueBall;
	/**
	 * Opponents cue ball
	 */
	private BallType not_lag_Winner_CueBall;
	/**
	 * Cushion bounce
	 */
	private boolean cushion_Bounce;
	/**
	 * Stick strike
	 */
	private boolean stick_Strike;
	/**
	 * Present ball
	 */
	private BallType cueBall_HitBall;
	/**
	 * red ball
	 */
	private boolean red_cue_Ball;
	/**
	 * White ball
	 */
	private boolean white_Cue_Ball;
	/**
	 * Yellow ball
	 */
	private boolean yellow_Cue_Ball;
	/**
	 * is the cushion bounce correct
	 */
	private int correct_cushion_Bounce;
	/**
	 * Points scored 
	 */
	private boolean points_scored;
	/**
	 * Bank
	 * 
	 */
	private boolean bank;
	/**
	 * is the stick hits the correct object
	 */
	private boolean correct_Stick_Strike;
	/**
	 * Is it foul or not?
	 */
	private boolean foul_Checker;
	/**
	 * Correct cushion strike
	 */
	private boolean cushion_strike;
	/**
	 * Creates a new game of three-cushion billiards with a given lag winner and the predetermined number of points required to win the game.


	 * @param lagWinner
	 * @param pointsToWin
	 */
 
	public ThreeCushion(PlayerPosition lagWinner, int pointsToWin) {
		this.lag_Winner = lagWinner;
		this.points_To_Win = pointsToWin;
		player_A_Score = 0;
		player_B_Score = 0;
		num_inning = 1;
		bank_Shot = false;
		game_Over = false;
		inning_Started = false;
		shot_Started = false;
		cue_Ball = null;
		correct_cushion_Bounce = 0;
		foul_Checker = false;
		break_Shot = false;
		cushion_strike = false;

	}
	/**
	 *Indicates the given ball has impacted the given cushion.

 
	 */
	public void cueBallImpactCushion() {
		if (game_Over == false) {
			if (shot_Started == true) {
				if ((break_Shot == true) && (num_inning == 1)) {
					foul_Checker = true;
					foul();
				} 
				else {
					cushion_strike = true;
					correct_cushion_Bounce += 1;
					if (correct_cushion_Bounce >= 3) {
						if ((cue_Ball == WHITE) && (yellow_Cue_Ball == false) && (red_cue_Ball == false)) {
							bank_Shot = true;
						} 
						else if ((cue_Ball == YELLOW) && (white_Cue_Ball == false) && (red_cue_Ball == false)) {
							bank_Shot = true;
						}

					}
				}
			}
		}
	}
	/**
	 * Indicates the player's cue ball has struck the given ball.


	 * @param ball
	 */
	
	public void cueBallStrike(BallType ball) {
		if ((stick_Strike == true) && (game_Over != true)) {
			cueBall_HitBall = ball;
			cushion_strike = false;
			if ((num_inning == 1) && (break_Shot == true)) {
				if (ball != RED) {
					foul_Checker = true;
					foul();
					 
				} 
				else {
					break_Shot = false;
					red_cue_Ball = true;
				}
			} 
			else {
				if (cueBall_HitBall == RED) {
					red_cue_Ball = true;
				} 
				else if (cueBall_HitBall == WHITE) {
					white_Cue_Ball = true;
				} 
				else if (cueBall_HitBall == YELLOW) {
					yellow_Cue_Ball = true;
				}
			}
		}

	}
	/**
	 *Indicates the cue stick has struck the given ball.
	 * @param ball
	 */
	public void cueStickStrike(BallType ball) {
		if (game_Over == false) {
			bank_Shot = false;
			if (break_Decision == true) {
				if ((shot_Started == true) || (ball != cue_Ball)) {
					shot_Started = true;
					foul_Checker = true;
					foul();
				} 
				else {
					shot_Started = true;
					inning_Started = true;
					stick_Strike = true;
				}
			}
		}

	}
	/**
	 * Indicates that all balls have stopped motion.

	 */
	public void endShot() {
		if ((shot_Started == true) && (stick_Strike == true) && (game_Over == false)) {
			if ((correct_cushion_Bounce >= 3) && (cushion_strike == false) && (foul_Checker == false)) {
				if (current_Inning_Player == PLAYER_A) {

					if ((cue_Ball == WHITE) && (red_cue_Ball == true) && (yellow_Cue_Ball == true)) {
						player_A_Score += 1;
						points_scored = true;
					}
					if ((cue_Ball == YELLOW) && (white_Cue_Ball == true) && (red_cue_Ball == true)) {
						player_A_Score += 1;
						points_scored = true;
					}
				} 
				else if (current_Inning_Player == PLAYER_B) {
					if ((cue_Ball == WHITE) && (red_cue_Ball == true) && (yellow_Cue_Ball == true)) {
						player_B_Score += 1;
						points_scored = true;
					}
					if ((cue_Ball == YELLOW) && (white_Cue_Ball == true) && (red_cue_Ball == true)) {
						player_B_Score += 1;
						points_scored = true;
					}
				}
			} 
			else if (foul_Checker == false) {

				if (current_Inning_Player == lag_Winner) {
					current_Inning_Player = not_lag_Winner;
					cue_Ball = not_lag_Winner_CueBall;

				} 
				else if (current_Inning_Player == not_lag_Winner) {
					current_Inning_Player = lag_Winner;
					cue_Ball = lag_Winner_CueBall;
				}

				num_inning = num_inning + 1;
				inning_Started = false;
			}
			shot_Started = false;
			break_Shot = false;
		}
		if (points_scored == false)
		{
			bank_Shot = false;
		}
		if ((player_A_Score == points_To_Win) || (player_B_Score == points_To_Win)) {
			game_Over = true;
			inning_Started = false;
		}
		points_scored = false;
		correct_cushion_Bounce = 0;
		foul_Checker = false;
		red_cue_Ball = false;
		white_Cue_Ball = false;
		yellow_Cue_Ball = false;

	}
	/**
	 * A foul immediately ends the player's inning, even if the current shot has not yet ended.

	 */
	public void foul() {

		if ((game_Over == false) && (break_Decision == true)){
			
			inning_Started = false;
			foul_Checker = true;
			if (current_Inning_Player == lag_Winner) {
				current_Inning_Player = not_lag_Winner;
				cue_Ball = not_lag_Winner_CueBall;

			} 
			else if (current_Inning_Player == not_lag_Winner) {
				current_Inning_Player = lag_Winner;
				cue_Ball = lag_Winner_CueBall;
			}
			num_inning ++;
		}
	}
	/**
	 * Gets the cue ball of the current player.

	 * @return
	 */
	public BallType getCueBall() {

		return cue_Ball;

	}
	/**
	 * Gets the inning number.

	 * @return
	 */


	public int getInning() {
		return num_inning;
	}
	/**
	 *  Gets the current player.

	 * @return
	 */
	public PlayerPosition getInningPlayer() {
		return current_Inning_Player;

	}
	/**
	 * Gets the number of points scored by Player A.

	 * @return
	 */
	
	public int getPlayerAScore() {

		return player_A_Score;
	}
	/**
	 *Gets the number of points scored by Player B.
	 * @return
	 */

	public int getPlayerBScore() {

		return player_B_Score;
	}
	/**
	 * Returns true if and only if the most recently completed shot was a bank shot.

	 * @return
	 */
	public boolean isBankShot() {
		return bank_Shot;

	}
	/**
	 * Returns true if and only if this is the break shot (i.e., the first shot of the game).

	 * @return
	 */
	
	public boolean isBreakShot() {

		return break_Shot;

	}
	/**
	 *Returns true if the game is over (i.e., one of the players has reached the designated number of points to win).
 
	 * @return
	 */


	public boolean isGameOver() {
		return game_Over;
	}
	/**
	 * Returns true if the shooting player has taken their first shot of the inning.

	 * @return
	 */


	public boolean isInningStarted() {

		return inning_Started;
	}
	/**
	 * Returns true if a shot has been taken (see cueStickStrike()), but not ended (see endShot()).

	 * @return
	 */

	
	public boolean isShotStarted() {
		return shot_Started;
	}
	/**
	 * Sets whether the player that won the lag chooses to break (take first shot), or chooses the other player to break.

	 * @param selfBreak
	 * @param cueBall
	 */
	
	public void lagWinnerChooses(boolean selfBreak, BallType cueBall) {
		break_Decision = true;
		lag_Winner_CueBall = cueBall;
		break_Shot = true;
		if (lag_Winner == PLAYER_A) {
			not_lag_Winner = PLAYER_B;
		} 
		else {
			not_lag_Winner = PLAYER_A;
		}
		if (lag_Winner_CueBall == YELLOW) {
			not_lag_Winner_CueBall = WHITE;
		} 
		else {
			not_lag_Winner_CueBall = YELLOW;
		}
		if (selfBreak == true) {
			current_Inning_Player = lag_Winner;
			cue_Ball = lag_Winner_CueBall;
		} 
		else {
			current_Inning_Player = not_lag_Winner;
			cue_Ball = not_lag_Winner_CueBall;
		}

	}
	/**
	 * Returns a one-line string representation of the current game state.

	 */

	public String toString() {
		String fmt = "Player A%s: %d, Player B%s: %d, Inning: %d %s%s";
		String playerATurn = "";
		String playerBTurn = "";
		String inningStatus = "";
		String gameStatus = "";
		if (getInningPlayer() == PLAYER_A) {
			playerATurn = "*";
		} 
		else if (getInningPlayer() == PLAYER_B) {
			playerBTurn = "*";
		}
		if (isInningStarted()) {
			inningStatus = "started";
		} 
		else {
			inningStatus = "not started";
		}
		if (isGameOver()) {
			gameStatus = ", game result final";
		}
		return String.format(fmt, playerATurn, getPlayerAScore(), playerBTurn, getPlayerBScore(), getInning(),
				inningStatus, gameStatus);
	}
}