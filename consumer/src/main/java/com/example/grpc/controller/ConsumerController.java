package com.example.grpc.controller;


import org.springframework.web.bind.annotation.RestController;

// import com.example.grpc.HelloRequest;
// import com.example.grpc.HelloResponse;
import com.example.grpc.HelloServiceGrpc;
import com.example.grpc.dto.HelloRequest;
import com.example.grpc.dto.HelloResponse;

import io.grpc.ManagedChannel;
import io.grpc.ManagedChannelBuilder;
import lombok.extern.slf4j.Slf4j;

import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@Slf4j
@RestController
public class ConsumerController {
    
    // @Override
    // public StreamObserver<HelloRequest> hello(
    //     StreamObserver<HelloResponse> responseObserver
    // ) {
    //     // ...
    //     return null;
    // }

    @PostMapping(path = "hello", consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Object> postMethodName(@RequestBody HelloRequest request) {

        ManagedChannel channel = ManagedChannelBuilder.forAddress("localhost", 9090)
          .usePlaintext()
          .build();

        HelloServiceGrpc.HelloServiceBlockingStub stub 
          = HelloServiceGrpc.newBlockingStub(channel);

        com.example.grpc.HelloResponse helloResponse = stub.hello(com.example.grpc.HelloRequest.newBuilder()
          .setFirstName(request.getFirstName())
          .setLastName(request.getLastName())
          .build());

        log.debug("helloResponse : [{}]", helloResponse.getGreeting());
        // System.out.println(helloResponse.getGreeting());
        channel.shutdown();

        HelloResponse response = new HelloResponse();
        response.setGreeting(helloResponse.getGreeting());

        return new ResponseEntity<Object>(response, HttpStatus.OK);
    }
}
