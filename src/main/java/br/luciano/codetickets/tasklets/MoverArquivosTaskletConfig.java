package br.luciano.codetickets.tasklets;

import org.springframework.batch.core.step.tasklet.Tasklet;
import org.springframework.batch.repeat.RepeatStatus;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.io.File;

@Configuration
public class MoverArquivosTaskletConfig {

    @Bean
    public Tasklet moverArquivosTasklet() {
        return (contribution, chunkContext) -> {
            File pastaOrigem = new File("files");
            File pastaDestino = new File("imported-files");
            if (!pastaDestino.exists()) {
                pastaDestino.mkdirs();
            }

            File[] arquivos = pastaOrigem.listFiles((dir, name) -> name.equalsIgnoreCase("dados.csv"));

            if (arquivos != null) {
                for (File arquivo : arquivos) {
                    File arquivoDestino = new File(pastaDestino, arquivo.getName());
                    if (arquivo.renameTo(arquivoDestino)) {
                        System.out.println("Arquivo movido: " + arquivo.getName());
                    } else {
                        throw new RuntimeException("Não foi possível mover o arquivo: " + arquivo.getName());
                    }
                }
            }
            return RepeatStatus.FINISHED;
        };
    }
}
