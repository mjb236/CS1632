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
	ties: u32
}

// add a win to the player Statistics
fn add_player_win(mut p_stats: &mut Statistics) {
	p_stats.wins += 1;
}

// add a loss to the player Statistics
fn add_player_loss(mut p_stats: &mut Statistics) {
	p_stats.losses += 1;
}

// add a loss to the player Statistics
fn add_player_tie(mut p_stats: &mut Statistics) {
	p_stats.ties += 1;
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
fn get_cpu_choice() -> Choice {
	let roll = rand::thread_rng().gen_range(0, 3);
	match roll {
		0 => Choice::Rock,
		1 => Choice::Paper,
		2 => Choice::Scissors,
		_ => Choice::Invalid
	}
}

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

fn main() {
	let mut stats: Statistics = Statistics { wins: 0, losses: 0, ties: 0 };

	// run the game until the player chooses to quit
	loop {
		// get the player's choice
		let player_choice = get_player_choice();
		match player_choice {
			Choice::Invalid => continue, 	//next loop iteration
			Choice::Quit => break,				//exit game
			_ => {}												//valid choice
		}

		let cpu_choice = get_cpu_choice();
		match compare(player_choice, cpu_choice) {
			Outcome::PlayerWin => add_player_win(&mut stats),
			Outcome::PlayerLoss => add_player_loss(&mut stats),
			Outcome::Tie => add_player_tie(&mut stats),
			Outcome::Invalid => {}
		}

	}

	println!("END");
}
