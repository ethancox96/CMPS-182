CREATE TABLE Drivers(
        LicenseID INTEGER,
        Name VARCHAR(30) NOT NULL,
        Address VARCHAR(40),
        BirthDate DATE,
        EyeColor CHAR(3),
        HairColor CHAR(3),
        LicenseExpireDate DATE,
        LicenseClass CHAR(1),
	PRIMARY KEY(LicenseID, Name, Address)
);

CREATE TABLE Vehicles(
        VIN INTEGER PRIMARY KEY,
        Model VARCHAR(30) NOT NULL,
        Year INTEGER NOT NULL,
        VehicleColor CHAR(3),
        OwnerLicenseID INTEGER NOT NULL,
        RegExpireDate DATE,
        InsuranceCo VARCHAR(30),
        InUse BOOLEAN
);

CREATE TABLE Tickets(
        TicketID INTEGER PRIMARY KEY,
        VIN INTEGER NOT NULL,
        LicenseID INTEGER,
        TicketDate DATE NOT NULL,
        Address VARCHAR(40),
        Infraction VARCHAR(30),
        Fee DECIMAL(6,2),
        Paid BOOLEAN
);
