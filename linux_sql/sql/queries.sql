-- function to round to nearest 5 minutes
CREATE FUNCTION round5(ts timestamp) RETURNS timestamp AS
    $$
BEGIN
RETURN date_trunc('hour', ts) + date_part('minute', ts):: int / 5 * interval '5 min';
END;
$$
    LANGUAGE PLPGSQL;

-- function to take total and free memory & return percent of memory used
CREATE FUNCTION percentMemInUse(total integer, free real) RETURNS real AS
$$
BEGIN
    RETURN ((total - free) / total) * 100;
END;
$$
    LANGUAGE PLPGSQL;

-- group by hardware
SELECT cpu_number,
       id,
       total_mem,
       ROW_NUMBER() over(
           PARTITION BY cpu_number
           ORDER BY
               total_mem
           )
FROM
    host_info;

-- average memory used in 5 minute intervals
SELECT host_usage.host_id,
       round5(host_usage.time_stamp),
       AVG( percentMemInUse(host_info.total_mem, host_usage.memory_free))
FROM host_usage, host_info
GROUP BY round5(host_usage.time_stamp), host_usage.host_id;

-- detect failure (data points < 5 in 5 minute interval)
SELECT host_id, round5(time_stamp), COUNT(*) as num_data_points
FROM host_usage
GROUP BY round5(time_stamp), host_id
HAVING COUNT(*) < 5;
