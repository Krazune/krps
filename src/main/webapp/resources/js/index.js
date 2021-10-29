(function()
{
	"use strict";

	let _playerChoiceImage;
	let _computerChoiceImage;

	let _gameDescriptionLabel;

	let _gameRockChoice;
	let _gamePaperChoice;
	let _gameScissorsChoice;

	let _gameConfirmButton;

	let _game;

	document.addEventListener("DOMContentLoaded", start);

	function start()
	{
		_playerChoiceImage = document.getElementById("player-choice-image");
		_computerChoiceImage = document.getElementById("computer-choice-image");

		_gameDescriptionLabel = document.getElementById("game-description");

		_gameRockChoice = document.getElementById("game-rock-choice");
		_gamePaperChoice = document.getElementById("game-paper-choice");
		_gameScissorsChoice = document.getElementById("game-scissors-choice");

		_gameConfirmButton = document.getElementById("game-confirm-button");

		_game = new KRPS.GameManager();

		_game.setResetCallback(gameResetHandler);
		_game.setGameFinishedCallback(gameFinishedHandler);

		_gameRockChoice.addEventListener("click", function()
		{
			if (_game.isWaitingForComputer())
			{
				return;
			}

			_game.chooseRock();

			_gameRockChoice.classList.add("game__choice-button--selected");
			_gamePaperChoice.classList.remove("game__choice-button--selected");
			_gameScissorsChoice.classList.remove("game__choice-button--selected");

			_playerChoiceImage.src = "/resources/images/hand-rock.svg";
			_playerChoiceImage.alt = "Rock chosen.";

			_gameConfirmButton.disabled = false;
		});

		_gamePaperChoice.addEventListener("click", function()
		{
			if (_game.isWaitingForComputer())
			{
				return;
			}

			_game.choosePaper();

			_gameRockChoice.classList.remove("game__choice-button--selected");
			_gamePaperChoice.classList.add("game__choice-button--selected");
			_gameScissorsChoice.classList.remove("game__choice-button--selected");

			_playerChoiceImage.src = "/resources/images/hand-paper.svg";
			_playerChoiceImage.alt = "Paper chosen.";

			_gameConfirmButton.disabled = false;
		});

		_gameScissorsChoice.addEventListener("click", function()
		{
			if (_game.isWaitingForComputer())
			{
				return;
			}

			_game.chooseScissors();

			_gameRockChoice.classList.remove("game__choice-button--selected");
			_gamePaperChoice.classList.remove("game__choice-button--selected");
			_gameScissorsChoice.classList.add("game__choice-button--selected");

			_playerChoiceImage.src = "/resources/images/hand-scissors.svg";
			_playerChoiceImage.alt = "Scissors chosen.";

			_gameConfirmButton.disabled = false;
		});

		_gameConfirmButton.addEventListener("click", function()
		{
			_gameConfirmButton.disabled = true;
			_gameDescriptionLabel.innerHTML = "...";

			_game.play();
		});
	}

	function gameResetHandler()
	{
		_playerChoiceImage.src = "/resources/images/question.svg";
		_playerChoiceImage.alt = "Unknown choice.";

		_computerChoiceImage.src = "/resources/images/question.svg";
		_computerChoiceImage.alt = "Unknown choice.";

		_gameDescriptionLabel.innerHTML = "Choose your move, and confirm it.";

		_gameRockChoice.classList.remove("game__choice-button--selected");
		_gamePaperChoice.classList.remove("game__choice-button--selected");
		_gameScissorsChoice.classList.remove("game__choice-button--selected");

		_gameConfirmButton.disabled = true;
	}

	function gameFinishedHandler()
	{
		let computerChoice = _game.getComputerChoice();

		switch (computerChoice)
		{
			case "rock":
			_computerChoiceImage.src = "/resources/images/hand-rock.svg";
			_computerChoiceImage.alt = "Rock chosen.";
			break;

			case "paper":
			_computerChoiceImage.src = "/resources/images/hand-paper.svg";
			_computerChoiceImage.alt = "Paper chosen.";
			break;

			case "scissors":
			_computerChoiceImage.src = "/resources/images/hand-scissors.svg";
			_computerChoiceImage.alt = "Scissors chosen.";
			break;
		}

		let result = _game.getResult();

		switch (result)
		{
			case "win":
			_gameDescriptionLabel.innerHTML = "You win!";
			break;

			case "loss":
			_gameDescriptionLabel.innerHTML = "You lose!";
			break;

			case "draw":
			_gameDescriptionLabel.innerHTML = "Draw!";
			break;
		}

		_gameConfirmButton.disabled = false;
	}
})();