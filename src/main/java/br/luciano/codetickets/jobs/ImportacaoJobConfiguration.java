package br.luciano.codetickets.jobs;

import org.springframework.batch.core.Job;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.job.builder.JobBuilder;
import org.springframework.batch.core.launch.support.RunIdIncrementer;
import org.springframework.batch.core.repository.JobRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.transaction.PlatformTransactionManager;

@Configuration
public class ImportacaoJobConfiguration {
    @Autowired
    private PlatformTransactionManager transactionManager;

    @Bean
    public Job importacaoJob(@Qualifier("passoInicial") Step passoInicial, @Qualifier("movimentacaoInicial") Step movimentacaoInicial,
                             @Qualifier("moverArquivosStep") Step moverArquivosStep, JobRepository jobRepository) {
        return new JobBuilder("geracao-tickets", jobRepository)
                .start(passoInicial)
                .next(moverArquivosStep)
                .next(movimentacaoInicial)
                .incrementer(new RunIdIncrementer())
                .build();
    }

}
