-- 创建数据库
DROP DATABASE IF EXISTS aggregated_blog;
CREATE DATABASE aggregated_blog;


-- 创建作者表
CREATE TABLE author (
                        author_id SERIAL PRIMARY KEY,
                        author_name VARCHAR(100) NOT NULL,
                        contact_info VARCHAR(255),
                        bio TEXT,
                        joined_time TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
                        github_link VARCHAR(255),
                        weibo_link VARCHAR(255),
                        is_verified BOOLEAN DEFAULT FALSE
);

-- 创建文章表
CREATE TABLE article (
                         article_id SERIAL PRIMARY KEY,
                         author_id INT NOT NULL,
                         title VARCHAR(255) NOT NULL,
                         content TEXT NOT NULL,
                         category VARCHAR(100),
                         publish_time TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
                         views INT DEFAULT 0,
                         likes INT DEFAULT 0,
                         abstract TEXT,
                         copyright_info TEXT,
                         version INT DEFAULT 1,
                         FOREIGN KEY (author_id) REFERENCES author(author_id)
);

-- 创建评论表
CREATE TABLE comment (
                         comment_id SERIAL PRIMARY KEY,
                         article_id INT NOT NULL,
                         author_id INT NOT NULL,
                         comment_content TEXT NOT NULL,
                         comment_time TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
                         parent_comment_id INT,
                         likes INT DEFAULT 0,
                         status VARCHAR(20) DEFAULT 'published',
                         FOREIGN KEY (article_id) REFERENCES article(article_id),
                         FOREIGN KEY (author_id) REFERENCES author(author_id),
                         FOREIGN KEY (parent_comment_id) REFERENCES comment(comment_id)
);

-- 创建索引以提升查询性能
-- 为文章表的作者ID创建索引，加快基于作者的文章查询
CREATE INDEX idx_article_author_id ON article (author_id);
-- 为评论表的文章ID创建索引，加快基于文章的评论查询
CREATE INDEX idx_comment_article_id ON comment (article_id);
-- 为评论表的作者ID创建索引，加快基于作者的评论查询
CREATE INDEX idx_comment_author_id ON comment (author_id);