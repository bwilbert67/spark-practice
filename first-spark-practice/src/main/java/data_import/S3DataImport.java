package data_import;


import com.amazonaws.auth.AWSCredentials;
import com.amazonaws.auth.AWSStaticCredentialsProvider;
import com.amazonaws.regions.Region;
import com.amazonaws.regions.Regions;
import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.AmazonS3Client;
import com.amazonaws.services.s3.AmazonS3ClientBuilder;
import com.amazonaws.services.s3.model.S3Object;
import com.opencsv.CSVReader;
import com.opencsv.exceptions.CsvException;


import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.StringReader;
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
    public List<String[]> readDataFromCloud() throws IOException, CsvException {
        S3Object s3Object = s3Client.getObject(bucketName, objectKey);


        BufferedReader reader = new BufferedReader(new InputStreamReader(s3Object.getObjectContent()));
        StringBuilder stringBuilder = new StringBuilder();
        String line;
        while ((line = reader.readLine()) != null) {
            stringBuilder.append(line);
            stringBuilder.append("\n");
            System.out.println(line);
        }
        String data = stringBuilder.toString();
        CSVReader csvReader = new CSVReader(new StringReader(data));
        return csvReader.readAll();


    }
}
