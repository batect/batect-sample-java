CREATE TABLE transfers (
  id uuid PRIMARY KEY NOT NULL,
  from_currency char(3) NOT NULL,
  to_currency char(3) NOT NULL,
  transfer_date timestamp(0) with time zone NOT NULL,
  original_amount numeric NOT NULL,
  exchange_rate numeric NOT NULL
);
