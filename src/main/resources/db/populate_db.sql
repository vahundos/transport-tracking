DELETE FROM events;
DELETE FROM zones;
DELETE FROM transport;

INSERT INTO zones (latitude, longitude, radius) VALUES
  (49.234354, 28.431592, 600),
  (49.239009, 28.493636, 300);

INSERT INTO transport (latitude, longitude) VALUES
  (49.234013, 28.441802),
  (49.036024, 28.651700),
  (49.234354, 28.431592);
