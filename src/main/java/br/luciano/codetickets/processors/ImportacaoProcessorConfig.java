package br.luciano.codetickets.processors;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ImportacaoProcessorConfig {

    @Bean
    public ImportacaoProcessor processor() {
        return new ImportacaoProcessor();
    }
}
