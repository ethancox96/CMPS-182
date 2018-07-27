SELECT d.LicenseID, SUM(t.FEE) AS TicketFeeSum, MAX(t.Fee) AS TicketFeeMax
FROM MultiTicketedDrivers d, Tickets t
WHERE d.LICENSEID = t.LICENSEID
GROUP BY d.LicenseID;

--  licenseid | ticketfeesum | ticketfeemax 
-- -----------+--------------+--------------
--      10003 |       477.27 |       348.63
--      10008 |      2061.73 |      1004.73
--      10004 |       197.36 |       123.18
--      10001 |        94.28 |        47.14
--      10006 |       488.08 |       322.44

DELETE FROM TICKETS
WHERE TicketID = 3000013;

DELETE FROM TICKETS
WHERE TicketID = 3000016;

-- licenseid | ticketfeesum | ticketfeemax 
-- ----------+--------------+--------------
--     10003 |       477.27 |       348.63
--     10008 |      1057.00 |       623.18
--     10004 |       197.36 |       123.18
--     10006 |       488.08 |       322.44
