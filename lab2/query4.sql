SELECT t.ticketID, v.Model, v.Year, t.FEE              
FROM Vehicles v, (SELECT t.vin, t.ticketID, t.FEE 
		  FROM Tickets t 
		  WHERE t.fee > 50) t
WHERE t.vin = v.vin AND v.InsuranceCo = 'GEICO';
