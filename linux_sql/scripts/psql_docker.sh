#!/bin/bash

#capture CLI arguments
cmd=$1
db_username=$2
db_password=$3

#start docker
sudo systemctl status docker || sudo systemctl start docker

#check container status
container_status=$(docker container inspect -f '{{.State.Status}}' jrvs-psql)

#switch case to handle create|stop|start options
case $cmd in
  create)
    echo 'create selected'

    #check if container is already created
    if [ -n "$container_status" ]; then
      echo 'container already exists'
      exit 1
    fi

    #check # of CLI args
    if [ $# -ne 3 ]; then
      echo 'Create requires username and password'
      exit 1
    fi

    #create container
    echo 'creating container'
    #create new volume if not exist
    docker volume create pgdata
    #create a container using psql image with name jrvs-psql
    docker run --name jrvs-psql -e POSTGRES_PASSWORD="$db_password" -d -v pgdata:/var/lib/postgresql/data -p 5432:5432 postgres:9.6-alpine
    #install psql CLI client
    sudo yum install -y postgresql
    #connect to psql instance using psql REPL
    psql -h localhost -U postgres createuser "$db_username" -d postgres -W

    ;;
  start|stop)
    echo 'start or stop selected'

    if [ -z "$container_status" ]; then
      echo "Container does not exist"
      exit 1
    fi

    if [ "$container_status" = "running" ]; then
      echo 'Stopping container'
      docker container stop jrvs-psql
      exit 0
    fi

    if [ "$container_status" = "exited" ]; then
      echo 'Starting container'
      docker container start jrvs-psql
      exit 0
    fi
    ;;
  *)
    echo 'Illegal command'
    echo 'Commands: start|stop|create'
    exit 1
    ;;
esac
