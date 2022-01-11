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

#hardware specs
lscpu_out=`lscpu`
hostname=$(hostname -f)
cpu_number=$(echo "$lscpu_out"  | egrep "^CPU\(s\):" | awk '{print $2}' | xargs)
cpu_architecture=$(echo "$lscpu_out" | egrep "^Architecture:" | awk '{print $2}' | xargs)
cpu_model=$(echo "$lscpu_out" | egrep "^Model name:" | awk '{print $3 $4 $5 $6 $7}'| xargs)
cpu_mhz=$(echo "$lscpu_out" | egrep "^CPU MHz:" | awk '{print $3}' | xargs)
l2_cache=$(echo "$lscpu_out"| egrep "^L2 cache:" | awk '{print $3}' | xargs)
l2_cache="${l2_cache::-1}" #trim tailing K
meminfo_out=$(cat /proc/meminfo)
total_mem=$(echo "$meminfo_out" | egrep "^MemTotal:" | awk '{print $2}' | xargs)

#psql host_info insert
insert_stmt="INSERT INTO host_info(hostname, cpu_number, cpu_architecture, cpu_model, cpu_mhz, l2_cache, total_mem, time_stamp)
  VALUES('$hostname', '$cpu_number', '$cpu_architecture', '$cpu_model', '$cpu_mhz', '$l2_cache', '$total_mem', CURRENT_TIMESTAMP)"

#connect to psql and execute insert statement
export PGPASSWORD=$psql_password
psql -h $psql_host -p $psql_port -d $psql_dbName -U $psql_user -c "$insert_stmt"
exit $?

