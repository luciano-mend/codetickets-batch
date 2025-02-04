package br.luciano.codetickets.steps;

import br.luciano.codetickets.models.Importacao;
import br.luciano.codetickets.processors.ImportacaoProcessor;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.repository.JobRepository;
import org.springframework.batch.core.step.builder.StepBuilder;
import org.springframework.batch.item.ItemReader;
import org.springframework.batch.item.ItemWriter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.transaction.PlatformTransactionManager;

@Configuration
public class ImportacaoStepConfig {

    @Autowired
    private PlatformTransactionManager transactionManager;

    @Bean
    public Step passoInicial(@Qualifier("importacaoReader") ItemReader<Importacao> importacaoReader,
                             @Qualifier("importacaoWriter") ItemWriter<Importacao> importacaoWriter,
                             ImportacaoProcessor processor, JobRepository jobRepository){
        return new StepBuilder("passo-inicial", jobRepository)
                .<Importacao, Importacao>chunk(200, transactionManager)
                .reader(importacaoReader)
                .processor(processor)
                .writer(importacaoWriter)
                .allowStartIfComplete(true)
                .build();
    }
}
