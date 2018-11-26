insert into price_list(currency, date, id) values ('BTC', PARSEDATETIME('20180507', 'YYYYMMDD'), 1);
insert into price_list(currency, date, id) values ('ETC', PARSEDATETIME('20180507', 'YYYYMMDD'), 2);
insert into price_list(currency, date, id) values ('LTC', PARSEDATETIME('20180507', 'YYYYMMDD'), 3);

insert into quote(price, price_list_id, time, id) values ('34.98', 1, '0915', 4);
insert into quote(price, price_list_id, time, id) values ('36.13', 1, '1045', 5);
insert into quote(price, price_list_id, time, id) values ('37.01', 1, '1230', 6);
insert into quote(price, price_list_id, time, id) values ('35.98', 1, '1400', 7);
insert into quote(price, price_list_id, time, id) values ('33.56', 1, '1530', 8);

insert into quote(price, price_list_id, time, id) values ('1.45', 2, '0900', 9);
insert into quote(price, price_list_id, time, id) values ('1.87', 2, '1030', 10);
insert into quote(price, price_list_id, time, id) values ('1.55', 2, '1245', 11);
insert into quote(price, price_list_id, time, id) values ('2.01', 2, '1515', 12);
insert into quote(price, price_list_id, time, id) values ('2.15', 2, '1700', 13);

insert into quote(price, price_list_id, time, id) values ('14.32', 3, '0930', 14);
insert into quote(price, price_list_id, time, id) values ('14.87', 3, '1115', 15);
insert into quote(price, price_list_id, time, id) values ('15.03', 3, '1245', 16);
insert into quote(price, price_list_id, time, id) values ('14.76', 3, '1400', 17);
insert into quote(price, price_list_id, time, id) values ('14.15', 3, '1700', 18);
