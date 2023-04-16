INSERT INTO public.comic_data (upc, issue_number, series, publish_date, image_url, author, artist) VALUES (75960608936900111, 1, 'The Amazing Spider-Man (2018)', '07/11/2018', 'b/90/5b3feb60f1a30', 'Nick Spencer', 'Ryan Ottley');
INSERT INTO public.comic_data (upc, issue_number, series, publish_date, image_url, author, artist) VALUES (75960607913100111, 1, 'Thor: God of Thunder (2012)', '11/14/2012', '3/30/51646f6c2a712', 'Jason Aaron', 'Esad Ribic');
INSERT INTO public.comic_data (upc, issue_number, series, publish_date, image_url, author, artist) VALUES (75960609724103011, 30, 'Star Wars: Doctor Aphra (2020)', '04/12/2023', '3/70/64346a94258aa', 'Alyssa Wong', 'Rachael Stott');
INSERT INTO public.comic_data (upc, issue_number, series, publish_date, image_url, author, artist) VALUES (75960608956700111, 1, 'Immortal Hulk (2018)', '06/06/2018', '3/50/5b0f16d976240', 'Al Ewing', 'Joe Bennett');
INSERT INTO public.comic_data (upc, issue_number, series, publish_date, image_url, author, artist) VALUES (75960605921800111, 1, 'Civil War (2006)', '05/03/2006', 'e/f0/511307b2f1200', 'Mark Millar', 'Steve Mcniven');
INSERT INTO public.comic_data (upc, issue_number, series, publish_date, image_url, author, artist) VALUES (75960608857706111, 61, 'Avengers (2018)', '10/19/2022', '6/00/634f64cc2305b', 'Jason Aaron', 'Ivan Fiorelli');
INSERT INTO public.comic_data (upc, issue_number, series, publish_date, image_url, author, artist) VALUES (75960608917800311, 3, 'Ant-Man & the Wasp (2018)', '07/04/2018', '6/e0/5b3408e8c6a88', 'Mark Waid', 'Javier Garron');
INSERT INTO public.comic_data (upc, issue_number, series, publish_date, image_url, author, artist) VALUES (75960620099300511, 5, 'Death of Doctor Strange (2021)', '01/26/2022', '5/b0/61cf40567ca00', 'Jed Mackay', 'Lee Garbett');
INSERT INTO public.comic_data (upc, issue_number, series, publish_date, image_url, author, artist) VALUES (5960604716-64821, 648, 'Amazing Spider-Man (1999)', '11/10/2010', '6/03/5813b47f01394', 'Dan Slott', 'Humberto Ramos');
INSERT INTO public.comic_data (upc, issue_number, series, publish_date, image_url, author, artist) VALUES (75960605921800111, 1, 'Avengers Vs. X-Men (2012)', '04/04/2012', 'd/90/4f7dc878b57fe', 'Brian Michael Bendis', 'John Romita');

INSERT INTO public.users (username, password_hash, role) VALUES ('max', '$2a$10$kXnAbYWckcphV6uVqAtivutGkAgpPLIdCQGd.h/AtOiRZ8DW/u9w2', 'ROLE_USER');

INSERT INTO public.collection (public, user_id, collection_name) VALUES (true, 7, 'The Marvel Collection');
INSERT INTO public.collection (public, user_id, collection_name) VALUES (false, 7, 'The Spiderman Collection');

INSERT INTO public.comic_collection (collection_id, comic_data_id) VALUES (9, 8);
INSERT INTO public.comic_collection (collection_id, comic_data_id) VALUES (9, 9);
INSERT INTO public.comic_collection (collection_id, comic_data_id) VALUES (9, 10);
INSERT INTO public.comic_collection (collection_id, comic_data_id) VALUES (9, 11);
INSERT INTO public.comic_collection (collection_id, comic_data_id) VALUES (9, 12);
INSERT INTO public.comic_collection (collection_id, comic_data_id) VALUES (9, 13);
INSERT INTO public.comic_collection (collection_id, comic_data_id) VALUES (9, 14);
INSERT INTO public.comic_collection (collection_id, comic_data_id) VALUES (9, 15);
INSERT INTO public.comic_collection (collection_id, comic_data_id) VALUES (9, 16);
INSERT INTO public.comic_collection (collection_id, comic_data_id) VALUES (9, 17);
INSERT INTO public.comic_collection (collection_id, comic_data_id) VALUES (10, 8);
INSERT INTO public.comic_collection (collection_id, comic_data_id) VALUES (10, 12);
INSERT INTO public.comic_collection (collection_id, comic_data_id) VALUES (10, 13);
INSERT INTO public.comic_collection (collection_id, comic_data_id) VALUES (10, 16);
INSERT INTO public.comic_collection (collection_id, comic_data_id) VALUES (10, 17);