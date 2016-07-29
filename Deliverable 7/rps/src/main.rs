// Rock-Paper-Scissors
// Michael Bowen - CS1632

extern crate rand;

use std::io;

use rand::Rng;

// all possible choices for playing the game
enum Choice {
	Rock,
	Paper,
	Scissors,
	Quit,
	Invalid
}

// possible outcomes of the game
enum Outcome {
	PlayerWin,
	PlayerLoss,
	Tie,
	Invalid
}

// store some statistics
struct Statistics {
	wins: u32,
	losses: u32,
	ties: u32,
	rocks: u32,
	papers: u32,
	scissors: u32
}

// add a win to the player Statistics
fn add_player_win(mut p_stats: &mut Statistics) {
	p_stats.wins += 1;
}

// add a loss to the player Statistics
fn add_player_loss(mut p_stats: &mut Statistics) {
	p_stats.losses += 1;
}

// add a tie to the player Statistics
fn add_player_tie(mut p_stats: &mut Statistics) {
	p_stats.ties += 1;
}

// add a rock to the player Statistics
fn add_player_rock(mut p_stats: &mut Statistics) {
	p_stats.rocks += 1;
}

// add a paper to the player Statistics
fn add_player_paper(mut p_stats: &mut Statistics) {
	p_stats.papers += 1;
}

// add a scissors to the player Statistics
fn add_player_scissors(mut p_stats: &mut Statistics) {
	p_stats.scissors += 1;
}

// return the correct choice based on user input
fn get_player_choice() -> Choice {
	println!("Enter choice (r,p,s) or q to quit >");
	let mut choice = String::new();
	io::stdin().read_line(&mut choice).expect("FAIL");
	match choice.trim() {
		"r" => Choice::Rock,
		"p" => Choice::Paper,
		"s" => Choice::Scissors,
		"q" => Choice::Quit,
		_ => Choice::Invalid
	}
}

// use the random number generator to determine the computer's choice
fn get_cpu_choice(cpu_choice: &mut Choice) {
	let roll = rand::thread_rng().gen_range(0, 3);
	match roll {
		0 => *cpu_choice = Choice::Rock,
		1 => *cpu_choice = Choice::Paper,
		2 => *cpu_choice = Choice::Scissors,
		_ => *cpu_choice = Choice::Invalid
	}
}

// compare the player and cpu choices and return the outcome
fn compare(player: Choice, cpu: Choice) -> Outcome{
	match player {
		Choice::Rock => match cpu {
			Choice::Rock => Outcome::Tie,
			Choice::Paper => Outcome::PlayerLoss,
			Choice::Scissors => Outcome::PlayerWin,
			_ => Outcome::Invalid
		},
		Choice::Paper => match cpu {
			Choice::Rock => Outcome::PlayerWin,
			Choice::Paper => Outcome::Tie,
			Choice::Scissors => Outcome::PlayerLoss,
			_ => Outcome::Invalid
		},
		Choice::Scissors => match cpu {
			Choice::Rock => Outcome::PlayerLoss,
			Choice::Paper => Outcome::PlayerWin,
			Choice::Scissors => Outcome::Tie,
			_ => Outcome::Invalid
		},
		_ => Outcome::Invalid	
	}
} 

// print the stats for the end of the game
fn print_stats(p_stats: Statistics) {
	let mut total_games: u32 = p_stats.wins + p_stats.losses + p_stats.ties;
	if total_games == 0 {
		// update total_games to 1 so calculations don't come up as NaN
		// if there weren't any games played, all results will be 0 anyway
		total_games = 1;
	}

	// calculate percentages
	let win_pct: f32 = 100.0 * (p_stats.wins as f32) / (total_games as f32);
	let loss_pct: f32 = 100.0 * (p_stats.losses as f32) / (total_games as f32);
	let tie_pct: f32 = 100.0 * (p_stats.ties as f32) / (total_games as f32);

	// print info
	println!("Player Stats:");
	println!("Wins: {} ({:.2}%)", p_stats.wins, win_pct);
	println!("Ties: {} ({:.2}%)", p_stats.ties, tie_pct);
	println!("Losses: {} ({:.2}%)", p_stats.losses, loss_pct);
	println!("Rocks: {}", p_stats.rocks);
	println!("Papers: {}", p_stats.papers);
	println!("Scissors: {}", p_stats.scissors);
}

fn main() {
	let mut stats: Statistics = Statistics { wins: 0, losses: 0, ties: 0, rocks: 0, papers: 0, scissors: 0 };

	// run the game until the player chooses to quit
	loop {
		// get the player's choice
		let player_choice = get_player_choice();
		match player_choice {
			Choice::Invalid => {
				println!("Please enter r, p, s, or q!!!!!");
				continue;										//next loop iteration
			}, 	
			Choice::Quit => break,				//exit game
			_ => {}												//valid choice
		}

		// get cpu choice
		let mut cpu_choice = Choice::Invalid;
		get_cpu_choice(&mut cpu_choice);
		
		// print out the choices and update player stats
		match player_choice {
			Choice::Rock => {
				println!("Player chose: Rock");
				add_player_rock(&mut stats);
			},
			Choice::Paper => {
				println!("Player chose: Paper");
				add_player_paper(&mut stats);
			},
			Choice::Scissors => {
				println!("Player chose: Scissors");
				add_player_scissors(&mut stats);
			},
			_ => {}
		}
		match cpu_choice {
			Choice::Rock => println!("Opponent chose: Rock"),
			Choice::Paper => println!("Opponent chose: Paper"),
			Choice::Scissors => println!("Opponent chose: Scissors"),
			_ => {}
		}

		// print the outcome and update stats
		match compare(player_choice, cpu_choice) {
			Outcome::PlayerWin => {
				add_player_win(&mut stats);
				println!("You win!");
			},
			Outcome::PlayerLoss => {
				add_player_loss(&mut stats);
				println!("You lose!");
			},
			Outcome::Tie => {
				add_player_tie(&mut stats);
				println!("It's a tie!");
			},
			Outcome::Invalid => {}
		}
	} //end game loop

	print_stats(stats);
}