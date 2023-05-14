#/bin/bash
ENV_FILE=../.env

function fail() {
    echo $2
    exit $1
}

if [[ -f $ENV_FILE ]]; then
    echo "Sourcing environment variables..."
    source $ENV_FILE
else
    fail 9 "$ENV_FILE not present..."
fi

echo "Creating bucket ${AWS_S3_BUCKET}..."
aws s3api create-bucket --endpoint-url=${AWS_ENDPOINT} --bucket=${AWS_S3_BUCKET} --region=${AWS_DEFAULT_REGION}
echo "Upload some files"
aws s3api put-object --bucket=${AWS_S3_BUCKET} --key=test_1 --body=./test_1.txt --endpoint-url=${AWS_ENDPOINT}
aws s3api put-object --bucket=${AWS_S3_BUCKET} --key=test_2 --body=./test_2.txt --endpoint-url=${AWS_ENDPOINT}
aws s3api put-object --bucket=${AWS_S3_BUCKET} --key=test_3 --body=./test_3.txt --endpoint-url=${AWS_ENDPOINT}