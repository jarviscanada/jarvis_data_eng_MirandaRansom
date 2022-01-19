CREATE TABLE IF NOT EXISTS PUBLIC.host_info(
    id SERIAL NOT NULL PRIMARY KEY,
    hostname VARCHAR(100) NOT NULL UNIQUE,
    cpu_number INT NOT NULL,
    cpu_architecture VARCHAR(25) NOT NULL,
    cpu_model VARCHAR(50) NOT NULL,
    cpu_mhz FLOAT(3),
    L2_cache INT NOT NULL,
    total_mem INT NOT NULL,
    time_stamp TIMESTAMP NOT NULL
);

CREATE TABLE IF NOT EXISTS PUBLIC.host_usage(
    time_stamp TIMESTAMP NOT NULL,
    host_id INT REFERENCES host_info (id),
    memory_free FLOAT(3) NOT NULL,
    cpu_idle INT NOT NULL,
    cpu_kernel INT NOT NULL,
    disk_io INT NOT NULL,
    disk_available FLOAT(3) NOT NULL
);