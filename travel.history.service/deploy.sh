#!/bin/sh

BASEDIR=$(dirname "$0")

kubectl apply -f  $BASEDIR/deployment.yaml