SELECT t.TicketID, T.TicketDate, T.Infraction, d.name AS TicketedDriverName
FROM TIckets t, Vehicles v, Drivers d
WHERE t.LicenseID != v.OwnerLicenseID AND t.VIN = v.VIN;
