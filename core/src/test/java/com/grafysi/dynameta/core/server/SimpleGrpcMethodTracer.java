package com.grafysi.dynameta.core.server;

import io.grpc.stub.StreamObserver;

import java.util.ArrayList;
import java.util.List;

public class SimpleGrpcMethodTracer implements GrpcMethodTracer {

    private List<Call> callList = new ArrayList<>();

    @Override
    public void trace(String methodName, Object request, StreamObserver<Object> responseObserver) {
        callList.add(new Call(methodName, request, responseObserver));
    }

    public List<Call> getCallList() {
        return callList;
    }


    public static class Call {
        private String methodName;
        private Object request;
        private StreamObserver<Object> responseObserver;

        private Call(String methodName, Object request, StreamObserver<Object> responseObserver) {
            this.methodName = methodName;
            this.request = request;
            this.responseObserver = responseObserver;
        }

        public String getMethodName() {
            return methodName;
        }

        public Object getRequest() {
            return request;
        }

        public StreamObserver<Object> getResponseObserver() {
            return responseObserver;
        }
    }

}
