
    alter table comments 
        drop 
        foreign key comments_user_id_fk;

    alter table comments 
        drop 
        foreign key comments_post_id_fk;

    alter table posts 
        drop 
        foreign key posts_author_fk;

    drop table if exists comments;

    drop table if exists contact_messages;

    drop table if exists posts;

    drop table if exists users;

    create table comments (
        id bigint not null auto_increment,
        created_at datetime,
        updated_at datetime,
        content varchar(255) not null,
        post_id bigint not null,
        user_id bigint not null,
        primary key (id)
    ) ENGINE=InnoDB CHARACTER SET utf8 COLLATE utf8_general_ci;

    create table contact_messages (
        id bigint not null auto_increment,
        created_at datetime,
        updated_at datetime,
        checked bit not null,
        email varchar(255),
        message varchar(255),
        name varchar(255),
        subject varchar(255),
        primary key (id)
    ) ENGINE=InnoDB CHARACTER SET utf8 COLLATE utf8_general_ci;

    create table posts (
        id bigint not null auto_increment,
        created_at datetime,
        updated_at datetime,
        category varchar(255),
        comments_allowed bit,
        content longtext not null,
        publish_date datetime,
        published bit not null,
        slug varchar(255),
        summary longtext not null,
        title varchar(255) not null,
        author_id bigint not null,
        primary key (id)
    ) ENGINE=InnoDB CHARACTER SET utf8 COLLATE utf8_general_ci;

    create table users (
        id bigint not null auto_increment,
        created_at datetime,
        updated_at datetime,
        admin bit not null,
        email varchar(255) not null unique,
        hashed_password varchar(255),
        name varchar(255) not null unique,
        salt varchar(255),
        primary key (id)
    ) ENGINE=InnoDB CHARACTER SET utf8 COLLATE utf8_general_ci;

    alter table comments 
        add index comments_user_id_fk (user_id), 
        add constraint comments_user_id_fk 
        foreign key (user_id) 
        references users (id);

    alter table comments 
        add index comments_post_id_fk (post_id), 
        add constraint comments_post_id_fk 
        foreign key (post_id) 
        references posts (id);

    alter table posts 
        add index posts_author_fk (author_id), 
        add constraint posts_author_fk 
        foreign key (author_id) 
        references users (id);
