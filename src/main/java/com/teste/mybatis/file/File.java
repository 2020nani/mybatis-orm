package com.teste.mybatis.file;


public class File {
    private String pathOrigem;

    private String data;

    public File(String pathOrigem, String data) {
        this.pathOrigem = pathOrigem;
        this.data = data;
    }

    public String getPathOrigem() {
        return pathOrigem;
    }

    public String getData() {
        return data;
    }
}
