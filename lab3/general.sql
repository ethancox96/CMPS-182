ALTER TABLE DRIVERS 
	ADD CONSTRAINT LC_CHECK 
		CHECK (LicenseClass IN ('A','B','C','M'));

ALTER TABLE TICKETS 
	ADD CONSTRAINT positive_fee 
		CHECK (Fee > 0);

ALTER TABLE VEHICLES 
	ADD CONSTRAINT in_use 
		CHECK((InUse != TRUE) OR (RegExpireDate IS NOT NULL));