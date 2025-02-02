package br.luciano.codetickets.jobs;

import org.springframework.batch.core.Job;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.job.builder.JobBuilder;
import org.springframework.batch.core.launch.support.RunIdIncrementer;
import org.springframework.batch.core.repository.JobRepository;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

//@Configuration
public class MovimentacaoJobConfiguration {

//    @Bean
    public Job jobMovimentacao(Step movimentacaoInicial, JobRepository jobRepository) {
        return new JobBuilder("movimentacao-banco", jobRepository)
                .start(movimentacaoInicial)
                .incrementer(new RunIdIncrementer())
                .build();
    }
}
