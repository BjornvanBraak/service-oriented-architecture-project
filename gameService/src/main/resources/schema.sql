CREATE TABLE game (
  id INT AUTO_INCREMENT PRIMARY KEY,
  home_team VARCHAR(250) NOT NULL,
  away_team VARCHAR(250) NOT NULL,
  start_time DATETIME NOT NULL,
  league VARCHAR(250) NOT NULL
);

CREATE TABLE game_result (
  id INT AUTO_INCREMENT PRIMARY KEY,
  goals_home_team INT NOT NULL,
  goals_away_team INT NOT NULL,
  game_id INT,
  FOREIGN KEY (game_id) REFERENCES game (id)
);

