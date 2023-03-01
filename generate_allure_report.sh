#!/bin/bash
parent_path=$( cd "$(dirname "${BASH_SOURCE[0]}")" ; pwd -P )
cd "$parent_path/target/allure/allure-2.9.0/bin/"
bash allure serve "$parent_path/allure-results"
