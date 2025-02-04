package br.luciano.codetickets.steps;

import br.luciano.codetickets.models.Movimentacao;
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
public class MovimentacaoStepConfig {

    @Autowired
    private PlatformTransactionManager transactionManager;

    @Bean
    public Step movimentacaoInicial(@Qualifier("movimentacaoReader") ItemReader<Movimentacao> movimentacaoReader,
                                    @Qualifier("movimentacaoWriter") ItemWriter<Movimentacao> movimentacaoWriter,
                                    JobRepository jobRepository) {
        return new StepBuilder("movimentacao-inicial", jobRepository)
                .<Movimentacao, Movimentacao>chunk(200, transactionManager)
                .reader(movimentacaoReader)
                .writer(movimentacaoWriter)
                .allowStartIfComplete(true)
                .build();
    }
}
