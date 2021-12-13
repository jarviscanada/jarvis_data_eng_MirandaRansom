#!/bin/bash

#capture CLI arguments
cmd=$1
db_username=$2
db_password=$3

#start docker
sudo systemctl status docker || sudo systemctl start docker

#check container status
container_status=$(docker container inspect -f '{{.State.Status}}' jrvs-other)

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

    ;;
  start|stop)
    echo 'start or stop selected'

    if [ -z "$container_status" ]; then
      echo "Container does not exist"
    fi

    if [ "$container_status" = "running" ]; then
      echo 'Stopping container'
      docker container stop jrvs-psql
    fi

    if [ "$container_status" = "exited" ]; then
      echo 'Starting container'
      docker container start jrvs-psql
    fi
    ;;
  *)
    echo 'Illegal command'
    echo 'Commands: start|stop|create'
    exit 1
    ;;
esac
