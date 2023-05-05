import com.amazonaws.auth.AWSCredentials;
import com.amazonaws.auth.BasicAWSCredentials;
import com.opencsv.exceptions.CsvException;
import data_import.S3DataImport;

import java.io.IOException;
import java.util.Scanner;


public class App {


    public static void main(String[] args) throws IOException, CsvException {
        Scanner userInput = new Scanner(System.in);


        String region = "us-east-1";
        String bucketName = "bw-weather-bucket";
        String objectKey = "data";


        //using scanner to manually enter aws keys so they are not public on GitHub
        System.out.print("Please enter Ben's aws access key: ");
        String accessKey = userInput.nextLine();

        System.out.print("Please enter Ben's aws secret key: ");
        String secretKey = userInput.nextLine();


        AWSCredentials credentials = new BasicAWSCredentials(accessKey, secretKey);



        S3DataImport dataImport = new S3DataImport(region, bucketName, objectKey, credentials);


        dataImport.readDataFromCloud();
        System.out.println("test");
    }
}
