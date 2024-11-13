

import io.grpc.Server;
import io.grpc.ServerBuilder;

// acts as server
public class GrpcServer {
    public static void main(String[] args) {
        Server server = ServerBuilder
                .forPort(8080)
                .addService(new HelloServiceImpl()).build();

        try {
            server.start();
            server.awaitTermination();
        } catch (Exception e) {
            // TODO: handle exception
        }

    }
}
