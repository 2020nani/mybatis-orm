package com.teste.mybatis.file;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.io.*;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.zip.ZipEntry;
import java.util.zip.ZipOutputStream;

@RestController
public class FileController {

    @PostMapping("/teste")
    public void teste (@RequestBody MultipartFile file) throws IOException {
        List<String> ceps = new ArrayList<>();
        String path = "C:\\Users\\Dell\\Downloads\\ceps2.txt";
        Scanner in = new Scanner(new FileReader(path));
        while (in.hasNextLine()) {
            String line = in.nextLine();
            ceps.add(line);
        }
        ceps.stream().forEach(teste -> System.out.println(teste));

        //FileUtils.armazenaArquivoCompactado(file, "C:/Users/Dell/Desktop/t/arquivo.zip");
    }
}
