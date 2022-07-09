package com.teste.mybatis.file;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.io.*;
import java.util.zip.ZipEntry;
import java.util.zip.ZipOutputStream;

@RestController
public class FileController {

    @PostMapping("/teste")
    public void teste (@RequestBody File file) throws IOException {
        FileUtils.armazenaArquivoCompactado(file.getPathOrigem(), "C:/Users/Dell/Desktop/t/arquivo.zip");
    }
}
