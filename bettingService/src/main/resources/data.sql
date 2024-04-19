INSERT INTO product (product_id,name,description,price) VALUES
(100,'TV','Idiot Box',2000.00),
(101,'Telescope','Helps you see aliens',500.00),
(102,'Smart Phone','For smart people',800.00),
(103,'Washing machine','Clean your clothes',1000.00),
(104,'Watch','Shows time',300.00),
(104,'Costly Watch','Shows time and your social status',5300.00),
(105,'Costly Smart Phone','For smart and wealthy people',2800.00);

INSERT INTO bet (bet_value_creator, bet_value_taker, bet_on_home_team_creator, bet_status, payout_creator, payout_taker, bet_creator_id, bet_taker_id, game_id) VALUES
(2.0, 5.0, TRUE, 'BET_PLACEMENT_VERIFYING_CREATOR_AND_GAME', 4.0, 10.0, 1, 1, 1),
(2.0, 5.0, TRUE, 'BET_PLACEMENT_VERIFYING_CREATOR_AND_GAME', 4.0, 10.0, 1, 1, 1),
(2.0, 5.0, TRUE, 'BET_PLACEMENT_VERIFYING_CREATOR_AND_GAME', 4.0, 10.0, 1, 1, 1);
