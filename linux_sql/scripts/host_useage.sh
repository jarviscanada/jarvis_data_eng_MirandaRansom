#!/bin/bash

psql_host=$1
psql_port=$2
psql_dbName=$3
psql_user=$4
psql_password=$5

#exit if illegal command line arguments
if [ $# -ne 5 ]; then
  echo illegal number of commands
  exit 1
fi

#usage info
hostname=$(hostname -f)
vmstat_mb=$(vmstat --unit M)
meminfo_out=$(cat /proc/meminfo)
df_out=$(df /)
memory_free=$(echo "$vmstat_mb" | awk '{print $4}'| tail -n1 | xargs)
cpu_idle=$(echo "$meminfo_out" | egrep "^MemFree:" | awk '{print $2}' | xargs)
cpu_kernel=$(vmstat --unit M | tail -1 | awk '{print $13}' | xargs)
disk_io=$(echo "$vmstat_mb" |tail -1| awk '{print $10}'| xargs)
disk_available=$(echo "$df_out" |tail -1 | awk '{print $4}' | xargs)

#find host id from host_info
export PGPASSWORD=$psql_password
host_id=$(psql -h $psql_host -p $psql_port -d $psql_dbName -U $psql_user -t -c "SELECT id FROM host_info WHERE hostname='$hostname'")

#psql host_usage  insert
insert_stmt="INSERT INTO host_usage(time_stamp, host_id, memory_free, cpu_idle, cpu_kernel, disk_io, disk_available)
  VALUES(CURRENT_TIMESTAMP,'$host_id', '$memory_free', '$cpu_idle', '$cpu_kernel', '$disk_io', '$disk_available')"

#connect to psql and execute insert statement
psql -h $psql_host -p $psql_port -d $psql_dbName -U $psql_user -c "$insert_stmt"
exit $?