(function()
{
	"use strict";

	let _userChoiceImage;
	let _computerChoiceImage;

	let _gameStatusLabel;

	let _gameChoiceRock;
	let _gameChoicePaper;
	let _gameChoiceScissors;

	let _gameConfirmButton;

	let _game;

	document.addEventListener("DOMContentLoaded", _start);

	function _start()
	{
		_userChoiceImage = document.getElementById("user-choice-image");
		_computerChoiceImage = document.getElementById("computer-choice-image");

		_gameStatusLabel = document.getElementById("game-status");

		_gameChoiceRock = document.getElementById("game-choice-rock");
		_gameChoicePaper = document.getElementById("game-choice-paper");
		_gameChoiceScissors = document.getElementById("game-choice-scissors");

		_gameConfirmButton = document.getElementById("game-confirm-button");

		_game = new KRPS.GameManager();

		_game.setResetCallback(_gameResetHandler);
		_game.setGameFinishedCallback(_gameFinishedHandler);

		_gameChoiceRock.addEventListener("click", function()
		{
			if (_game.isWaitingForComputer())
			{
				return;
			}

			_game.chooseRock();

			_gameChoiceRock.classList.add("game__choice-button--selected");
			_gameChoicePaper.classList.remove("game__choice-button--selected");
			_gameChoiceScissors.classList.remove("game__choice-button--selected");

			_userChoiceImage.src = "/resources/images/hand-rock.svg";
			_userChoiceImage.alt = "Rock chosen.";

			_gameConfirmButton.disabled = false;
		});

		_gameChoicePaper.addEventListener("click", function()
		{
			if (_game.isWaitingForComputer())
			{
				return;
			}

			_game.choosePaper();

			_gameChoiceRock.classList.remove("game__choice-button--selected");
			_gameChoicePaper.classList.add("game__choice-button--selected");
			_gameChoiceScissors.classList.remove("game__choice-button--selected");

			_userChoiceImage.src = "/resources/images/hand-paper.svg";
			_userChoiceImage.alt = "Paper chosen.";

			_gameConfirmButton.disabled = false;
		});

		_gameChoiceScissors.addEventListener("click", function()
		{
			if (_game.isWaitingForComputer())
			{
				return;
			}

			_game.chooseScissors();

			_gameChoiceRock.classList.remove("game__choice-button--selected");
			_gameChoicePaper.classList.remove("game__choice-button--selected");
			_gameChoiceScissors.classList.add("game__choice-button--selected");

			_userChoiceImage.src = "/resources/images/hand-scissors.svg";
			_userChoiceImage.alt = "Scissors chosen.";

			_gameConfirmButton.disabled = false;
		});

		_gameConfirmButton.addEventListener("click", function()
		{
			_gameConfirmButton.disabled = true;
			_gameStatusLabel.innerHTML = "...";

			_game.play();
		});
	}

	function _gameResetHandler()
	{
		_userChoiceImage.src = "/resources/images/question.svg";
		_userChoiceImage.alt = "Unknown choice.";

		_computerChoiceImage.src = "/resources/images/question.svg";
		_computerChoiceImage.alt = "Unknown choice.";

		_gameStatusLabel.innerHTML = "Choose your move, and confirm it.";

		_gameChoiceRock.classList.remove("game__choice-button--selected");
		_gameChoicePaper.classList.remove("game__choice-button--selected");
		_gameChoiceScissors.classList.remove("game__choice-button--selected");

		_gameConfirmButton.disabled = true;
	}

	function _gameFinishedHandler()
	{
		let computerChoice = _game.getComputerChoice();

		switch (computerChoice)
		{
			case "r":
			_computerChoiceImage.src = "/resources/images/hand-rock.svg";
			_computerChoiceImage.alt = "Rock chosen.";
			break;

			case "p":
			_computerChoiceImage.src = "/resources/images/hand-paper.svg";
			_computerChoiceImage.alt = "Paper chosen.";
			break;

			case "s":
			_computerChoiceImage.src = "/resources/images/hand-scissors.svg";
			_computerChoiceImage.alt = "Scissors chosen.";
			break;
		}

		let result = _game.getResult();

		switch (result)
		{
			case "w":
			_gameStatusLabel.innerHTML = "You win!";
			break;

			case "l":
			_gameStatusLabel.innerHTML = "You lose!";
			break;

			case "d":
			_gameStatusLabel.innerHTML = "Draw!";
			break;
		}

		_gameConfirmButton.disabled = false;
	}
})();