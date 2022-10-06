SET MODE MySQL;

DROP TABLE IF EXISTS genres;

CREATE TABLE IF NOT EXISTS holidays
(
    month_num INTEGER NOT NULL,
    day_num INTEGER NOT NULL,
    PRIMARY KEY (month_num, day_num),
    CHECK (month_num >= 1 AND month_num <= 12),
    CHECK (day_num >= 1 AND day_num <= 31)
);