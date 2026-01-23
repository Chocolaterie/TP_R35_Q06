insert into category (name) values ('Informatique');
insert into category (name) values ('Viennoiserie');

insert into article (title, category_id) values ('Blueberry Muffin Mix', 1);
insert into article (title, category_id) values ('Luxury Bathrobe', 2);
insert into article (title, category_id) values ('Portable Solar Generator', 1);
insert into article (title, category_id) values ('Toasted Coconut Granola', 2);
insert into article (title, category_id) values ('Organic Almond Flour', 1);
insert into article (title, category_id) values ('Greek Yogurt', 1);
insert into article (title, category_id) values ('Digital Camera', 2);
insert into article (title, category_id) values ('Maple Oatmeal', 2);
insert into article (title, category_id) values ('Foldable Yoga Mat', 1);
insert into article (title, category_id) values ('Tomatillo Salsa', 1);


insert into app_user (email, password) values ('sgobin@eni-ecole.fr', '123456');
insert into app_user (email, password) values ('titi@eni-ecole.fr', '123');

insert into category_articles (articles_id, category_id) values (1, 1);

insert into tag (value) values ("Chocolatine");
insert into tag (value) values ("Beurre Doux");

insert into article_tags (articles_id, tags_id) values (1, 1);
insert into article_tags (articles_id, tags_id) values (1, 2);

