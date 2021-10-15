(function()
{
	"use strict";

	let playerChoiceImage;
	let computerChoiceImage;

	let gameDescriptionLabel;

	let gameRockChoice;
	let gamePaperChoice;
	let gameScissorsChoice;

	let gameConfirmButton;

	let game;

	document.addEventListener("DOMContentLoaded", start);

	function start()
	{
		playerChoiceImage = document.getElementById("player-choice-image");
		computerChoiceImage = document.getElementById("computer-choice-image");

		gameDescriptionLabel = document.getElementById("game-description");

		gameRockChoice = document.getElementById("game-rock-choice");
		gamePaperChoice = document.getElementById("game-paper-choice");
		gameScissorsChoice = document.getElementById("game-scissors-choice");

		gameConfirmButton = document.getElementById("game-confirm-button");

		game = new KRPS.GameManager();

		game.setResetCallback(gameResetHandler);
		game.setGameFinishedCallback(gameFinishedHandler);

		gameRockChoice.addEventListener("click", function()
		{
			if (game.isWaitingForComputer())
			{
				return;
			}

			game.chooseRock();

			gameRockChoice.classList.add("game__choice-button--selected");
			gamePaperChoice.classList.remove("game__choice-button--selected");
			gameScissorsChoice.classList.remove("game__choice-button--selected");

			playerChoiceImage.src = "/resources/hand-rock.svg";
			playerChoiceImage.alt = "Rock chosen.";

			gameConfirmButton.disabled = false;
		});

		gamePaperChoice.addEventListener("click", function()
		{
			if (game.isWaitingForComputer())
			{
				return;
			}

			game.choosePaper();

			gameRockChoice.classList.remove("game__choice-button--selected");
			gamePaperChoice.classList.add("game__choice-button--selected");
			gameScissorsChoice.classList.remove("game__choice-button--selected");

			playerChoiceImage.src = "/resources/hand-paper.svg";
			playerChoiceImage.alt = "Paper chosen.";

			gameConfirmButton.disabled = false;
		});

		gameScissorsChoice.addEventListener("click", function()
		{
			if (game.isWaitingForComputer())
			{
				return;
			}

			game.chooseScissors();

			gameRockChoice.classList.remove("game__choice-button--selected");
			gamePaperChoice.classList.remove("game__choice-button--selected");
			gameScissorsChoice.classList.add("game__choice-button--selected");

			playerChoiceImage.src = "/resources/hand-scissors.svg";
			playerChoiceImage.alt = "Scissors chosen.";

			gameConfirmButton.disabled = false;
		});

		gameConfirmButton.addEventListener("click", function()
		{
			gameConfirmButton.disabled = true;
			gameDescriptionLabel.innerHTML = "...";

			game.play();
		});
	}

	function gameResetHandler()
	{
		playerChoiceImage.src = "/resources/question.svg";
		playerChoiceImage.alt = "Unknown choice.";

		computerChoiceImage.src = "/resources/question.svg";
		computerChoiceImage.alt = "Unknown choice.";

		gameDescriptionLabel.innerHTML = "Choose your move, and confirm it.";

		gameRockChoice.classList.remove("game__choice-button--selected");
		gamePaperChoice.classList.remove("game__choice-button--selected");
		gameScissorsChoice.classList.remove("game__choice-button--selected");

		gameConfirmButton.disabled = true;
	}

	function gameFinishedHandler()
	{
		let computerChoice = game.getComputerChoice();

		switch (computerChoice)
		{
			case "rock":
			computerChoiceImage.src = "/resources/hand-rock.svg";
			computerChoiceImage.alt = "Rock chosen.";
			break;

			case "paper":
			computerChoiceImage.src = "/resources/hand-paper.svg";
			computerChoiceImage.alt = "Paper chosen.";
			break;

			case "scissors":
			computerChoiceImage.src = "/resources/hand-scissors.svg";
			computerChoiceImage.alt = "Scissors chosen.";
			break;
		}

		let result = game.getResult();

		switch (result)
		{
			case "win":
			gameDescriptionLabel.innerHTML = "You win!";
			break;

			case "loss":
			gameDescriptionLabel.innerHTML = "You lose!";
			break;

			case "draw":
			gameDescriptionLabel.innerHTML = "Draw!";
			break;
		}

		gameConfirmButton.disabled = false;
	}
})();