package com.teste.mybatis.file;

import java.io.*;
import java.io.File;
import java.util.zip.ZipEntry;
import java.util.zip.ZipOutputStream;

public class FileUtils {

    private final static int TAMANHO_BUFFER = 4096;

    public static void armazenaArquivoCompactado(String arqEntrada, String arqSaida) throws IOException {
        byte[] dados = new byte[TAMANHO_BUFFER];

        try {
            FileOutputStream destino = new FileOutputStream(new java.io.File(arqSaida));
            ZipOutputStream saida = new ZipOutputStream(new BufferedOutputStream(destino));
            java.io.File file = new File(arqEntrada);
            FileInputStream streamDeEntrada = new FileInputStream(file);
            BufferedInputStream origem = new BufferedInputStream(streamDeEntrada, TAMANHO_BUFFER);
            ZipEntry entry = new ZipEntry(file.getName());
            saida.putNextEntry(entry);

            int cont;

            while((cont = origem.read(dados, 0, TAMANHO_BUFFER)) != -1) {
                saida.write(dados, 0, cont);
            }
            origem.close();
            saida.close();
        } catch(IOException e) {
            throw new IOException(e.getMessage());
        }
    }
}
