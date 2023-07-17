insert into contents (content_id, content) values
(1, 'newspapers'),
(2, 'tv'),
(3, 'books'),
(4, 'socialMedia'),
(5, 'article');
insert into tags (tag_id , name) values
(11, 'like'),
(12, 'hate'),
(13, 'ok'),
(14, 'awesome'),
(15, 'neutral'),
(16, 'positive'),
(17 ,'negative'),
(18, 'love'),
(19, 'bad'),
(20, 'awful');

insert into child_parent (child_id , parent_id) values
(11, 16),
(18, 16),
(12, 17),
(19, 17),
(20, 17),
(13, 15),
(14, 16);
insert into content_tags (content_id , tag_id) values
(1,11),
(1,20),
(1,19),
(1, 12),
(1, 15),
(2,13),
(2,16),
(2,20),
(3, 12),
(3, 17),
(3, 14),
(3, 11),
(5, 12),
(5, 17),
(4, 14),
(4, 11);