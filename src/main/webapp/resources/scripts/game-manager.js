(function(KRPS)
{
	"use strict"

	KRPS.GameManager = (function()
	{
		function GameManager()
		{
			this._userChoice = "";
			this._computerChoice = "";
			this._result = "";

			this._isWaitingForComputer = false;

			this._resetCallback = null;
			this._gameFinishedCallBack = null;
		}

		GameManager.prototype.chooseRock = function()
		{
			if (this._result !== "")
			{
				this.reset();
			}

			this._userChoice = "r";
		};

		GameManager.prototype.choosePaper = function()
		{
			if (this._result !== "")
			{
				this.reset();
			}

			this._userChoice = "p";
		};

		GameManager.prototype.chooseScissors = function()
		{
			if (this._result !== "")
			{
				this.reset();
			}

			this._userChoice = "s";
		};

		GameManager.prototype.getUserChoice = function()
		{
			return this._userChoice;
		};

		GameManager.prototype.getComputerChoice = function()
		{
			return this._computerChoice;
		};

		GameManager.prototype.getResult = function()
		{
			return this._result;
		};

		GameManager.prototype.play = function()
		{
			let httpRequest = new XMLHttpRequest();
			let gameManagerThis = this;

			httpRequest.onreadystatechange = function()
			{
				gameManagerThis._playRequestStateChangeHandler(httpRequest);
			};

			httpRequest.open("POST", "/");
			httpRequest.setRequestHeader('Content-Type', 'application/x-www-form-urlencoded');
			httpRequest.send("choice=" + this._userChoice);

			this._isWaitingForComputer = true;
		};

		GameManager.prototype.isWaitingForComputer = function()
		{
			return this._isWaitingForComputer;
		}

		GameManager.prototype.reset = function()
		{
			this._userChoice = "";
			this._computerChoice = "";
			this._result = "";

			if (this._resetCallback !== null)
			{
				this._resetCallback();
			}
		};

		GameManager.prototype.setResetCallback = function(callback)
		{
			this._resetCallback = callback;
		};

		GameManager.prototype.setGameFinishedCallback = function(callback)
		{
			this._gameFinishedCallBack = callback;
		};

		GameManager.prototype._playRequestStateChangeHandler = function(httpRequest)
		{
			if (httpRequest.readyState === XMLHttpRequest.DONE)
			{
				if (httpRequest.status === 200)
				{
					let gameSummary = null;

					try
					{
						gameSummary = JSON.parse(httpRequest.responseText);
					}
					catch (e)
					{
						alert('Error.');

						this.reset();

						return;
					}

					this._computerChoice = gameSummary.computerChoice;
					this._result = gameSummary.outcome;

					if (this._gameFinishedCallBack !== null)
					{
						this._gameFinishedCallBack();
					}

					this._isWaitingForComputer = false;
				}
				else
				{
					alert('Error.');

					this.reset();
				}
			}
		};

		return GameManager;
	})();
}
)(window.KRPS = window.KRPS || {});