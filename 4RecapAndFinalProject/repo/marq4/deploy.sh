#! /bin/bash

set -euo pipefail

SERVICE='flaskapp.service'

echo 'Inside EC2 instance. '

cd /home/ec2-user/App/

# Create venv if it doesn't exist:
if [ ! -d 'venv' ]; then
    echo 'Creating virtual environment... '
    python3 -m venv venv
fi

echo 'Upgrading pip... '
./venv/bin/python3 -m pip install --upgrade pip

echo '(EC2 deploy) Installing requirements... '
./venv/bin/python3 -m pip install -r requirements.txt

echo 'Restarting Flask App service... '
sudo systemctl restart ${SERVICE}

# Check if restart was successful
if sudo systemctl is-active --quiet ${SERVICE}
then
    echo '[SUCCESS] Flask app restarted successfully. '
    sudo systemctl status ${SERVICE} --no-pager -l
else
    echo '[ERROR] Flask app failed to start! '
    sudo systemctl status ${SERVICE} --no-pager -l
    exit 1
fi
