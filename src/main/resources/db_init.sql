CREATE TABLE category (
    category_id int NOT NULL,
    category_name_key varchar(64) NOT NULL,
    category_description_key varchar(64) DEFAULT NULL,
    category_preview BLOB DEFAULT NULL,
    enabled BOOLEAN DEFAULT TRUE,
    category_type_id int NOT NULL,
    modified_date TIMESTAMP DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
    CONSTRAINT PK_Category PRIMARY KEY (category_id)
);

CREATE TABLE text_i18n (
    text_key varchar(64) NOT NULL ,
    text_value varchar(2048) NOT NULL,
    locale varchar(64) NOT NULL,
    modified_date TIMESTAMP DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
    CONSTRAINT PK_text_i18n PRIMARY KEY (text_key)
);

CREATE TABLE locale (
    locale varchar(64) NOT NULL,
    CONSTRAINT PK_locale PRIMARY KEY (locale)
);

CREATE TABLE category_type (
    category_type_id int NOT NULL,
    category_type_name varchar(128),
    category_type_note varchar(256),
    CONSTRAINT PK_category_type PRIMARY KEY (category_type_id)
);

CREATE TABLE category_parent (
    id int NOT NULL,
    category_id int NOT NULL,
    category_parent_id int NOT NULL,
    CONSTRAINT PK_category_parent PRIMARY KEY (id)
);

CREATE TABLE image (
    image_id int NOT NULL,
    image_raw_data LONGTEXT NOT NULL,
    category_id int NOT NULL,
    enabled BOOLEAN DEFAULT TRUE,
    modified_date TIMESTAMP DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
    is_daily BOOLEAN DEFAULT FALSE NOT NULL,
    CONSTRAINT PK_image PRIMARY KEY (image_id)
);

CREATE TABLE image_progress
(
    image_progress_id int NOT NULL,
    user_id int NOT NULL,
    image_id int NOT NULL,
    completed_image_parts MEDIUMTEXT,
    is_complete BOOLEAN,
    modified_date TIMESTAMP DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
    CONSTRAINT PK_image_progress PRIMARY KEY (image_progress_id)
);

CREATE TABLE user
(
    user_id int NOT NULL,
    user_email varchar(100) NOT NULL,
    CONSTRAINT PK_image_progress PRIMARY KEY (user_id)
);

ALTER TABLE `category` ADD CONSTRAINT `tablesConnectionByNameKey` FOREIGN KEY (`category_name_key`) REFERENCES `text_i18n`(`text_key`) ON DELETE RESTRICT ON UPDATE RESTRICT;
ALTER TABLE `category` ADD CONSTRAINT `tablesConnectionByDescriptionKey` FOREIGN KEY (`category_description_key`) REFERENCES `text_i18n`(`text_key`) ON DELETE RESTRICT ON UPDATE RESTRICT;
ALTER TABLE `category` ADD CONSTRAINT `tablesConnectionByCategoryType` FOREIGN KEY (`category_type_id`) REFERENCES `category_type`(`category_type_id`) ON DELETE RESTRICT ON UPDATE RESTRICT;
ALTER TABLE `text_i18n` ADD CONSTRAINT `tablesConnectionByLocale` FOREIGN KEY (`locale`) REFERENCES `locale`(`locale`) ON DELETE RESTRICT ON UPDATE RESTRICT;

ALTER TABLE `category_parent` ADD CONSTRAINT `tablesConnectionByCategoryParent` FOREIGN KEY (`category_id`) REFERENCES `category`(`category_id`) ON DELETE RESTRICT ON UPDATE RESTRICT;
ALTER TABLE `category_parent` ADD CONSTRAINT `tablesConnectionByCategoryID` FOREIGN KEY (`category_parent_id`) REFERENCES `category`(`category_id`) ON DELETE RESTRICT ON UPDATE RESTRICT;
ALTER TABLE `image` ADD CONSTRAINT `tablesConnectionByImageCategoryID` FOREIGN KEY (`category_id`) REFERENCES `category`(`category_id`) ON DELETE RESTRICT ON UPDATE RESTRICT;

ALTER TABLE `image_progress` ADD CONSTRAINT `tablesConnectionByImageID` FOREIGN KEY (`image_id`) REFERENCES `image`(`image_id`) ON DELETE RESTRICT ON UPDATE RESTRICT;
ALTER TABLE `image_progress` ADD CONSTRAINT `tablesConnectionByUserID` FOREIGN KEY (`user_id`) REFERENCES `user`(`user_id`) ON DELETE RESTRICT ON UPDATE RESTRICT;
