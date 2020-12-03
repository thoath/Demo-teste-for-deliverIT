CREATE TABLE INVOICE (
    id bigserial NOT NULL,
	name varchar(255) NULL,
	amount DECIMAL NULL,
	due_date timestamp NULL,
	payment_date timestamp NULL,
	CONSTRAINT invoice_pkey PRIMARY KEY (id)
);

CREATE TABLE PAYMENT_RULE (
    id bigserial NOT NULL,
	delay_in_days int8 NOT NULL,
	penalty DECIMAL NULL,
	interest_day DECIMAL NULL,
	CONSTRAINT payment_rules_pkey PRIMARY KEY (id)
);