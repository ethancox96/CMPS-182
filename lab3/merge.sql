UPDATE TICKETS
SET Paid = TRUE
WHERE TicketID IN (SELECT tp.TicketID
		   FROM TicketPayments tp, Tickets t
		   WHERE t.FEE = tp.Amount);
