CREATE VIEW MultiTicketedDrivers AS
	SELECT d.LicenseID, d.Name, d.Address                    
	FROM Drivers d, Tickets t
	WHERE d.LicenseID = t.LicenseID 
	GROUP BY d.LicenseID 
	HAVING COUNT(d.LicenseID) > 1;

