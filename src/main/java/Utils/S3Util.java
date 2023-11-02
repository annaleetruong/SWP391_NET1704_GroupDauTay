package Utils;

import java.io.IOException;
import java.io.InputStream;
import software.amazon.awssdk.awscore.exception.AwsServiceException;
import software.amazon.awssdk.core.exception.SdkClientException;
import software.amazon.awssdk.core.sync.RequestBody;
import software.amazon.awssdk.regions.Region;
import software.amazon.awssdk.services.s3.S3Client;
import software.amazon.awssdk.services.s3.model.PutObjectRequest;
import software.amazon.awssdk.services.s3.model.S3Exception;

public class S3Util {

    private static final String BUCKET = "bird-farm-shop";

    public static void uploadFile(String fileName, InputStream inputStream)
            throws S3Exception, AwsServiceException, SdkClientException, IOException {
        Region region = Region.AP_SOUTHEAST_1;

        S3Client client = S3Client.builder().region(region).build();

        PutObjectRequest request = PutObjectRequest.builder()
                .bucket(BUCKET)
                .key(fileName)
                .acl("public-read")
                .build();

        client.putObject(request,
                RequestBody.fromInputStream(inputStream, inputStream.available()));
    }
}
