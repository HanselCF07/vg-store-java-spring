CREATE TABLE vg_store.video_game_category (
    video_game_category_id SERIAL PRIMARY KEY,
    name VARCHAR(50) NOT NULL,
    created_at TIMESTAMPTZ NOT NULL DEFAULT CURRENT_TIMESTAMP,
    record_status INT NOT NULL DEFAULT 1
);

CREATE TABLE vg_store.video_game_developer (
    video_game_developer_id SERIAL PRIMARY KEY,
    name VARCHAR(100) NOT NULL,
    country VARCHAR(50),
    description TEXT,
    created_at TIMESTAMPTZ NOT NULL DEFAULT CURRENT_TIMESTAMP,
    record_status INT NOT NULL DEFAULT 1
);

CREATE TABLE vg_store.video_game (
    video_game_id SERIAL PRIMARY KEY,
    video_game_public_id VARCHAR(255) UNIQUE,
    video_game_developer_id INT REFERENCES vg_store.video_game_developer(video_game_developer_id),
    status INT NOT NULL DEFAULT 1,
    title VARCHAR(100) NOT NULL,
    price DECIMAL(10,2) NOT NULL,
    description TEXT,
    release_date DATE,
    created_at TIMESTAMPTZ NOT NULL DEFAULT CURRENT_TIMESTAMP,
    record_status INT NOT NULL DEFAULT 1
);

-- Many-to-many relationship between video games and categories
CREATE TABLE vg_store.video_game_category_mtm (
    video_game_category_mtm_id SERIAL PRIMARY KEY,
    video_game_id INT REFERENCES vg_store.video_game(video_game_id),
    video_game_category_id INT REFERENCES vg_store.video_game_category(video_game_category_id),
    created_at TIMESTAMPTZ NOT NULL DEFAULT CURRENT_TIMESTAMP,
    record_status INT NOT NULL DEFAULT 1
);

CREATE TABLE vg_store.dlc (
    dlc_id SERIAL PRIMARY KEY,
    dlc_public_id VARCHAR(255) UNIQUE,
    video_game_id INT REFERENCES vg_store.video_game(video_game_id),
    status INT NOT NULL DEFAULT 1,
    title VARCHAR(100) NOT NULL,
    description TEXT,
    price DECIMAL(10,2) NOT NULL,
    release_date DATE,
    created_at TIMESTAMPTZ NOT NULL DEFAULT CURRENT_TIMESTAMP,
    record_status INT NOT NULL DEFAULT 1
);

CREATE TABLE vg_store.video_game_resource_location (
    video_game_resource_location_id SERIAL PRIMARY KEY,
    name VARCHAR(50) NOT NULL, -- e.g., 'head', 'background', 'catalog', 'carousel'
    description TEXT,
    created_at TIMESTAMPTZ NOT NULL DEFAULT CURRENT_TIMESTAMP,
    record_status INT NOT NULL DEFAULT 1
);

CREATE TABLE vg_store.video_game_static_resource (
    video_game_static_resource_id SERIAL PRIMARY KEY,
    video_game_id INT REFERENCES vg_store.video_game(video_game_id),
    video_game_resource_location_id INT REFERENCES vg_store.video_game_resource_location(video_game_resource_location_id),
    file_id VARCHAR(255), -- ID del archivo en GridFS
    filename VARCHAR(100),
    url VARCHAR(255),
    mimetype VARCHAR(50),
    description TEXT,
    created_at TIMESTAMPTZ NOT NULL DEFAULT CURRENT_TIMESTAMP,
    record_status INT NOT NULL DEFAULT 1
);
