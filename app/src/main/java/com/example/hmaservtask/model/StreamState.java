package com.example.hmaservtask.model;

import java.util.List;

public class StreamState {
    public enum Status {
        LOADING,
        SUCCESS,
        ERROR

    }

    private String message;
    private Status status;
    private List<Stream> streamList;

    public StreamState(List<Stream> streamList, Status status, String message) {
        this.message = message;
        this.status = status;
        this.streamList = streamList;
    }

    public StreamState(Status status, String message) {
        this.message = message;
        this.status = status;
        this.streamList = streamList;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public Status getStatus() {
        return status;
    }

    public void setStatus(Status status) {
        this.status = status;
    }

    public List<Stream> getStreamList() {
        return streamList;
    }

    public void setStreamList(List<Stream> streamList) {
        this.streamList = streamList;
    }
}
