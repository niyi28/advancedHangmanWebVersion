
const createGame = async () => {
    var _playerName = document.getElementById('playerName').value;
    var _language = document.getElementById('language').value;
    const response = await fetch(
        "/createGame?playerName="+_playerName+"&language="+_language);
    const isOk = await response.text();
    if (isOk !== "OK") {
        alert('Could not create the game, try again!');
        return;
    }
    // redirect the player to the game page
    window.location.href = "/game.html";
}

const getGameStatus = async () => {
    const response = await fetch("/getGameStatus");
    const textResponse = await response.text();
    var gameStatus = textResponse.split(',');
    var playerName = gameStatus[0];
    document.getElementById('welcomeMessage').innerHTML = "<h2>Welcome "+playerName+"</h2>"
    var wordSize = parseInt(gameStatus[1]);
    var word = "";
    for(var i=0; i<wordSize; i++) {
        word += "_ ";
    }
    document.getElementById('hiddenWord').innerHTML = word;
}