#!/bin/zsh

isBuild=$1
isRun=$2
projects=$3
mavenProfiles=$4

echo "projects: ${projects}"
echo "mavenProfiles: ${mavenProfiles}"
echo "isBuild: ${isBuild}"
echo "isRun: ${isRun}"

# build
if [ "${isBuild}" == "do-build" ]; 
then
echo ""
echo "------------------------------------------------------------------------"
echo "[START] build sub-module ${projects}."
echo "------------------------------------------------------------------------"
echo ""
mvn clean package --projects ${projects} --activate-profiles ${mavenProfiles}
echo ""
echo "------------------------------------------------------------------------"
echo "[COMPLETED] build sub-module ${projects}."
echo "------------------------------------------------------------------------"
fi

# run
if [ "${isRun}" == "do-run" ]; 
then
echo ""
echo "------------------------------------------------------------------------"
echo "[START] run sub-module ${projects}."
echo "------------------------------------------------------------------------"
echo ""
mvn spring-boot:run --projects ${projects}
echo ""
echo "------------------------------------------------------------------------"
echo "[COMPLETED] run sub-module ${projects}."
echo "------------------------------------------------------------------------"
fi
