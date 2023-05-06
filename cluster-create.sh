#/bin/bash

if [ "$#" -ne 3 ]; then
    echo "Usage: ./cluster_create.sh bucket-name  region  zone"
    exit
fi

PROJECT=$(gcloud config get-value project)
BUCKET=$1
REGION=$2
ZONE=$3
INSTALL=gs://$BUCKET/install.sh

#upload install
gsutil cp install.sh $INSTALL

gcloud beta dataproc clusters create \
    --num-workers=2 \
    --worker-machine-type=n1-standard-1 \
    --master-machine-type=n1-standard-1\
    --image-version=1.5-ubuntu18 \
    --enable-component-gateway \
    --optional-components=ANACONDA,JUPYTER \
    --zone=$ZONE \
    --region=$REGION \
    --initialization-actions=$INSTALL \
    --worker-boot-disk-size=45GB \
    cluster1