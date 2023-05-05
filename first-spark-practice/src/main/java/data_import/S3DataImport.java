package data_import;

import com.amazonaws.auth.AWSCredentials;
import com.amazonaws.auth.AWSStaticCredentialsProvider;
import com.amazonaws.regions.Region;
import com.amazonaws.regions.Regions;
import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.AmazonS3Client;
import com.amazonaws.services.s3.AmazonS3ClientBuilder;

import java.util.List;

public class S3DataImport implements CloudDataImporter{
    private final String region;
    private final String bucketName;
    private final String objectKey;
    private final AWSCredentials credentials;
    private final AmazonS3 s3Client;

    public S3DataImport(String region, String bucketName, String objectKey, AWSCredentials credentials) {
        this.region = region;
        this.bucketName = bucketName;
        this.objectKey = objectKey;
        this.credentials = credentials;
        s3Client = AmazonS3ClientBuilder.standard()
                .withCredentials(new AWSStaticCredentialsProvider(credentials))
                .withRegion(region)
                .build();
    }

    public String getRegion() {
        return region;
    }

    public String getBucketName() {
        return bucketName;
    }

    public String getObjectKey() {
        return objectKey;
    }

    public AWSCredentials getCredentials() {
        return credentials;
    }

    public AmazonS3 getS3Client() {
        return s3Client;
    }

    @Override
    public List<String[]> readDataFromCloud() {
        return null;
    }
}
