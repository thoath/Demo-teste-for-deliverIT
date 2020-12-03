CREATE TABLE INVOICE (
    id bigserial NOT NULL,
	name varchar(255) NOT NULL,
	amount DECIMAL NOT NULL,
	due_date timestamp NOT NULL,
	payment_date timestamp NOT NULL,
	CONSTRAINT invoice_pkey PRIMARY KEY (id)
);

CREATE TABLE PAYMENT_RULE (
    id bigserial NOT NULL,
	delay_in_days int8 NOT NULL,
	penalty DECIMAL NULL,
	interest_day DECIMAL NULL,
	CONSTRAINT payment_rules_pkey PRIMARY KEY (id)
);