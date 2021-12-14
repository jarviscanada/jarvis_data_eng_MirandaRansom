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