package br.luciano.codetickets.steps;

import br.luciano.codetickets.models.Movimentacao;
import br.luciano.codetickets.writers.MovimentacaoWriter;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.repository.JobRepository;
import org.springframework.batch.core.step.builder.StepBuilder;
import org.springframework.batch.item.ItemReader;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;
import org.springframework.transaction.PlatformTransactionManager;

@Component
public class MovimentacaoSteps {

    @Autowired
    private PlatformTransactionManager transactionManager;

    @Autowired
    private MovimentacaoWriter writer;

    @Bean
    public Step movimentacaoInicial(ItemReader<Movimentacao> reader, JobRepository jobRepository) {
        return new StepBuilder("movimentacao-inicial", jobRepository)
                .<Movimentacao, Movimentacao>chunk(200, transactionManager)
                .reader(reader)
                .writer(writer.writer())
                .build();
    }
}
