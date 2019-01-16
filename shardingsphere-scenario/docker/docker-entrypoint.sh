#!/bin/sh

echo "replace {MYSQL_INSTANCE_HOST} to $MYSQL_INSTANCE_HOST"

exec "$@"
