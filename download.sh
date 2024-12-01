#!/bin/bash

# Set the year and day variables
YEAR=2024
DAY=1
SLEEP_TIME=20

# Get the current curl version
CURL_VERSION=$(curl --version | head -n 1 | awk '{print $2}')


# User agent
USER_AGENT="Mr. Kropp's automated download script using curl/$CURL_VERSION . Contact me at @kroppeb@tech.lgbt"

# Ensure AOC_SESSION_TOKEN is set
if [ -z "$AOC_SESSION_TOKEN" ]; then
  echo "Error: AOC_SESSION_TOKEN environment variable is not set."
  exit 1
fi

if [ $SLEEP_TIME -gt 0 ]; then
  echo "Sleeping for $SLEEP_TIME seconds"
  sleep $SLEEP_TIME
fi

# Fetch the input using curl
curl -H "Cookie: session=$AOC_SESSION_TOKEN" \
     -H "User-Agent: $USER_AGENT" \
     https://adventofcode.com/$YEAR/day/$DAY/input \
     > ./today/resources/$DAY.txt

# Print success message
if [ $? -eq 0 ]; then
  echo "Input for day $DAY of $YEAR downloaded successfully to ./today/resources/$DAY.txt"
else
  echo "Failed to download input."
fi
