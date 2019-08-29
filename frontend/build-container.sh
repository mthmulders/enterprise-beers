#!/usr/bin/env bash

if [[ "$#" -ne 1 ]]; then
    echo "$0 <num>"
    echo ""
    echo "Parameters:"
    echo "  <num> the version of the container being built"
    exit
fi

pushd app/

yarn build || exit 1

popd

tmp_dir=$(mktemp -d)
mkdir -p ${tmp_dir}/app/build

cp -Rv app/build/* ${tmp_dir}/app/build/
cp -Rv Dockerfile ${tmp_dir}/

pushd ${tmp_dir}

docker build -t enterprisebeers.azurecr.io/frontend:1.0.$1 .
docker push enterprisebeers.azurecr.io/frontend:1.0.$1

popd
