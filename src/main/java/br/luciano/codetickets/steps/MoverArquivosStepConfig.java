package br.luciano.codetickets.steps;

import org.springframework.batch.core.Step;
import org.springframework.batch.core.repository.JobRepository;
import org.springframework.batch.core.step.builder.StepBuilder;
import org.springframework.batch.core.step.tasklet.Tasklet;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.transaction.PlatformTransactionManager;

@Configuration
public class MoverArquivosStepConfig {

    @Autowired
    private PlatformTransactionManager transactionManager;

    @Bean
    public Step moverArquivosStep(@Qualifier("moverArquivosTasklet") Tasklet moverArquivosTasklet, JobRepository jobRepository) {
        return new StepBuilder("mover-arquivo", jobRepository)
                .tasklet(moverArquivosTasklet, transactionManager)
                .allowStartIfComplete(true)
                .build();
    }
}
